package controller.download;

import java.io.File;
import java.io.IOException;

public class DownloadDirectory {
	private String downloadPath;
    
    public String getDownloadPath() {
    	return downloadPath;
    }
    
    public void setDownloadPath(String filePath) {
    	downloadPath = uniqueDirectory(filePath);
    }
    
    static String uniqueDirectory(String filePath) {
    	String uniqueDirectory =  Long.toString(System.nanoTime());
	    String dirPath = filePath + "\\" + uniqueDirectory;
	    return dirPath;
    }
    
    public File createDirectory(String filePath) {
    	setDownloadPath(filePath);
    	String dirPath = getDownloadPath();
	    
    	File tmpdir = new File(dirPath);	
	    if (!tmpdir.exists()) {
			if (tmpdir.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}   	
	    return tmpdir;
    }
    
	public void deleteDirectory(File file) throws IOException{
		if(file.isDirectory()){ 
			if(file.list().length==0){	 
	    		if (file.delete()) {
	   				System.out.println("Directory is deleted : " 
                               + file.getAbsolutePath());
	   			} else {
	   				System.out.println("Failed to delete directory!");
	   			}
	    	} else{
	    		String files[] = file.list();
	    		for (String temp : files) {
	        	   File fileDelete = new File(file, temp); 
	        	   //recursive delete
	        	   deleteDirectory(fileDelete);
	        	   }
	        	   //check the directory again, if empty then delete it
	        	if(file.list().length==0){
	           	     file.delete();
	        	     System.out.println("Directory is deleted : " 
	                                                  + file.getAbsolutePath());
	        	}
	    	}
		}else{
			file.delete();
	    	System.out.println("File is deleted : " + file.getAbsolutePath());
	    }
	}
}
