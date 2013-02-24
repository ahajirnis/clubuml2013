package uml2parser;

import logging.Log;
import java.util.HashMap;
/**
 * 
 * @author Prashant Shukla
 *
 */
public class XmiIdToElementMap {
	/**
	 * 
	 */
	private HashMap <String, XmiElement> idToElementMap;
	/**
	 * 
	 */
	public XmiIdToElementMap() {
		
		idToElementMap = new HashMap<String, XmiElement>();
	}
	/**
	 * 
	 * @param XmiId
	 * @param element
	 */
	public void addElement(String XmiId, XmiElement element ) {
		idToElementMap.put(XmiId, element);
	}
	/**
	 * 
	 * @param XmiId
	 * @return
	 */
	public XmiElement getElementFromId(String XmiId) {
		
		return (idToElementMap.get(XmiId));
		 
	}
}
