package controller.upload;


import logging.Log;
/**
 * Abstract class for Class diagram upload processors. Implements a
 * createPngFile() that should be common for Class diagrams.
 * 
 * @author RD2012
 * 
 */
public abstract class ClassUploadProcessor implements UploadProcessor {

	/**
	 * Generates PNG file.
	 * 
	 * @param pFileName
	 *            file name which is used for creating the name for the dot and
	 *            png files.
	 * @param pJavaFileName
	 *            java file name used to generate the dot file. This file should
	 *            be located in the pFileDir path.
	 * @param pFileDir
	 *            directory containing uploaded files and generated files.
	 * @param pLibPath
	 *            library path in context which contains UmlGraph-5.6.jar.
	 */
	public void createPngFile(String pFileName, String pJavaFileName,
			String pFileDir, String pLibPath) {

		try {

			String umlGraphPath = pLibPath + "UmlGraph-5.6.jar";
			String dotFileName = pFileName + ".dot";
			String pngFileName = pFileName + ".png";
			
			// Command to create the dot file from a Java file
			String command1[] = { "java", "-jar", umlGraphPath, "-all",
					"-private", pFileDir + pJavaFileName, "-output",
					pFileDir + dotFileName };

			Process procObj1 = Runtime.getRuntime().exec(command1);

			int exitVal = procObj1.waitFor();

			if (exitVal != 0) {
				Log.LogCreate().Info("Error in creating the dot file");
			}

			try {
				Thread.sleep(1000L);
			} catch (Exception e) {
				e.printStackTrace();
				Log.LogCreate().Info("Error 1: creating dot file failed");
			}

			// Command to generate PNG file from dot file
			String command2[] = { UploadProcessor.GRAPHICVIZ_PATH_WINDOWS,
					"-Tpng", "-o", pFileDir + pngFileName,
					pFileDir + dotFileName };

			try {
				Process procObj = Runtime.getRuntime().exec(command2);
				exitVal = procObj.waitFor();
				if (exitVal != 0) {
					Log.LogCreate().Info("Error in creating the png file");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.LogCreate().Info("Error 2: generating png file failed");
			}

		} catch (Exception e) {
			Log.LogCreate().Info("Error in creating the png file.............");
			e.printStackTrace();
		}
	}
}
