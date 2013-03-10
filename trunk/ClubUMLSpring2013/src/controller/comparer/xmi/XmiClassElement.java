package controller.comparer.xmi;

import java.util.ArrayList;

public class XmiClassElement extends XmiBaseElement{

	private ArrayList<XmiOperationElement> operations;
	private ArrayList<XmiAttributeElement> attributes;
	private ArrayList<XmiClassElement> nestedClass;
	private ArrayList<XmiGeneralizationElement> generalization;

	public XmiClassElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);
		
		this.operations = new ArrayList<XmiOperationElement>();
		this.attributes = new ArrayList<XmiAttributeElement>();
		this.nestedClass = new ArrayList<XmiClassElement>();
		this.generalization = new ArrayList<XmiGeneralizationElement>();
	}

	public ArrayList<XmiOperationElement> getOperations() {
		return operations;
	}

	public void addOperation(XmiOperationElement operation) {
		this.operations.add(operation);
	}

	public ArrayList<XmiAttributeElement> getAttributes() {
		return attributes;
	}

	public void addAttribute(XmiAttributeElement attribute) {
		this.attributes.add(attribute);
	}

	public ArrayList<XmiClassElement> getNestedClass() {
		return nestedClass;
	}

	public void addNestedClass(XmiClassElement nestedClass) {
		this.nestedClass.add(nestedClass);
	}
	
	@Override
	public String toString() {
		return "Class: " + super.toString();
	}

	public ArrayList<XmiGeneralizationElement> getGeneralization() {
		return generalization;
	}

	public void addGeneralization(XmiGeneralizationElement generalization) {
		this.generalization.add(generalization);
	}
}
