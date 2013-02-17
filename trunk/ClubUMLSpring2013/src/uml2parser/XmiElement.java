package uml2parser;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;

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
	
	public XmiElement GetParentElem() { 
		return parentElem;
	}
	public List<XmiElement> childElemList() {
		return childElem;
	}
	
	public void addChildElement(XmiElement elem) {
		childElem.add(elem);
	}
	
	public void setParentElem(XmiElement elem) {
		if (parentElem == null) {
			parentElem = elem;
		}
	}
	public String getElementId() {
		return elem_xmi_id;
	}
}
