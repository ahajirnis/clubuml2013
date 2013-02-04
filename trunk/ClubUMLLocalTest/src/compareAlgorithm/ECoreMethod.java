package compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;

/**
 * Class for EMethods
 * 
 * @author Shoeb Shaikh
 * 
 */
public class ECoreMethod {
	private EList<EOperation> methodList;
	private EOperation method;
	private String name;
	private EClassifier dataType;

	/**
	 * Constructor.
	 * 
	 * @param eClass
	 *            used to extract all operations.
	 */
	public ECoreMethod(ECoreClass eClass) {
		methodList = eClass.getEClass().getEAllOperations();
		method = null;
		name = "";
	}

	/**
	 * Get an operation from method list at position index.
	 * 
	 * @param index
	 *            position of desired operation
	 * @return EOperation object
	 */
	public EOperation getEMethod(int index) {
		setEMethod(index);
		return methodList.get(index);
	}

	/**
	 * Returns the active EOperation object.
	 * 
	 * @return EOperation object or null if none exist
	 */
	public EOperation getEMethod() {
		return method;

	}

	/**
	 * Sets an active EOperation object to an element in methodList at position
	 * index. No operation if methodList is not defined.
	 * 
	 * @param index
	 *            position in methodList
	 */
	public void setEMethod(int index) {
		if (methodList != null) {
			method = methodList.get(index);
			name = method.getName();
			dataType = method.getEType();
		}
	}

	/**
	 * Returns the name of the active EOperation object.
	 * 
	 * @return active EOperation name
	 */
	public String getName() {
		return this.name;
	}
}
