package controller.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;

public class DownloadZipfile {
	private static final int BUFFER_SIZE = 1024;
    private static final int COMPRESSION_LEVEL = 8; //default:8 
    
    /**
     * Compress
     * @param targetPath
     * @param output - the output filename  
     * @throws IOException
     */
	public void downloadZipfileProcessor(
			String targetPath, 
			String output) throws IOException {

		File targetFile = new File(targetPath);
		
		if(!targetFile.isFile() && !targetFile.isDirectory()) {
		   	throw new IOException("File not found");
	     }
		
		if(!(StringUtils.substringAfterLast(output, ".")).equalsIgnoreCase("zip")) {
			throw new IOException("Check the output filename extension(.zip)");
		}
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    ZipOutputStream zos = null;
    	
	    try {
    		fos = new FileOutputStream(output);
    		bos = new BufferedOutputStream(fos);
    		zos = new ZipOutputStream(bos);
    		zos.setLevel(COMPRESSION_LEVEL); 
    		zipEntry(targetFile, targetPath, zos); 
    		zos.finish();
    		zos.close();
    	} catch (IOException ex) {
    	    
    	} finally {
    		if(zos != null) {
    			zos.close();
    		}
    		if(bos != null) {
    			bos.close();
    		}
    		if(fos != null) {
    			fos.close();
    		}	
    	}
	}
	
	/** 
	 * Compress a targetFile
	 * @param targetFile
	 * @param targetPath
	 * @param zos
	 * @throws IOException
	 */
	
    private static void zipEntry(File targetFile, String targetPath, ZipOutputStream zos) 
        	throws IOException {
        	
        	if(targetFile.isDirectory()) {
        		if(targetFile.getName().equalsIgnoreCase(".metadata")){
        			return;
        		}
        		File[] fileArray = targetFile.listFiles();
        		for(int i = 0; i < fileArray.length; i++) {
        			zipEntry(fileArray[i], targetPath, zos);
        		}
        	} else {
        	
        		BufferedInputStream bis = null;
        		FileInputStream fis = null;
        		try {
        			String sFilePath = targetFile.getPath();
        			String zipEntryName = sFilePath.substring(targetPath.length()+1, sFilePath.length());
        			//System.out.println("targetFile :" + targetFile);
        			//System.out.println("filepath :" + sFilePath);
        			//System.out.println("zipEntryName :" + zipEntryName);
        			
        			fis = new FileInputStream(targetFile);
        			bis = new BufferedInputStream(fis);
        			ZipEntry zentry = new ZipEntry(zipEntryName);
        			zentry.setTime(targetFile.lastModified());
        			zos.putNextEntry(zentry);
        		
        			byte[] buffer = new byte[BUFFER_SIZE];
        			int cnt = 0;

        			while ((cnt = bis.read(buffer,0,BUFFER_SIZE)) != -1) {
        				zos.write(buffer,0,cnt);
        			}
        			zos.closeEntry();
        		
        		} finally {
        			
        			if(bis != null) {
        				bis.close();
        			}
        			if(fis != null) {
        				fis.close();
        			}
        		}
        	}
        }
}
