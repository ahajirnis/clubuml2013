package uml2parser;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

/**
 * 
 * @author 
 *
 */
public class ModelFileInfo {
	
	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private List<XmiElement> list; 
	/**
	 * 
	 * @param fileName
	 */
	public ModelFileInfo(String fileName) {
		this.fileName = fileName;
		list= new ArrayList<XmiElement>();
	}
	/**
	 * 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 
	 * @param elem
	 */
	public void addElement(XmiElement elem) {
		list.add(elem);
	}
	/**
	 * 
	 * @param idx
	 * @return
	 */
	public XmiElement getElement(int idx) {
		return list.get(idx);
	}
	/**
	 * 
	 * @param parentElem
	 * @param childElem
	 */
	public void AddChildElement(XmiElement parentElem, XmiElement childElem) {
		
		for (int i = 0 ; i < list.size(); i++) {
			if (parentElem.equals(list.get(i))){
				parentElem.addChildElement(childElem);
				break;
			}
		}
		
	}
}
