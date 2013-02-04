package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * Class for EPackages
 * 
 * @author Shoeb Shaikh
 * 
 */
public class ECorePackage {
	private EPackage ePackage;
	private String name;

	/**
	 * Constructor.
	 * 
	 * @param diagram to extract the EPackage
	 */
	public ECorePackage(EList<EObject> diagram) {
		if (diagram.get(0) instanceof EPackage) {
			ePackage = (EPackage) diagram.get(0);
			name = ePackage.getName().toString();
		}
	}

	/**
	 * Gets the EPackage
	 * 
	 * @return EPackage object
	 */
	public EPackage getePackage() {
		return ePackage;
	}

	/**
	 * Sets the ePackage
	 * 
	 * @param ePackage
	 *            object to store
	 */
	public void setePackage(EPackage ePackage) {
		this.ePackage = ePackage;
	}

	/**
	 * Getes the EPackage name
	 * @return EPackage name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * No operation (May remove)
	 */
	public void writeStatement() {
		// Write to report
	}
}
