package controller.comparer.xmi;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.xml.sax.Attributes;

import uml2parser.XmiElement;

public class XmiCompareElement extends XmiElement {

	private String umlType;
	private String visibility;
	
	public XmiCompareElement(String name, Attributes attrs) {
		super(name, attrs);
		// TODO Auto-generated constructor stub
	}
}
