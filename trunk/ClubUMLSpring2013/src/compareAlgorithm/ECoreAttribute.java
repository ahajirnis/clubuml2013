package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;

/**
 * Class for EAttributes
 * 
 * @author Shoeb Shaikh
 * 
 */
public class ECoreAttribute {
	private EList<EAttribute> attributeList;
	private EAttribute eAttribute;
	private String name;
	private EClassifier dataType;

	/**
	 * Constructors.
	 * 
	 * @param eClass
	 *            used to extract all attributes.
	 */
	public ECoreAttribute(ECoreClass eClass) {
		attributeList = eClass.getEClass().getEAllAttributes();
		eAttribute = null;
	}

	/**
	 * Gets an attribute from attribute list at position index
	 * 
	 * @param index
	 *            position of desired attribute
	 * @return EAttribute object
	 */
	public EAttribute getEAttribute(int index) {
		setEAttribute(index);
		return attributeList.get(index);
	}

	/**
	 * Returns the active EAttribute object.
	 * 
	 * @return EAttribute object or null if none exist
	 */
	public EAttribute getEAttribute() {
		return eAttribute;
	}

	/**
	 * Sets an active EAttribute object to an element in attributeList at
	 * position index. No operation if attributeList is not defined.
	 * 
	 * @param index
	 *            position in attributeList
	 */
	public void setEAttribute(int index) {
		if (attributeList != null) {
			eAttribute = attributeList.get(index);
			name = eAttribute.getName();
			dataType = eAttribute.getEType();
		}
	}

	/**
	 * Returns the name of the active EAttribute object.
	 * 
	 * @return active EAttribute's name
	 */
	public String getName() {
		return name;
	}
}
