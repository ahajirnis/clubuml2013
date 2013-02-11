package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;

/**
 * Class for EAttributes
 * @author Shoeb Shaikh
 *
 */
public class ECoreAttribute {
	private EList<EAttribute> attributeList;
	private EAttribute eAttribute;
	private String name;
	private EClassifier dataType;

	public ECoreAttribute(ECoreClass eClass) {
		attributeList = eClass.getEClass().getEAllAttributes();
		eAttribute = null;
	}

	public EAttribute getEAttribute(int index) {
		setEAttribute(index);
		return attributeList.get(index);
	}

	public EAttribute getEAttribute() {
		if (eAttribute != null)
			return eAttribute;
		return eAttribute;
	}

	public void setEAttribute(int index) {
		eAttribute = attributeList.get(index);
		name = eAttribute.getName();
		dataType = eAttribute.getEType();
	}

	public String getName() {
		return name;
	}
}
