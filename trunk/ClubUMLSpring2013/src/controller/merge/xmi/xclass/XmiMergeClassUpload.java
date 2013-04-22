package controller.merge.xmi.xclass;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import controller.upload.FileInfo;
import controller.upload.UploadProcessor;
import controller.upload.UploadProcessorFactory;

/**
 * Stripped down version of the UploadServlet
 * (This code should be refactored with UploadServlet to avoid duplicate code)
 * @author RD2012
 *
 */
public class XmiMergeClassUpload {

	private static final String DESTINATION_DIR_PATH = "/uploads/";
	private static final String LIB_DIR_PATH = "/lib/";
	private File destinationDir;
	private File libDir;
	private List<FileInfo> fileList;
	private String newFolder;
	private ServletContext context;
	private String id_file_date;
	public XmiMergeClassUpload() {}


		public void process(ServletContext context, HttpSession session, 
				File umlFile, File notationFile, File diFile) 
						throws ServletException, IOException {

			// Set id properly
			String id = session.getAttribute("userId").toString();

			this.context = context;

			libDir = new File(context.getRealPath(LIB_DIR_PATH));
			
			fileList = new ArrayList<FileInfo>();
			
			String filename = umlFile.getName();
			System.out.println("UML Name: " + filename);

			try {
				destinationDir = createDir(id);

				new File(destinationDir, filename);		
				String absolutePath = destinationDir + "\\";

				String libPath = libDir + "\\";
			
				// copy files to new folder in webapp
				CopyFile(umlFile, absolutePath);
				CopyFile(notationFile, absolutePath);
				CopyFile(diFile, absolutePath);
				
				// Add files to list
				fileList.add(new FileInfo(absolutePath, umlFile.getName(), libPath));
				fileList.add(new FileInfo(absolutePath, notationFile.getName(), libPath));
				fileList.add(new FileInfo(absolutePath, diFile.getName(), libPath));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// Obtains upload processor to perform parsing and file 
			// generations		
			if (!fileList.isEmpty()) {
				String folderPath = "uploads/" + id_file_date ;
				UploadProcessor processor = UploadProcessorFactory
						.getUploadProcessorMethod(filename  ,fileList, folderPath, Integer.parseInt(id));
				System.out.println("GetProcessor: " + filename + " " + folderPath + " " + Integer.parseInt(id));
				if (processor != null){
					System.out.println("CREATED NEW FILES");
					processor.process();
				} else {
					System.out.println("FAILED PROCESS CREATING NEW FILES");
				}
			}	


		}

		private void CopyFile(File fileToCopy, String destination) {
			Path from = Paths.get(fileToCopy.getAbsoluteFile().toString());
			Path to = Paths.get(destination + fileToCopy.getName());
			try {
				Files.copy(from, to);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		private File createDir(String id) {
			id_file_date = new SimpleDateFormat("yyyy-MM-dd_HHmmss") .format(new java.util.Date());
			newFolder = context.getRealPath(DESTINATION_DIR_PATH) + "/" + id_file_date ;
			File dir = new File(newFolder);
			logging.Log.LogCreate().Info("Creating folder = " + dir.toString());
			if(!dir.mkdirs()  )
			{
				logging.Log.LogCreate().Info("Failed to create folder " + newFolder);
			}

			return dir;
		}
		
}



