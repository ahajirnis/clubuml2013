package controller.compare;

import java.util.List;

import controller.upload.FileInfo;

public interface ComparerIntf {	
	
	/*
	 * Take two path string which points to the actual diagram file
	 * 
	 * @return A list of generic element, here I just put Object there which can hold any type of the element.
	 */
	public List<Object> compare(List<FileInfo>file1, List<FileInfo>file2, String compareLayer);
	
}
