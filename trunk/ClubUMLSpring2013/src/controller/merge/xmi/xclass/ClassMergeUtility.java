package controller.merge.xmi.xclass;

import java.util.ArrayList;

public final class ClassMergeUtility {

	/**
	 * Creates the href value based on the XmiNotationElement and the new fileName
	 * @param element
	 * @param fileName
	 * @return href value
	 */
	public final static String buildHref(XmiNotationElement element, String fileName) {
		return fileName + ".uml#" + element.getNewId();
	}

	/**
	 * Creates the href value based on the Id and the new fileName
	 * @param id
	 * @param fileName
	 * @return href value
	 */
	public final static String buildHref(String Id, String fileName) {
		return fileName + ".uml#" + Id;
	}
	
	/**
	 * Builds a list of the specified notation element type
	 * @param element to pull the children elements from
	 * @param notationType int value for type
	 * @return arraylist of XmiNotationElement for specified type
	 */
	public final static ArrayList<XmiNotationElement> getNotationElements(XmiNotationElement element, int notationType) {
		
		ArrayList<XmiNotationElement> elements = new  ArrayList<XmiNotationElement>();
		for (XmiNotationElement notation : element.getElementList()) {
			if (notation.getType() == notationType) {
				elements.add(notation);
			}
		}
		
		return elements;
	}
	
}
