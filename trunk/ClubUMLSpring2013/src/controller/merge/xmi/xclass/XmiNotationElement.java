package controller.merge.xmi.xclass;

import java.util.ArrayList;

import uml2parser.ModelFileInfo;

public class XmiNotationElement {

	public static final int TYPE_CLASS = 1;
	public static final int TYPE_ATTRIBUTE = TYPE_CLASS + 1;
	public static final int TYPE_OPERATION = TYPE_ATTRIBUTE + 1;
	public static final int TYPE_GENERALIZATION = TYPE_OPERATION + 1;
	public static final int TYPE_ASSOCIATION = TYPE_GENERALIZATION + 1;
	public static final int TYPE_UML_ELEMENT = TYPE_ASSOCIATION + 1;
	
	private String Id;
	private ModelFileInfo notation;
	private String newId;
	
	private int type;
	private ArrayList<XmiNotationElement> elementList = new ArrayList<XmiNotationElement>();
	
	// For associations
	private String target;
	private String source;
	
	public XmiNotationElement(String Id, String newId, ModelFileInfo notation, int type) {
		setId(Id);
		setNewId(newId);
		setNotation(notation);
		setType(type);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the notation
	 */
	public ModelFileInfo getNotation() {
		return notation;
	}

	/**
	 * @param notation the notation to set
	 */
	public void setNotation(ModelFileInfo notation) {
		this.notation = notation;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the newId
	 */
	public String getNewId() {
		return newId;
	}

	/**
	 * @param newId the newId to set
	 */
	public void setNewId(String newId) {
		this.newId = newId;
	}

	/**
	 * @return the elementList
	 */
	public ArrayList<XmiNotationElement> getElementList() {
		return elementList;
	}

	/**
	 * @param element to add to list
	 */
	public void addElement(XmiNotationElement element) {
		this.elementList.add(element);
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
}
