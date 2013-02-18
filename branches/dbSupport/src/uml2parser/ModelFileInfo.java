package uml2parser;


import java.util.ArrayList;
import java.util.List;

import logging.Log;

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
		//Log.LogCreate().Info(elem.getElementName());
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
			if (ElementIteratorToAddChildElem(parentElem, childElem, list.get(i)) == true)
				break;
		}
		
	}
	
	private boolean ElementIteratorToAddChildElem(XmiElement parentElem, XmiElement childElem, XmiElement listElem) {
		boolean retval = false;
		if (parentElem.getElementId() == null ) {
			if (parentElem.getElementName().equals(listElem.getElementName())) {
				//Log.LogCreate().Info("AddChildElement : " + parentElem.getElementName() + " childElem " + childElem.getElementName());
				parentElem.addChildElement(childElem);
				retval = true;
			}
		} else if (parentElem.getElementId().equals(listElem.getElementId())){
			//Log.LogCreate().Info("AddChildElement : " + parentElem.getElementName() + " childElem " + childElem.getElementName());
			parentElem.addChildElement(childElem);
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
	
	public List<XmiElement> findElementsByName(String ElemName) {
		List<XmiElement> elementList = new ArrayList<XmiElement>();
		//Log.LogCreate().Info("findElementsByName: elementList size" + String.valueOf(elementList.size()) );
		for (int i = 0; i < list.size(); i++) {
			XmiElement elem= getElement(i);
			ElementIterator(elem,ElemName,elementList);
		}
		return elementList;

	}
	
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
	
	public boolean fileElementsById(String id) {
		boolean retval = false;
		for (int i = 0; i < list.size(); i++) {
			XmiElement elem= getElement(i);
			retval = ElementIteratorById(elem,id);
			if (retval) 
				break;
		}
		return retval;
	}
	
	private boolean ElementIteratorById(XmiElement name, String elemId) {
		List <XmiElement> childElementlist = name.getChildElemList();
		
		if(name.getElementId() != null && name.getElementId().equals(elemId)) {
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
}
