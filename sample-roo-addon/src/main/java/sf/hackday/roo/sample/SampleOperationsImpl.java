package sf.hackday.roo.sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.springframework.roo.process.manager.FileManager;
import org.springframework.roo.process.manager.MutableFile;
import org.springframework.roo.project.Path;
import org.springframework.roo.project.PathResolver;
import org.springframework.roo.project.ProjectOperations;
import org.springframework.roo.support.util.FileUtils;

/**
 * Implementation of {@link SampleOperations} interface.
 *
 * @since 1.1.1
 */
@Component
@Service
public class SampleOperationsImpl implements SampleOperations {

    private static final char SEPARATOR = File.separatorChar;

    /**
     * Get a reference to the FileManager from the underlying OSGi container. Make sure you
     * are referencing the Roo bundle which contains this service in your add-on pom.xml.
     * 
     * Using the Roo file manager instead if java.io.File gives you automatic rollback in case
     * an Exception is thrown.
     */
    @Reference private FileManager fileManager;
    
    /**
     * Get a reference to the ProjectOperations from the underlying OSGi container. Make sure you
     * are referencing the Roo bundle which contains this service in your add-on pom.xml.
     */
    @Reference private ProjectOperations projectOperations;

    /** {@inheritDoc} */
    public boolean isInstallTagsCommandAvailable() {
        return projectOperations.isFocusedProjectAvailable() && fileManager.exists(projectOperations.getPathResolver().getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF" + SEPARATOR + "tags"));
    }

    /** {@inheritDoc} */
    public String getProperty(String propertyName) {
        Validate.notBlank(propertyName, "Property name required");
        return System.getProperty(propertyName);
    }

    /** {@inheritDoc} */
    public void installTags() {
        // Use PathResolver to get canonical resource names for a given artifact
        PathResolver pathResolver = projectOperations.getPathResolver();
        createOrReplaceFile(pathResolver.getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF" + SEPARATOR + "tags" + SEPARATOR + "util"), "info.tagx");
        createOrReplaceFile(pathResolver.getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF" + SEPARATOR + "tags" + SEPARATOR + "form"), "show.tagx");
    }
    
    /**
     * A private method which illustrates how to reference and manipulate resources
     * in the target project as well as the bundle classpath.
     * 
     * @param path
     * @param fileName
     */
    private void createOrReplaceFile(String path, String fileName) {
        String targetFile = path + SEPARATOR + fileName;
        
        // Use MutableFile in combination with FileManager to take advantage of Roo's transactional file handling which 
        // offers automatic rollback if an exception occurs
        MutableFile mutableFile = fileManager.exists(targetFile) ? fileManager.updateFile(targetFile) : fileManager.createFile(targetFile);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // Use FileUtils to open an InputStream to a resource located in your bundle
            inputStream = FileUtils.getInputStream(getClass(), fileName);
            outputStream =  mutableFile.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
    }
}