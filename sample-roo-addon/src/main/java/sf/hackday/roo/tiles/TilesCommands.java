package sf.hackday.roo.tiles;

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
public class TilesCommands implements CommandMarker { // All command types must implement the CommandMarker interface
    
    /**
     * Get hold of a JDK Logger
     */
    private Logger log = Logger.getLogger(getClass().getName());

    /**
     * Get a reference to the SampleOperations from the underlying OSGi container
     */
    @Reference private TilesOperations operations; 
    
    @CliAvailabilityIndicator("web mvc tiles")
    public boolean isAddTilesAvailable() {
    	return this.operations.isAddTilesCommandAvailable();
    }
    
    @CliCommand(value = "web mvc tiles", help = "Add apache tiles to your web mvc project")
    public void addTiles() {
    	this.operations.addTiles();
    }
}