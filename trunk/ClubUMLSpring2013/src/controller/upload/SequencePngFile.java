package controller.upload;

import org.apache.tomcat.jni.File;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import logging.Log;
import java.io.*;

public abstract class SequencePngFile implements UploadProcessor{
	public static void createPngFile(String pFileName, String pInFileName,
			String pFileDir, String pLibPath) {
	try {
		final String umlPicutilPath = "D:\\GnuWin32\\bin\\pic2plot.exe";
		//logging.Log.LogCreate().Info("pFileName =" + pFileName + " pInFileName=" + pInFileName + " pFileDir= " + pFileDir + " pLibPath = " + pLibPath);
		logging.Log.LogCreate().Info(umlPicutilPath);
		Path srcSeqPicFile =new java.io.File(pLibPath + "sequence.pic").toPath();
		
		Path dstSeqPicFile = new java.io.File(pFileDir + "sequence.pic").toPath();
		Path temp = java.nio.file.Files.copy(srcSeqPicFile, dstSeqPicFile,REPLACE_EXISTING);
		logging.Log.LogCreate().Info(temp.toString());

		String picFileName = pFileDir + pInFileName;
		String pngFileName = pFileDir + pFileName + ".png";
		
		logging.Log.LogCreate().Info(picFileName);
		logging.Log.LogCreate().Info(pngFileName);

		// Command to generate PNG file from pic file
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:\\test.bat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(umlPicutilPath + " -Tpng --bitmap-size 1000x1000 " + picFileName + ">" + pngFileName + "\n" + "EXIT");
		bw.close();
		fw.close();
		String command[] = new String[]{"cmd","/k","start","C:\\test.bat"};  
		try {
			//Process procObj = Runtime.getRuntime().exec(command);
			 Runtime.getRuntime().exec(command);
		
		} catch (Exception e) {
			e.printStackTrace();
			Log.LogCreate().Info("Error 2: generating png file failed .." + e.getMessage());
		}

	} catch (Exception e) {
		Log.LogCreate().Info("Error in creating the png file............." + e.getMessage());
		
	}
	}

}
