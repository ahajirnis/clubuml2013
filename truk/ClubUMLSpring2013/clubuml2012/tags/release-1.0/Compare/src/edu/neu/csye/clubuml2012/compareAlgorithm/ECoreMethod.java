package edu.neu.csye.clubuml2012.compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
/**
 * Class for EMethods
 * @author Shoeb Shaikh
 *
 */
public class ECoreMethod {
	private EList<EOperation> methodList;
	private EOperation method;
	private String name;
	private EClassifier dataType;
	
	public ECoreMethod(ECoreClass eClass) {
		methodList = eClass.getEClass().getEAllOperations();
		method = null;
		name = "";
	}
	
	public EOperation getEMethod(int index) {
		setEMethod(index);
		return methodList.get(index);
	}
	
	public EOperation getEMethod() {
		if (method != null) 
			return method;
		return null;
	}
	
	public void setEMethod(int index) {
		method = methodList.get(index);
		name = method.getName();
		dataType = method.getEType();
	}
	
	public String getName() {
		return this.name;
	}
}

