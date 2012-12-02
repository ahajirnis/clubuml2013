package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;

/**
 * Class for EReferences
 * @author Shoeb Shaikh
 *
 */
public class ECoreReference {
	private EList<EReference> refList;
	private EReference eReference;
	private String lowerBound;
	private String upperBound;
	private String name;

	public ECoreReference(ECoreClass eClass) {
		refList = eClass.getEClass().getEAllReferences();
		eReference = null;
	}

	public EReference getEReference(int index) {
		setEReference(index);
		return refList.get(index);
	}

	public void setEReference(int index) {
		eReference = refList.get(index);
		name = eReference.getEType().getName().toString();
	}
}
