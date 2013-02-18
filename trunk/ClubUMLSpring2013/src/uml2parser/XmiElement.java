package uml2parser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import java.util.Stack;

public class XmiElement {
	
	final static String XMI_ID = "xmi:id" ;
	
	/**
	 * 
	 */
	private String elementName; 
	/**
	 * 
	 */
	private boolean foundmatch;
	/**
	 * 
	 */
	private XmiElement parentElem;
	/**
	 * 
	 */
	private List<XmiElement> childElem;
	/**
	 * 
	 */
	private List <Attribute> attr;
	/**
	 * 
	 */
	private String elem_xmi_id;

	/**
	 * 
	 * @param name
	 * @param attrs
	 */
	public XmiElement (String name, Attributes attrs) {
	    childElem = new ArrayList<XmiElement>();
		elementName = name;
		foundmatch = false;
		attr = new ArrayList<Attribute>();
		for (int j = 0; j < attrs.getLength();j++)
		{
			attr.add(new Attribute(attrs.getQName(j),attrs.getValue(j)));
			if (attrs.getQName(j).equals(XMI_ID)) {
				elem_xmi_id = attrs.getValue(j);
			}
		}

	}
	
	/**
	 * 
	 * @return
	 */
	public List<Attribute> getAttrib()  { 
		return attr; 
	}
	/**
	 * 
	 * @return
	 */
	public XmiElement getParentElem() { 
		return parentElem;
	}
	/**
	 * 
	 * @return
	 */
	public List<XmiElement> getChildElemList() {
		return childElem;
	}
	/**
	 * 
	 * @param elem
	 */
	public void addChildElement(XmiElement elem) {
		childElem.add(elem);
	}
	/**
	 * 
	 * @param elem
	 */
	public void setParentElem(XmiElement elem) {
		if (parentElem == null) {
			parentElem = elem;
		}
	}
	/**
	 * 
	 * @return
	 */
	public String getElementId() {
		return elem_xmi_id;
	}
	
	public String getElementName() { 
		return elementName;
	}
	
	
}
