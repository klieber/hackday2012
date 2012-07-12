package sf.hackday.roo.sample;

import java.util.logging.Logger;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.springframework.roo.shell.CliAvailabilityIndicator;
import org.springframework.roo.shell.CliCommand;
import org.springframework.roo.shell.CliOption;
import org.springframework.roo.shell.CommandMarker;
import org.springframework.roo.shell.converters.StaticFieldConverter;

/**
 * Example of a command class. The command class is registered by the Roo shell following an
 * automatic classpath scan. You can provide simple user presentation-related logic in this
 * class. You can return any objects from each method, or use the logger directly if you'd
 * like to emit messages of different severity (and therefore different colors on 
 * non-Windows systems).
 * 
 * @since 1.1.1
 */
@Component // Use these Apache Felix annotations to register your commands class in the Roo container
@Service
public class SampleCommands implements CommandMarker { // All command types must implement the CommandMarker interface
    
    /**
     * Get hold of a JDK Logger
     */
    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * Get a reference to the SampleOperations from the underlying OSGi container
     */
    @Reference private SampleOperations operations; 
    
    /**
     * Get a reference to the StaticFieldConverter from the underlying OSGi container;
     * this is useful for 'type save' command tab completions in the Roo shell
     */
    @Reference private StaticFieldConverter staticFieldConverter;

    /**
     * The activate method for this OSGi component, this will be called by the OSGi container upon bundle activation 
     * (result of the 'addon install' command) 
     * 
     * @param context the component context can be used to get access to the OSGi container (ie find out if certain bundles are active)
     */
    protected void activate(ComponentContext context) {
        staticFieldConverter.add(SamplePropertyName.class);
    }

    /**
     * The deactivate method for this OSGi component, this will be called by the OSGi container upon bundle deactivation 
     * (result of the 'addon remove' command) 
     * 
     * @param context the component context can be used to get access to the OSGi container (ie find out if certain bundles are active)
     */
    protected void deactivate(ComponentContext context) {
        staticFieldConverter.remove(SamplePropertyName.class);
    }
    
    // *************************************************************************
    //    Example 1 Printing colored messages to the shell
    // *************************************************************************
    
    /**
     * This method is optional. It allows automatic command hiding in situations when the command should not be visible.
     * For example the 'entity' command will not be made available before the user has defined his persistence settings 
     * in the Roo shell or directly in the project.
     * 
     * You can define multiple methods annotated with {@link CliAvailabilityIndicator} if your commands have differing
     * visibility requirements.
     * 
     * @return true (default) if the command should be visible at this stage, false otherwise
     */
    @CliAvailabilityIndicator("say hello")
    public boolean isSayHelloAvailable() {
        return true; // This command is always available!
    }
    
    /**
     * This method registers a command with the Roo shell. It also offers two command attributes, a mandatory one and an
     * optional command which has a default value.
     * 
     * @param name 
     * @param country
     */
    @CliCommand(value = "say hello", help = "Prints welcome message to the Roo shell")
    public void sayHello(
        @CliOption(key = "name", mandatory = true, help = "State your name") String name, // A mandatory command attribute
        @CliOption(key = "countryOfOrigin", mandatory = false, help = "Country of orgin") SamplePropertyName country) {
        
        log.info("Welcome " + name + "!");
        log.warning("Country of origin: " + (country == null ? SamplePropertyName.NOT_SPECIFIED.getPropertyName() : country.getPropertyName()));
        log.severe("It seems you are a running JDK " + operations.getProperty("java.version"));
        log.info("You can use the default JDK logger anywhere in your add-on to send messages to the Roo shell");
    }
    
    // *************************************************************************
    //    Example 2 Installing and replacing tagx files in the target project
    // *************************************************************************
    
    /**
     * Define when "web mvc install tags" command should be visible in the Roo shell. 
     * In this case we want to hide the command until the WEB-INF/tags folder is present.
     * 
     * @return true (default) if the command should be visible at this stage, false otherwise
     */
    @CliAvailabilityIndicator("web mvc install tags") // Define the exact command name
    public boolean isInstallTagsCommandAvailable() {
        return operations.isInstallTagsCommandAvailable();
    }
    
    /**
     * Replace existing MVC tagx files in the target project
     */
    @CliCommand(value = "web mvc install tags", help="Replace default Roo MVC tags used for scaffolding")
    public void installTags() {
        operations.installTags();
    }
}