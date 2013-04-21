package controller.merge.xmi.xclass;

import java.util.ArrayList;

import controller.comparer.xmi.XmiAssociationElement;
import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiGeneralizationElement;
import controller.comparer.xmi.XmiOperationElement;

public class XmiMergedClass {
	
	// New merged class name
	private String newName;
	
	// Reference to class elements
	private XmiClassElement class1;
	private XmiClassElement class2;
	
	// Class 1 elements to keep in final class
	private ArrayList<XmiAttributeElement> attributes1 = new ArrayList<XmiAttributeElement>();
	private ArrayList<XmiOperationElement> operations1 = new ArrayList<XmiOperationElement>();
	private ArrayList<XmiGeneralizationElement> generalizations1 = new ArrayList<XmiGeneralizationElement>();
	private ArrayList<XmiClassElement> nestedClasses1 = new ArrayList<XmiClassElement>();
	private ArrayList<XmiAssociationElement> associations1 = new ArrayList<XmiAssociationElement>();
	
	// Class 2 elements to keep in final class
	private ArrayList<XmiAttributeElement> attributes2 = new ArrayList<XmiAttributeElement>();
	private ArrayList<XmiOperationElement> operations2 = new ArrayList<XmiOperationElement>();
	private ArrayList<XmiGeneralizationElement> generalizations2 = new ArrayList<XmiGeneralizationElement>();
	private ArrayList<XmiClassElement> nestedClasses2 = new ArrayList<XmiClassElement>();
	private ArrayList<XmiAssociationElement> associations2 = new ArrayList<XmiAssociationElement>();
	
	/**
	 * Default constructor
	 */
	public XmiMergedClass() {}
	
	/**
	 * constructor used when class1 and class2 are perfect matches in terms of value.
	 * @param class1
	 * @param class2
	 */
	public XmiMergedClass(XmiClassElement class1, XmiClassElement class2) {
		this.class1 = class1;
		this.class2 = class2;
		
		// Only need to set one set of variables since classes are equal
		this.attributes1 = class1.getAttributes();
		
		this.operations1 = class1.getOperations();
		
		this.nestedClasses1 = class1.getNestedClass();
		
		this.newName = class1.getName();
	}
	
	/**
	 * @return the class1
	 */
	public XmiClassElement getClass1() {
		return class1;
	}
	/**
	 * @param class1 the class1 to set
	 */
	public void setClass1(XmiClassElement class1) {
		this.class1 = class1;
	}
	/**
	 * @return the class2
	 */
	public XmiClassElement getClass2() {
		return class2;
	}
	/**
	 * @param class2 the class2 to set
	 */
	public void setClass2(XmiClassElement class2) {
		this.class2 = class2;
	}
	/**
	 * @return the attributes
	 */
	public ArrayList<XmiAttributeElement> getAttributes() {
		return attributes1;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(ArrayList<XmiAttributeElement> attributes) {
		this.attributes1 = attributes;
	}
	/**
	 * @return the operations
	 */
	public ArrayList<XmiOperationElement> getOperations() {
		return operations1;
	}
	/**
	 * @param operations the operations to set
	 */
	public void setOperations(ArrayList<XmiOperationElement> operations) {
		this.operations1 = operations;
	}
	/**
	 * @return the generalizations
	 */
	public ArrayList<XmiGeneralizationElement> getGeneralizations() {
		return generalizations1;
	}
	/**
	 * @param generalizations the generalizations to set
	 */
	public void setGeneralizations(ArrayList<XmiGeneralizationElement> generalizations) {
		this.generalizations1 = generalizations;
	}
	/**
	 * @return the associations
	 */
	public ArrayList<XmiAssociationElement> getAssociations() {
		return associations1;
	}
	/**
	 * @param associations the associations to set
	 */
	public void setAssociations(ArrayList<XmiAssociationElement> associations) {
		this.associations1 = associations;
	}
	/**
	 * @return the attributes2
	 */
	public ArrayList<XmiAttributeElement> getAttributes2() {
		return attributes2;
	}
	/**
	 * @param attributes2 the attributes2 to set
	 */
	public void setAttributes2(ArrayList<XmiAttributeElement> attributes2) {
		this.attributes2 = attributes2;
	}
	/**
	 * @return the operations2
	 */
	public ArrayList<XmiOperationElement> getOperations2() {
		return operations2;
	}
	/**
	 * @param operations2 the operations2 to set
	 */
	public void setOperations2(ArrayList<XmiOperationElement> operations2) {
		this.operations2 = operations2;
	}
	/**
	 * @return the generalizations2
	 */
	public ArrayList<XmiGeneralizationElement> getGeneralizations2() {
		return generalizations2;
	}
	/**
	 * @param generalizations2 the generalizations2 to set
	 */
	public void setGeneralizations2(ArrayList<XmiGeneralizationElement> generalizations2) {
		this.generalizations2 = generalizations2;
	}
	/**
	 * @return the associations2
	 */
	public ArrayList<XmiAssociationElement> getAssociations2() {
		return associations2;
	}
	/**
	 * @param associations2 the associations2 to set
	 */
	public void setAssociations2(ArrayList<XmiAssociationElement> associations2) {
		this.associations2 = associations2;
	}
	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}
	/**
	 * @param newName the newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	@Override
	public String toString() {
		if (class1 != null && class2 != null) {
			return class1.toString() + " merged with " + class2.toString();
		} else if (class1 != null) {
			return class1.toString();
		} else if (class2 != null){
			return class2.toString();
		} else {
			return "No classes merged";
		}
	}
}
