package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;

/**
 * Class for EClass
 * @author Shoeb Shaikh
 *
 */
public class ECoreClass {
	private EList<EClassifier> classList;
	private EClass eClass;
	private boolean abstrct;
	private boolean intrfce;

	ECoreClass(ECorePackage ePackage) {
		classList = ePackage.getePackage().getEClassifiers();
		eClass = null;
		abstrct = false;
		intrfce = false;

	}
	public EClass getEClass(int index) {
		setEClass(index);
		return (EClass)classList.get(index);
	}

	public EClass getEClass() {
		if (eClass != null)
			return eClass;
		return null;
	}

	public void setEClass(int index) {
		eClass = (EClass)classList.get(index);
		abstrct = eClass.isAbstract();
		intrfce = eClass.isInterface();
	}

	public boolean isAbstrct() {
		return abstrct;
	}

	public boolean isIntrfce() {
		return intrfce;
	}

	public int size() {
		return classList.size();
	}
}
