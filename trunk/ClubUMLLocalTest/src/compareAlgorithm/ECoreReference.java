package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;

/**
 * Class for EReferences
 * 
 * @author Shoeb Shaikh
 * 
 */
public class ECoreReference {
	private EList<EReference> refList;
	private EReference eReference;
	private String name;

	/**
	 * Constructor.
	 * 
	 * @param eClass
	 */
	public ECoreReference(ECoreClass eClass) {
		refList = eClass.getEClass().getEAllReferences();
		eReference = null;
	}

	/**
	 * Gets the EReference object
	 * 
	 * @param index
	 *            - position of object to extract from refList
	 * @return EReference object
	 */
	public EReference getEReference(int index) {
		setEReference(index);
		return refList.get(index);
	}

	/**
	 * Sets the EReference and name
	 * 
	 * @param index
	 *            position of desired element in refList
	 */
	public void setEReference(int index) {
		eReference = refList.get(index);
		name = eReference.getEType().getName().toString();
	}
}
