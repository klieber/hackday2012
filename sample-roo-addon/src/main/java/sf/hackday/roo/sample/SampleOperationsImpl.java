package sf.hackday.roo.sample;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.springframework.roo.process.manager.FileManager;
import org.springframework.roo.process.manager.MutableFile;
import org.springframework.roo.project.Dependency;
import org.springframework.roo.project.Path;
import org.springframework.roo.project.PathResolver;
import org.springframework.roo.project.ProjectOperations;
import org.springframework.roo.project.Property;
import org.springframework.roo.support.util.DomUtils;
import org.springframework.roo.support.util.FileUtils;
import org.springframework.roo.support.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

	public boolean isAddTilesCommandAvailable() {
		return projectOperations.isFocusedProjectAvailable() && fileManager.exists(projectOperations.getPathResolver().getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF"));
	}

	public void addTiles() {
		String groupId = "org.apache.tiles";
		String version = "${tiles.version}";
		projectOperations.addProperty("", new Property("tiles.version","2.2.2"));
		projectOperations.addDependencies("", Arrays.asList(
				new Dependency(groupId,"tiles-api",version),
				new Dependency(groupId,"tiles-core",version),
				new Dependency(groupId,"tiles-jsp",version),
				new Dependency(groupId,"tiles-servlet",version),
				new Dependency(groupId,"tiles-template",version)
				));
		
		this.updateApplicationContext();
		
		PathResolver pathResolver = projectOperations.getPathResolver();
		String layoutsPath = pathResolver.getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF" + SEPARATOR + "layouts");
		
		if (!fileManager.exists(layoutsPath)) {
			fileManager.createDirectory(layoutsPath);
		}
		createOrReplaceFile(pathResolver.getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF"), "tiles.xml");
		createOrReplaceFile(layoutsPath, "layout.jsp");

	}

	/** {@inheritDoc} */
	public String getProperty(String propertyName) {
		Validate.notBlank(propertyName, "Property name required");
		return System.getProperty(propertyName);
	}

	private void updateApplicationContext() {
		String contextPath = (projectOperations.getPathResolver().getFocusedIdentifier(Path.SRC_MAIN_WEBAPP, "WEB-INF" + SEPARATOR + "spring" + SEPARATOR + "appServlet" + SEPARATOR + "servlet-context.xml"));
		if (fileManager.exists(contextPath)) {
			final Document appCtx = XmlUtils.readXml(fileManager.getInputStream(contextPath));
			final Element root = appCtx.getDocumentElement();
			Element internalResolver = XmlUtils.findFirstElement("/beans/bean[@class = 'org.springframework.web.servlet.view.InternalResourceViewResolver']", root);
			Element viewResolver = XmlUtils.findFirstElement("/beans/bean[@class = 'org.springframework.web.servlet.view.tiles2.TilesViewResolver']", root);
			Element tilesConfigurer = XmlUtils.findFirstElement("/beans/bean[@class = 'org.springframework.web.servlet.view.tiles2.TilesConfigurer']", root);

			if (internalResolver != null) {
				root.removeChild(internalResolver);
			}

			if (viewResolver == null) {
				viewResolver = this.createBeanElement(appCtx, "viewResolver", "org.springframework.web.servlet.view.tiles2.TilesViewResolver");
				viewResolver.appendChild(createPropertyElement(appCtx,"viewClass","org.springframework.web.servlet.view.tiles2.TilesView"));
				root.appendChild(viewResolver);
			}
			
			if (tilesConfigurer == null) {
				tilesConfigurer = this.createBeanElement(appCtx, "tilesConfigurer", "org.springframework.web.servlet.view.tiles2.TilesConfigurer");
				Element definitions = this.createPropertyElement(appCtx, "definitions", null);
				definitions.appendChild(this.createListElement(appCtx, "/WEB-INF/tiles.xml"));
				tilesConfigurer.appendChild(definitions);
				root.appendChild(tilesConfigurer);
			}
			
			DomUtils.removeTextNodes(root);
			
      fileManager.createOrUpdateTextFileIfRequired(contextPath,
      		XmlUtils.nodeToString(appCtx), false);
		}
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
	
	private Element createBeanElement(Document appCtx, String id, String className) {
		Element bean = appCtx.createElement("beans:bean");
		if (id != null) {
			bean.setAttribute("id", id);
		}
		if (className != null) {
			bean.setAttribute("class", className);
		}
		return bean;
	}

	private Element createPropertyElement(Document document, final String name,	final String value) {
		final Element property = document.createElement("beans:property");
		if (name != null) {
			property.setAttribute("name", name);
		}
		if (value != null) {
			property.setAttribute("value", value);
		}
		return property;
	}
	
	private Element createListElement(Document document, String ... values) {
		Element list = document.createElement("beans:list");
		for (String value : values) {
			list.appendChild(this.createValueElement(document, value));
		}
		return list;
	}
	
	private Element createValueElement(Document document, String value) {
		Element element = document.createElement("beans:value");		
		element.setTextContent(value);
		return element;
	}
}