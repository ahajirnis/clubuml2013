package logging;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Log {
	
	String filename;
	BufferedWriter bw = null;
	private static Log log = null; 
	public static Log LogCreate() {
		if (log == null) {
			log = new Log("C:\\temp\\temp.log");
		}
		return log;
	}
	
	/**
	 *  Make default constructor private
	 */
	private Log() {}
	/** 
	 *  Use this constructor only 
	 * @param filename
	 */
	private Log(String filename) {
		this.filename = filename; 
		createFile();
	}
	
	private void  createFile() {
		try {
			FileWriter fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
		}catch (Exception ex) {
			
		}
	}
	
	public void Info (String info) {
		try {
			if (bw != null){
				bw.write(info + "\n");
				bw.flush();
			}
			
		}catch (Exception ex) {
			
		}
	}
	
	public void close() {
		try {
			if (bw != null){
				bw.close();
			}
		}catch (Exception ex) {
			
		}			
	}
}