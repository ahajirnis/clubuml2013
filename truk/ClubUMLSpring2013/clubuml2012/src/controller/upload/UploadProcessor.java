package controller.upload;

/**
 * Interface for implementing upload processors
 * @author Richard Do
 */
public interface UploadProcessor {
	
	// paths to use GraphViz for windows and rho server to generate PNG files
	static final String GRAPHICVIZ_PATH_WINDOWS = "C:\\Graphviz\\bin\\dot";
	static final String GRAPHICVIZ_PATH_RHO = "/usr/bin/dot";
	
	/**
	 * Performs file parsing, generating java file and PNG file
	 */
	public void process();
}
