package controller.comparer.xmi;

import java.util.ArrayList;

public class XmiAssociationElement extends XmiBaseElement {

	private final static int MEMBER_END_SIZE = 2;
	
	private ArrayList<XmiMemberEndElement> memberEnd = new ArrayList<XmiMemberEndElement>(MEMBER_END_SIZE);
	
	public XmiAssociationElement(String id, String name, String type) {
		super(id, name, type, "");
	}

	public void addMemberEnd(XmiMemberEndElement element) {
		// if at maximum size, remove first element
		if (memberEnd.size() == MEMBER_END_SIZE) {
			memberEnd.remove(0);
		}
		
		// add element at the end
		memberEnd.add(element);
	}
}
