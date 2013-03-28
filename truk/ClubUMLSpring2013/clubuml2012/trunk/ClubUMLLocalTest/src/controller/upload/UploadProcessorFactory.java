package controller.upload;

/**
 * UploadProcessFactory - Returns an instance of an Upload object depending on
 * the file that is passed in.
 * 
 * @author Richard Do
 * 
 */
public class UploadProcessorFactory {

	// file types contants
	private final static int INVALID_FILE = 0;
	private final static int ECORE_FILE_CLASS = INVALID_FILE + 1;
	private final static int XMI_FILE_CLASS = ECORE_FILE_CLASS + 1;

	// extensions
	private final static String ECORE_EXTENSION = "ecore";
	private final static String XMI_EXTENSION = "xmi";

	/**
	 * For a valid target file, instantiates and returns an upload processor
	 * 
	 * @param pTargetFilePath
	 *            file to process
	 * @param pDestFilePath
	 *            destination path to create new files
	 * @param pFileName
	 *            new file name
	 * @param pLibPath
	 *            path to the library directory for web application.
	 * @return upload processor object for valid target file, else null
	 */
	public static UploadProcessor getUploadProcessorMethod(
			String pTargetFilePath, String pDestFilePath, String pFileName,
			String pLibPath) {

		switch (getFileType(pTargetFilePath)) {

		case ECORE_FILE_CLASS:
			return new EcoreUploadProcessor(pDestFilePath, pFileName, pLibPath);

		case XMI_FILE_CLASS:
			// Stub
			// return new XmiUploadProcessor(pDestFilePath, pFileName, pLibPath);
			break;
		}

		return null;
	}

	/**
	 * Extracts the file type
	 * 
	 * @param pTargetFilePath
	 *            file used to evaluate the file type based on extension
	 * @return integer representation of the file type to upload (see constants)
	 */
	private static int getFileType(String pTargetFilePath) {

		// Retrieve file extension
		String extension = pTargetFilePath.substring(
				pTargetFilePath.lastIndexOf(".") + 1, pTargetFilePath.length());

		if (extension.equalsIgnoreCase(ECORE_EXTENSION)) {

			return ECORE_FILE_CLASS;

		} else if (extension.equalsIgnoreCase(XMI_EXTENSION)) {

			return XMI_FILE_CLASS;

		}

		return INVALID_FILE;
	}
}
