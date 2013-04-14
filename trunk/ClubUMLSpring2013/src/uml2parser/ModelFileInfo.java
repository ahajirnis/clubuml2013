package uml2parser;

import java.util.ArrayList;
import java.util.List;
import logging.Log;
import org.xml.sax.Attributes;

/**
 * 
 * @author Prashant Shukla
 * When we parse a XMI file. This we have a list of elements that are present in the file
 * This list only contains the top level elements. 
 *
 */
public class ModelFileInfo {
	
	
	XmiIdToElementMap xmiIdElement;
	
	/**
	 * Name of the file that was parsed. 
	 */
	private String fileName;

	/**
	 * Name of the file with out path. 
	 */
	private String fileNameNoPath;
	
	/**
	 * List of top level elements in this XMI file
	 */
	private List<XmiElement> list; 
	
	/**
	 * Constructor
	 * @param fileName
	 */
	public ModelFileInfo(String fileName) {
		this.fileName = fileName;
		
		String[] fileParts = fileName.split("[\\\\|/]");
		fileNameNoPath = fileParts[fileParts.length - 1];
		
		xmiIdElement = new XmiIdToElementMap();
		list= new ArrayList<XmiElement>();
	}
	/**
	 * returns the name of the file.
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * Add a element to the list. 
	 * @param elem
	 */
	public void addElement(XmiElement elem) {
		//Log.LogCreate().Info(elem.getElementName());
		list.add(elem);
		String id = elem.getElementId();
		if (id != null) {
			xmiIdElement.addElement(id,elem);
		}
	}
	/**
	 * Returns the
	 * @param idx
	 * @return
	 */
	public XmiElement getElement(int idx) {
		XmiElement retElem = null;
		if (idx >= 0  && idx < list.size()) {
			retElem = list.get(idx);
		}
		return retElem;
	}
	/**
	 * 
	 * @param parentElem
	 * @param childElem
	 */
	public void AddChildElement(XmiElement parentElem, XmiElement childElem) {
		for (int i = 0 ; i < list.size(); i++) {
			if (ElementIteratorToAddChildElem(parentElem, childElem, list.get(i)) == true)
				break;
		}
		
	}
	/**
	 * 
	 * @param parentElem
	 * @param childElem
	 * @param listElem
	 * @return
	 */
	private boolean ElementIteratorToAddChildElem(XmiElement parentElem, XmiElement childElem, XmiElement listElem) {
		boolean retval = false;
		if (parentElem.getElementId() == null ) {
			if (parentElem.getElementName().equals(listElem.getElementName())) {
				//Log.LogCreate().Info("AddChildElement : " + parentElem.getElementName() + " childElem " + childElem.getElementName());
				parentElem.addChildElement(childElem);
				String id = childElem.getElementId();
				if(id != null) {
					xmiIdElement.addElement(id,childElem);
				}
				retval = true;
			}
		} else if (parentElem.getElementId().equals(listElem.getElementId())){
			//Log.LogCreate().Info("AddChildElement : " + parentElem.getElementName() + " childElem " + childElem.getElementName());
			parentElem.addChildElement(childElem);
			String id = childElem.getElementId();
			if(id != null) {
				xmiIdElement.addElement(id,childElem);
			}
			retval = true;
		} else {
			List <XmiElement> list = listElem.getChildElemList();
			for (int i = 0; i < list.size(); i++) {
				if (ElementIteratorToAddChildElem (parentElem, childElem, list.get(i))) {
					retval = true;
					break;
				}
				
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param ElemName
	 * @return
	 */
	public List<XmiElement> findElementsByName(String ElemName) {
		List<XmiElement> elementList = new ArrayList<XmiElement>();
		//Log.LogCreate().Info("findElementsByName: elementList size" + String.valueOf(elementList.size()) );
		for (int i = 0; i < list.size(); i++) {
			XmiElement elem= getElement(i);
			ElementIterator(elem,ElemName,elementList);
		}
		return elementList;

	}
	/**
	 * 
	 * @param name
	 * @param elemname
	 * @param elementList
	 */
	private void ElementIterator(XmiElement name, String elemname,List<XmiElement> elementList) {
		
		List <XmiElement> childElementlist = name.getChildElemList();
		//Log.LogCreate().Info("ElementIterator:  Element name " + name.getElementName());
		if(name.getElementName().equals(elemname)) {			
			elementList.add(name);
		} 
		
		if(childElementlist != null) {
			//Log.LogCreate().Info("ElementIterator:" +  name.getElementName() +" elementList size " + String.valueOf(childElementlist.size()) );
			// iterate the xmiElement which contains all the information 
			for (int i = 0; i < childElementlist.size(); i++) {
				ElementIterator(childElementlist.get(i),elemname,elementList);

			}		
		}
		return;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean findElementsById(String id) {
		boolean retval = false;
		for (int i = 0; i < list.size(); i++) {
			XmiElement elem= getElement(i);
			retval = ElementIteratorById(elem,id);
			if (retval) 
				break;
		}
		return retval;
	}
	/**
	 * 
	 * @param name
	 * @param elemId
	 * @return
	 */
	private boolean ElementIteratorById(XmiElement name, String elemId) {
		List <XmiElement> childElementlist = name.getChildElemList();
		
		if(name.getElementId() != null && name.getElementId().equals(elemId)) {
			name.setFoundMatch(true);
			return true;
		}
		if(childElementlist != null) {
		// iterate the xmiElement which contains all the information 
			for (int i = 0; i < childElementlist.size(); i++) {
				if (ElementIteratorById(childElementlist.get(i),elemId) == true)
					return true;
			}
		
		}	
		return false;
	}
	
	public XmiElement getXmiElementFromId(String id) {
		return xmiIdElement.getElementFromId(id);
	}
	
	/**
	 * @return the FileName without the path
	 */
	public String getFileNameNoPath() {
		return fileNameNoPath;
	}
}
