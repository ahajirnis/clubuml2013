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
		final String umlPicutilPath = "c:\\csye7945\\clubuml2012\\trunk\\ClubUMLSpring2013\\tools\\win\\pic2plot.exe";
		//logging.Log.LogCreate().Info("pFileName =" + pFileName + " pInFileName=" + pInFileName + " pFileDir= " + pFileDir + " pLibPath = " + pLibPath);
		logging.Log.LogCreate().Info(umlPicutilPath);
		Path srcSeqPicFile =new java.io.File(pLibPath + "sequence.pic").toPath();
		
		Path dstSeqPicFile = new java.io.File(pFileDir + "sequence.pic").toPath();
		Path temp = java.nio.file.Files.copy(srcSeqPicFile, dstSeqPicFile,REPLACE_EXISTING);
		logging.Log.LogCreate().Info(temp.toString());
		//String umlPicutilPath = pLibPath + "pic2plot";
		String picFileName = "\"" + pFileDir + pInFileName + "\"";
		String pngFileName =  pFileDir + "Sequence_diagram_" + pFileName + ".png" ;
		

		// Command to generate PNG file from pic file
		String command[] = {  umlPicutilPath,
				"-Tpng",  picFileName, ">",
				 pngFileName };
		//logging.Log.LogCreate().Info("umlPicutilPath =" + umlPicutilPath + " pngFileName=" + pngFileName + " picFileName= " + picFileName );
		
		try {
			Process procObj = Runtime.getRuntime().exec(umlPicutilPath + " -Tpng " + picFileName);
			java.io.File file = new java.io.File(pngFileName);
			if (!file.exists()) {
				file.createNewFile();
				logging.Log.LogCreate().Info("Here is the standard output of the command:\n");
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader stdInput = new BufferedReader(new 
		             InputStreamReader(procObj.getInputStream()));

		        BufferedReader stdError = new BufferedReader(new 
		             InputStreamReader(procObj.getErrorStream()));
		        String s = "";
		        int t;
		        // read the output from the command
		        logging.Log.LogCreate().Info("Here is the standard output of the command:\n");
		        while (( t = stdInput.read()) != -1) {
		        	bw.write(t);		        	
		        }
		        bw.close();	
		        // read any errors from the attempted command
		        logging.Log.LogCreate().Info("Here is the standard error of the command (if any):\n");
		        while ((s = stdError.readLine()) != null) {
		        	logging.Log.LogCreate().Info(s);
		        }
			
			
			int exitVal = procObj.waitFor();
			if (exitVal != 0) {
				Log.LogCreate().Info("Error in creating the png file " + String.valueOf(exitVal));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.LogCreate().Info("Error 2: generating png file failed .." + e.getMessage());
		}

	} catch (Exception e) {
		Log.LogCreate().Info("Error in creating the png file............." + e.getMessage());
		
	}
	}

}
