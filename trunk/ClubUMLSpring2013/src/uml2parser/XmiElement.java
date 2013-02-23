package uml2parser;
/**
 *  @author : Prashant Shukla
 *  This is class defines one element in the XMI file
 */
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import java.util.Stack;

public class XmiElement {
	
	final static String XMI_ID = "xmi:id" ;
	
	/**
	 * Element name
	 */
	private String elementName; 
	/**
	 * This flag is only used when we are trying to determine 
	 * if the ID is active in UML file or if it is an unused(inactive)
	 * entry. 
	 */
	private boolean foundmatch;
	/**
	 * This elements Parent element
	 */
	private XmiElement parentElem;
	/**
	 * List of child elements associated with this element 
	 */
	private List<XmiElement> childElem;
	/**
	 * List of attributes associated with this element
	 */
	private List <Attribute> attr;
	/**
	 * Value of the xmi:id attribute. Can be null if the attribute xmi:id is 
	 * not present
	 */
	private String elem_xmi_id;

	/**
	 *  Make sure no one calls the default constructor
	 */
	private XmiElement() {}
	/**
	 * Constructor 
	 * @param name : name of the element  
	 * @param attrs : Attribute associated with this element. 
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
	 *  List of Attributes that are associated with this element. This 
	 *  list is populated when we parse the XMI file. 
	 * @return the list of attributes associated with this element. 
	 */
	public List<Attribute> getAttrib()  { 
		return attr; 
	}
	/**
	 * This method returns the parent Element to the Element
	 * @return parent element. null if this element doesn't have 
	 *         any Parent element 
	 */
	public XmiElement getParentElem() { 
		return parentElem;
	}
	/**
	 * This method returns the list of ChildElements
	 * @return List of XmiElement this element is a parent of.
	 */
	public List<XmiElement> getChildElemList() {
		return childElem;
	}
	/**
	 * We are building a tree and this points to all the child 
	 * elements this Element has. ChildElem is a ArrayList and 
	 * we add all the child Element as we parse the XMI file 
	 * (Please refer to ParseXmi.java) 
	 * @param elem
	 */
	public void addChildElement(XmiElement elem) {
		childElem.add(elem);
	}
	/**
	 *  We are building a tree and this points to the parent
	 *  Element of this element. We make the determination of parent
	 *  elements while parsing the XMI file. (refer to ParseXmi.java)
	 * @param elem : The element that is parent of this Element
	 */
	public void setParentElem(XmiElement elem) {
		if (parentElem == null) {
			parentElem = elem;
		}
	}
	/**
	 * Return the value of xmi:id attribute. This can be null
	 * @return This returns null if the xmi:id attribute is not present
	 *         and return the xmi:id value if this attribute is present 
	 */
	public String getElementId() {
		return elem_xmi_id;
	}
	/**
	 * Returns the name of the Element
	 * @return The name of the element 
	 */
	public String getElementName() { 
		return elementName;
	}
	/**
	 *  The problem with Papyrus is that keeps the elements in the UML diagram even though
	 *  they have been removed. So in order to determine the active element we first the 
	 *  notation file and then parse the UML diagram. After we find the active Elements
	 *  we mark with the flag found match is true. 
	 * @param val - Indicating that a match was found
	 */
	public void setFoundMatch(boolean val) {
		foundmatch = val;
	}
	/**
	 * Return if the element is active or not. This only applies to Elements that we parse
	 * in the .UML file. This flag doesn't apply to the .notation file 
	 * @return
	 */
	public boolean getFoundMatch() {
		return foundmatch;
	}
	
	public String getAttributeValue(String attribName) {
		String temp = null;
		for (int k = 0; k < attr.size(); k++) {
			if (attr.get(k).getName().equals(attribName)) {
				temp = attr.get(k).getValue();
				break;
			}
		}
		return temp;
	}
	
}
