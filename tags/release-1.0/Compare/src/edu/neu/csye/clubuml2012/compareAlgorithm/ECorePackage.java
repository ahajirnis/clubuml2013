package edu.neu.csye.clubuml2012.compareAlgorithm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * Class for EPackages
 * @author Shoeb Shaikh
 *
 */
public class ECorePackage {
	private EPackage ePackage;
	private String name;

	public ECorePackage(EList<EObject> diagram) {
		if (diagram.get(0) instanceof EPackage) {
			ePackage = (EPackage)diagram.get(0);

			name = ePackage.getName().toString();
		}
	}
	
	
	public EPackage getePackage() {
		return ePackage;
	}


	public void setePackage(EPackage ePackage) {
		this.ePackage = ePackage;
	}


	public String getName() {
		return name;
	}


	public void writeStatement() {
		// Write to report
	}
}
