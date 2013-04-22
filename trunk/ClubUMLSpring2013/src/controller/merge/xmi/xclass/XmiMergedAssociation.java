package controller.merge.xmi.xclass;

import controller.comparer.xmi.XmiAssociationElement;

public class XmiMergedAssociation {

	private XmiAssociationElement associationElement;
	
	// This Id will be used for the Member End's type field
	private String class1Id;
	private String class2Id;
	
	// Get class name from the merged object new name field
	private String class1Name;
	private String class2Name;
	
	// Diagram
	private int diagramNum = 0;
	
	public XmiMergedAssociation(XmiAssociationElement association, String class1Id, String class2Id, String class1Name, String class2Name, int diagram) {
		this.setAssociationElement(association);
		this.setClass1Id(class1Id);
		this.setClass2Id(class2Id);
		this.setClass1Name(class1Name);
		this.setClass2Name(class2Name);
		this.setDiagramnum(diagram);
	}
	
	/**
	 * @return the associationElement
	 */
	public XmiAssociationElement getAssociationElement() {
		return associationElement;
	}
	/**
	 * @return the class1Id
	 */
	public String getClass1Id() {
		return class1Id;
	}
	/**
	 * @return the class2Id
	 */
	public String getClass2Id() {
		return class2Id;
	}
	/**
	 * @return the class1Name
	 */
	public String getClass1Name() {
		return class1Name;
	}
	/**
	 * @return the class2Name
	 */
	public String getClass2Name() {
		return class2Name;
	}
	/**
	 * @param associationElement the associationElement to set
	 */
	public void setAssociationElement(XmiAssociationElement associationElement) {
		this.associationElement = associationElement;
	}
	/**
	 * @param class1Id the class1Id to set
	 */
	public void setClass1Id(String class1Id) {
		this.class1Id = class1Id;
	}
	/**
	 * @param class2Id the class2Id to set
	 */
	public void setClass2Id(String class2Id) {
		this.class2Id = class2Id;
	}
	/**
	 * @param class1Name the class1Name to set
	 */
	public void setClass1Name(String class1Name) {
		this.class1Name = class1Name;
	}
	/**
	 * @param class2Name the class2Name to set
	 */
	public void setClass2Name(String class2Name) {
		this.class2Name = class2Name;
	}

	/**
	 * @return the diagramnum
	 */
	public int getDiagramnum() {
		return diagramNum;
	}

	/**
	 * @param diagramnum the diagramnum to set
	 */
	public void setDiagramnum(int diagramnum) {
		this.diagramNum = diagramnum;
	}

	

}
