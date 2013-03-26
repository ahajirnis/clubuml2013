package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;

/**
 * Class for EClass
 * 
 * @author Shoeb Shaikh
 * 
 */
public class ECoreClass {
	private EList<EClassifier> classList;
	private EClass eClass;
	private boolean abstrct;
	private boolean intrfce;

	/**
	 * Constructors.
	 * 
	 * @param ePackage
	 *            used to extract all classifiers.
	 */
	ECoreClass(ECorePackage ePackage) {
		classList = ePackage.getePackage().getEClassifiers();
		eClass = null;
		abstrct = false;
		intrfce = false;

	}

	/**
	 * Gets a classifier from classifier list at position index
	 * 
	 * @param index
	 *            position of desired classifier
	 * @return EClass object
	 */
	public EClass getEClass(int index) {
		setEClass(index);
		return (EClass) classList.get(index);
	}

	/**
	 * Returns the active EClass object.
	 * 
	 * @return EClass object or null if none exist
	 */
	public EClass getEClass() {
		return eClass;
	}

	/**
	 * Sets an active EClass object to an element in eClass at position index.
	 * No operation if classList is not defined.
	 * 
	 * @param index
	 *            position in classList
	 */
	public void setEClass(int index) {
		if (classList != null) {
			eClass = (EClass) classList.get(index);
			abstrct = eClass.isAbstract();
			intrfce = eClass.isInterface();
		}
	}

	/**
	 * Returns if EClass is an abstract
	 * 
	 * @return boolean
	 */
	public boolean isAbstrct() {
		return abstrct;
	}

	/**
	 * Returns if EClass is an interface
	 * 
	 * @return boolean
	 */
	public boolean isIntrfce() {
		return intrfce;
	}

	/**
	 * Returns the size of classList
	 * 
	 * @return size of classList
	 */
	public int size() {
		return classList.size();
	}
}
