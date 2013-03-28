package controller.upload;

import java.util.List;
import logging.Log;
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
	public final static String UML_EXTENSION = "uml";
	public final static String NOTATION_EXTENSION = "notation";
	public final static String DI_EXTENSION = "di";

	/**
	 * For a valid target file, instantiates and returns an upload processor
	 * 
	 * 	 @param  pTargetFilePath
	 *  
	 *   @param  fileList
	 *   
 	 *   @return upload processor object for valid target file, else null
	 */
	public static UploadProcessor getUploadProcessorMethod(
			String pTargetFilePath, List<FileInfo> fileList, String path, int id) {

		switch (getFileType(pTargetFilePath)) {

		case ECORE_FILE_CLASS:
			if (fileList.size() == 1) {
				return new EcoreUploadProcessor(fileList.get(0).getDestFilePath(), 
						fileList.get(0).getFileName(), fileList.get(0).getLibPath());
			}
			break;
		case XMI_FILE_CLASS:
			// Stub
			if (fileList.size() == 3) {
				return new UmlUploadProcessor(fileList, path, id);
			}
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

		} else if (extension.equalsIgnoreCase(UML_EXTENSION) || extension.equalsIgnoreCase(NOTATION_EXTENSION)
					||  extension.equalsIgnoreCase(DI_EXTENSION)
				) {

			return XMI_FILE_CLASS;

		}

		return INVALID_FILE;
	}
}
