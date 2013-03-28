package controller.upload;

import logging.Log;

public abstract class SequencePngFile implements UploadProcessor{
	public static void createPngFile(String pFileName, String pInFileName,
			String pFileDir, String pLibPath) {
	try {

		String umlPicutilPath = pLibPath + "pic2plot";
		String picFileName = pInFileName;
		String pngFileName = pFileName + ".png";

		// Command to generate PNG file from pic file
		String command[] = { UploadProcessor.GRAPHICVIZ_PATH_WINDOWS, umlPicutilPath,
				"-Tpng", pFileDir + picFileName, ">",
				pFileDir + pngFileName };

		try {
			Process procObj = Runtime.getRuntime().exec(command);
			int exitVal = procObj.waitFor();
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
