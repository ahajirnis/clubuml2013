package controller.compare;

import java.util.List;

public interface ComparerIntf {	
	
	/*
	 * Take two path string which points to the actual diagram file
	 * 
	 * @return A list of generic element, here I just put Object there which can hold any type of the element.
	 */
	public List<Object> compare(String filePath1, String filePath2);
	
}
