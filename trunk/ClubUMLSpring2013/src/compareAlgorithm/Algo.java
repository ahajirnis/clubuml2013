package compareAlgorithm;

import java.util.ArrayList;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * Algorithm class that compares all the features of a class diagram.
 * 
 * @author Shoeb Shaikh
 * 
 * 
 */
public class Algo {

	private EList<EObject> firstModel; // ECore objects
	private EList<EObject> secondModel;

	private ECorePackage ePackage1; // Packages
	private ECorePackage ePackage2;

	private ECoreClass eClass1; // Classes
	private ECoreClass eClass2;

	private ArrayList<String> matchedClasses; // Records matched classes
	private ArrayList<String> comparedClasses; // Records compared classes
	
	
	
	//private ArrayList<String> similarClasses;
	//private ArrayList<String> aloneClasses;
	

	Report report; // for writing report to the pdf file

	/**
	 * Constructor to initialize necessary class members.
	 * 
	 * @param firstModel
	 *            first Ecore model to compare
	 * @param secondModel
	 *            second ECore model to compare
	 * @param reportFile
	 *            path to store report
	 */
	public Algo(EList<EObject> firstModel, EList<EObject> secondModel,
			String reportFile) {
		this.firstModel = firstModel;
		this.secondModel = secondModel;
		report = new Report(reportFile);
		matchedClasses = new ArrayList<String>();
		comparedClasses = new ArrayList<String>();
	}

	/**
	 * Main dispatch function for comparing the features of a class.
	 */
	public void compare() {
		report.addToReport("Begin Comparison....");
		if (comparePackage()) {
			eClass1 = new ECoreClass(ePackage1);
			eClass2 = new ECoreClass(ePackage2);

			// Comparing classes entirely including the details
			compareClasses(eClass1, eClass2);

			// Reporting unmatch classes
			reportUnmatchedClasses();

		} else {
			report.addToReport("Checking individual classes due to absence of packages");
			for (int i = 0; i < firstModel.size(); i++) {
				EClass firstClass = (EClass) firstModel.get(i);
				for (int j = 0; j < secondModel.size(); j++) {
					EClass secondClass = (EClass) secondModel.get(j);
					compareUnPackedClasses(firstClass, secondClass);
				}
			}

			// Reporting unmatch classes
			reportUnmatchedClasses();
		}

		//********************************For testing matchedClasses, plz keep, Dong Guo
		for(int i = 0; i < matchedClasses.size(); i++){
			report.addToReport(i + ": " + matchedClasses.get(i));
		}
		//**********************************************************
		
		// Close the report
		report.finalize();

	}

	/**
	 * Checks packages and returns if the packages are similar or not.
	 * 
	 * @return true if similar else false
	 */
	private boolean comparePackage() {
		// report.startRoutine("packages");
		try {
			if (checkPackages()) {
				ePackage1 = new ECorePackage(firstModel);
				ePackage2 = new ECorePackage(secondModel);

				if (packageNameSimilar(ePackage1.getName(), ePackage2.getName()) > 0) {

					report.addToReport("Packages match : "
							+ ePackage1.getName() + " and "
							+ ePackage2.getName());
					return true;
				} else {
					report.addToReport("Packages don't match\n"
							+ "\nName of first Package : "
							+ ePackage1.getName()
							+ "\n Name of second Package : "
							+ ePackage1.getName());

					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Packages");
		}
		return false;
	}

	/**
	 * Checking if files are in proper format
	 * 
	 * @return true if files are valid, false otherwise
	 */
	private boolean checkPackages() {
		if (firstModel.get(0) instanceof EPackage) {
			if (secondModel.get(0) instanceof EPackage) {

			} else {
				report.addToReport("Could not find packages in second model");
				return false;
			}
		} else {
			report.addToReport("Could not find packages in first model");
			return false;
		}
		return true;
	}

	/**
	 * Compares package names.
	 * 
	 * @param firstName
	 *            first name to compare
	 * @param secondName
	 *            second name to compare
	 * @return true if names match else false
	 */
	private int packageNameSimilar(String firstName, String secondName) {
		return this.compareNames(firstName, secondName);
	}

	/**
	 * General method for comparing any pair of names.
	 * 
	 * @param name1
	 *            - first name to compare
	 * @param name2
	 *            - second name to compare
	 * @return PERFECT_MATCH if same names
	 * @return PARTIAL_MATCH if names are similar based on soundex comparison
	 * @return NOT_MATCH if names not matched at all
	 */
	private int compareNames(String name1, String name2) {
		try {
			if (new Soundex().difference(name1, name2) == 4) {
				return Constants.PERFECT_MATCH;
			}
			if (new Soundex().difference(name1, name2) > 2) {
				return Constants.PARTIAL_MATCH;
			}
		} catch (EncoderException e) {
			return Constants.NOT_MATCH;
		}
		return Constants.NOT_MATCH;
	}

	/**
	 * Compare package information. If package are the same, compare class
	 * details. If package are not the same, compare structure details. Adds
	 * comments to report.
	 * 
	 * @param class1
	 *            first class to compare
	 * @param class2
	 *            second class to compare
	 */
	private void compareUnPackedClasses(EClass class1, EClass class2) {
		// report.startRoutine("classes");
		try {

			// compare class names by soundex
			int comparedValue = compareNames(class1.getName(), class2.getName());

			if (comparedValue == Constants.PERFECT_MATCH) {
				// add classes to the list of matched classes
				matchedClasses.add(class1.getName());
				matchedClasses.add(class2.getName());

				// add to the report
				report.addToReport("Perfect Match : " + class1.getName()
						+ " : " + class2.getName());

				// send the classes for comparing details
				compareClassDetails(class1, class2);

			} else {
				// Pass the two classes for structural class comparison
				this.structuralComparison(class1, class2);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Unpacked Classes");
		}
	}

	/**
	 * Compares Classes. Finds perfect and partial match. Adds comments to
	 * report object.
	 * 
	 * @param firstEClass
	 *            first class to compare
	 * @param secondEClass
	 *            second class to compare
	 */
	private void compareClasses(ECoreClass firstEClass, ECoreClass secondEClass) {
		// report.startRoutine("classes");
		try {
			for (int x = 0; x < firstEClass.size(); x++) {
				// flag for determining whether a class is found
				boolean classFound = false;

				EClass class1 = firstEClass.getEClass(x);

				for (int y = 0; y < secondEClass.size(); y++) {
					EClass class2 = secondEClass.getEClass(y);

					// compare class names by soundex
					int comparedValue = compareNames(class1.getName(),
							class2.getName());

					if (comparedValue == Constants.PERFECT_MATCH) {
						// add classes to the list of matched classes
						matchedClasses.add(class1.getName());
						matchedClasses.add(class2.getName());

						// add to the report
						report.addToReport("Perfect Match : "
								+ class1.getName() + " : " + class2.getName());

						// send the classes for comparing details
						compareClassDetails(class1, class2);

						// set the flag
						classFound = true;
					} else {
						// Pass the two classes for structural class comparison
						this.structuralComparison(class1, class2);

						// set the flag
						classFound = true;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Classes");
		}
	}

	/**
	 * Compare classes details after classes match.
	 * 
	 * @param cls1
	 *            first class to compare
	 * @param cls2
	 *            second class to compare
	 */
	private void compareClassDetails(EClass cls1, EClass cls2) {
		compareSuperClass(cls1, cls2);
		compareAttributes(cls1, cls2);
		compareMethods(cls1, cls2);
		compareReferences(cls1, cls2);
		
		/*
		 * If the classes match each other,
		 * they will be added into matchedClasses in compareClasses()
		 * These two lines are duplicated.
		 * 
		 * Dong Guo 02/17/2013
		 */
		//matchedClasses.add(cls1.getName());
		//matchedClasses.add(cls2.getName());
	}

	/**
	 * Compares super classes of designated classes and adds comments to report
	 * object.
	 * 
	 * @param cls1
	 *            first class to compare super class
	 * @param cls2
	 *            second class to compare super class
	 */
	private void compareSuperClass(EClass cls1, EClass cls2) {
		// report.startRoutine("super classes");
		try {
			// Get super class list from classes
			EList<EClass> superClassList1 = cls1.getESuperTypes();
			EList<EClass> superClassList2 = cls2.getESuperTypes();

			// Select classes one by one and compare
			for (int i = 0; i < superClassList1.size(); i++) {
				for (int j = 0; j < superClassList2.size(); j++) {
					if (compareNames(superClassList1.get(i).getName(),
							superClassList2.get(j).getName()) > 0) {
						report.addToReport("Super Classes matched : "
								+ "first " + superClassList1.get(i).getName()
								+ " with " + "second "
								+ superClassList2.get(i).getName());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Super classes");
		}
	}

	/**
	 * Compare class attributes and adds comments to report object.
	 * 
	 * @param cls1
	 *            first class containing attribute to compare
	 * @param cls2
	 *            second class containing attributes to compare
	 */
	private void compareAttributes(EClass cls1, EClass cls2) {
		// report.startRoutine("attributes");
		try {
			EList<EAttribute> attrList1 = cls1.getEAttributes();
			EList<EAttribute> attrList2 = cls2.getEAttributes();

			for (int i = 0; i < attrList1.size(); i++) {
				// flag set if attribute found
				boolean attrFound = false;

				for (int j = 0; j < attrList2.size(); j++) {
					if (compareNames(attrList1.get(i).getName(),
							attrList2.get(j).getName()) > 1) {
						// set the flag
						attrFound = true;
						report.addToReport("  Attributes name matches : "
								+ "first " + attrList1.get(i).getName()
								+ " with " + "second "
								+ attrList2.get(i).getName());

						// compare attribute type
						if (compareETypes(attrList1.get(i).getEType(),
								attrList2.get(j).getEType())) {
							report.addToReport("  Attributes type matches : "
									+ "first "
									+ attrList1.get(j).getEType().getName()
									+ " with " + "second "
									+ attrList2.get(j).getEType().getName());
						}
					}
				}
				// add to report the unmatched attribute
				if (!attrFound) {
					report.addToReport("Attributes from first that don't match : "
							+ attrList1.get(i).getName());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Attributes");
		}
	}

	/**
	 * Compare methods by method name and type and adds comments to report
	 * object.
	 * 
	 * @param cls1
	 *            first class containing operations to compare
	 * @param cls2
	 *            second class containing operations to compare
	 */
	private void compareMethods(EClass cls1, EClass cls2) {
		// report.startRoutine("methods");
		try {
			EList<EOperation> methodList1 = cls1.getEAllOperations();
			EList<EOperation> methodList2 = cls2.getEAllOperations();

			for (int i = 0; i < methodList1.size(); i++) {
				for (int j = 0; j < methodList2.size(); j++) {
					if (compareNames(methodList1.get(i).getName(), methodList2
							.get(j).getName()) > 0) {
						report.addToReport("Methods name matches : " + "first "
								+ methodList1.get(i).getName() + " with "
								+ "second " + methodList2.get(i).getName());

						if (this.compareETypes(methodList1.get(i).getEType(),
								methodList2.get(j).getEType())) {
							report.addToReport("Methods return type matches : "
									+ "first "
									+ methodList1.get(i).getEType().getName()
									+ " with " + "second "
									+ methodList2.get(i).getEType().getName());
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Methods");
		}
	}

	/**
	 * Compare references of a class. Adds comments to report object if
	 * references match or not.
	 * 
	 * @param cls1
	 *            first class containing references to compare
	 * @param cls2
	 *            second class containing references to compare
	 */
	private void compareReferences(EClass cls1, EClass cls2) {
		// report.addToReport("references");
		try {
			EList<EReference> refList1 = cls1.getEAllReferences();
			EList<EReference> refList2 = cls2.getEAllReferences();

			for (int i = 0; i < refList1.size(); i++) {
				// flag set if references match
				boolean refFound = false;

				for (int j = 0; j < refList2.size(); j++) {
					if (this.compareNames(refList1.get(i).getEType().getName(),
							refList2.get(j).getEType().getName()) > 0) {
						// add to report
						report.addToReport("Reference Name match : "
								+ refList1.get(i).getName());

						String refName1 = refList1.get(i).getEType().getName()
								.toString();
						String refName2 = refList2.get(j).getEType().getName()
								.toString();

						// compare opposite reference
						if (compareNames(refName1, refName2) == 2) {
							report.addToReport("Opposite Reference match : "
									+ refName1);
							refFound = true;
						}
					}
				}
				if (!refFound) {
					report.addToReport("Reference from first diagram "
							+ refList1.get(i).getName() + " -->"
							+ refList1.get(i).getEType().getName()
							+ " not found in second diagram");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("References");
		}
	}

	/**
	 * Compare ETypes
	 * 
	 * @param etype1
	 *            first classifier to compare
	 * @param etype2
	 *            second classifier to compare
	 * @return true if equals else false
	 */
	private boolean compareETypes(EClassifier etype1, EClassifier etype2) {
		return etype1.equals(etype2);
	}

	/**
	 * Structurally compare the two classes ie reference attribute n methods
	 * 
	 * @param cls1
	 *            first class to compare
	 * @param cls2
	 *            second class to compare
	 */
	private void structuralComparison(EClass cls1, EClass cls2) {
		// report.startRoutine("structural class");
		try {
			// check if any of the class already in list of matched classes
			if (!(this.matchedClasses.contains(cls1.getName()) || this.matchedClasses
					.contains(cls2.getName()))) {
				report.addToReport("Comparing classes.." + cls1.getName()
						+ " : " + cls2.getName());

				// Check if structurally the similarity is greater than 50 %
				if (this.structAttrCompare(cls1, cls2) >= 0.5
						&& structMethodCompare(cls1, cls2) >= 0.5
						&& structRefCompare(cls1, cls2) >= 0.5) {
					report.addToReport("Structural Match " + cls1.getName()
							+ " : " + cls2.getName());
					
					/*
					 * set list of classes with different names but might be same 
					 * Dong Guo
					 * 
					 * similarClasses.add(cls1.getName());
					 * similarClasses.add(cls2.getName());
					 */
					
					this.compareClassDetails(cls1, cls2);
				} else {
					// Add the unmatched classes to the list of classes compared
					listComparedClasses(cls1, cls2);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Structural class");
		}
	}

	/**
	 * Compare attributes based on name.
	 * 
	 * @param cls1
	 *            first class containing attributes to compare
	 * @param cls2
	 *            second class containing attributes to compare
	 * @return float between 0 and 1. Percentage of attributes matching.
	 */
	private float structAttrCompare(EClass cls1, EClass cls2) {
		// report.startRoutine("structural attributes");
		try {
			EList<EAttribute> attrList1 = cls1.getEAttributes();
			EList<EAttribute> attrList2 = cls2.getEAttributes();

			// If attribute list of both classes is empty then return 1
			// This allows comparison based on method and reference similarities
			if (attrList1.size() == 0 && attrList2.size() == 0)
				return 1;

			float attrScore = 0;

			for (int i = 0; i < attrList1.size(); i++) {
				for (int j = 0; j < attrList2.size(); j++) {
					if (compareNames(attrList1.get(i).getName(),
							attrList2.get(j).getName()) == 2) {
						// debug code
						report.addToReport("Attribute match "
								+ attrList1.get(i).getName());
						attrScore += 1;
					}
				}
			}
			// If attribute list is not empty then return, percentage of the
			// matched attribute
			// In this case a value between 0 and 1
			if (attrList1.size() != 0)
				return attrScore / attrList1.size();

			// Return 0 if attribute list of class 1 is empty and other is not
			return attrScore;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Structural attributes");
		}
		return 0;
	}

	/**
	 * Compare methods structurally based on name and return type
	 * 
	 * @param cls1
	 *            first class containing operations to compare
	 * @param cls2
	 *            second class containing operations to compare
	 * @return float between 0 to 1. Percentage of operations matching.
	 */
	private float structMethodCompare(EClass cls1, EClass cls2) {
		// report.startRoutine("structural methods");
		try {
			EList<EOperation> methodList1 = cls1.getEAllOperations();
			EList<EOperation> methodList2 = cls2.getEAllOperations();

			if (methodList1.size() == 0 && methodList2.size() == 0)
				return 1;

			float methodScore = 0;

			for (int i = 0; i < methodList1.size(); i++) {
				for (int j = 0; j < methodList2.size(); j++) {
					// Compare method name
					if (this.compareNames(methodList1.get(i).getName(),
							methodList2.get(j).getName()) > 0) {
						// Compare method return type
						if (this.compareETypes(methodList1.get(i).getEType(),
								methodList2.get(j).getEType())) {
							// method params not compared
							report.addToReport("Method match "
									+ methodList1.get(i).getName());
							methodScore += 1;
						}
					}
				}
			}
			// Return a percentage of the matched methods
			// In this case a decimal value between 0 & 1
			if (methodList1.size() != 0)
				return methodScore / methodList1.size();

			// Return 0 if the method list of class1 is empty and other class is
			// not
			return methodScore;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Structural methods");
		}
		return 0;
	}

	/**
	 * Compare two classes structure
	 * 
	 * @param cls1
	 *            first class containing references to compare
	 * @param cls2
	 *            second class containing references to compare
	 * @return a float value between 1 and 0. 1 is perfect match and 0
	 *         completely different
	 * 
	 */
	private float structRefCompare(EClass cls1, EClass cls2) {
		// report.startRoutine("structural references");
		try {
			EList<EReference> refList1 = cls1.getEAllReferences();
			EList<EReference> refList2 = cls2.getEAllReferences();

			// If both classes do not hav any references return 1
			// This allows comparison to be continued on the basis of matched
			// attributes and methods
			if (refList1.size() == 0 && refList2.size() == 0)
				return 1;

			float refScore = 0;

			for (int i = 0; i < refList1.size(); i++) {
				for (int j = 0; j < refList2.size(); j++) {
					// Compare if the referenced class is same
					if (this.compareNames(refList1.get(i).getEType().getName(),
							refList2.get(j).getEType().getName()) > 0) {
						String refName1 = refList1.get(i).getEType().getName()
								.toString();
						String refName2 = refList2.get(j).getEType().getName()
								.toString();

						// Check if the relationship name is same
						if (this.compareNames(refName1, refName2) == 2) {
							report.addToReport("Reference match "
									+ refList1.get(i).getName());
							refScore += 1;
						}
					}
				}
			}
			// Return a percentage of the matched references
			if (refList1.size() != 0)
				return refScore / refList1.size();
			return refScore;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// report.terminateRoutine("Structural references");
		}
		return 0;
	}

	/**
	 * Add classes to the list of compared classes
	 * 
	 * @param cls1
	 *            first class that was compared
	 * @param cls2
	 *            second class that was compared
	 */
	private void listComparedClasses(EClass cls1, EClass cls2) {
		if (!comparedClasses.contains(cls1.getName()))
			comparedClasses.add(cls1.getName());
		if (!comparedClasses.contains(cls2.getName()))
			comparedClasses.add(cls2.getName());
	}

	/**
	 * Add to report the list of unmatched classes by seperating the classes
	 * already present in matched classes
	 */
	private void reportUnmatchedClasses() {
		for (int i = 0; i < comparedClasses.size(); i++) {
			if (!matchedClasses.contains(comparedClasses.get(i))) {
				report.addToReport("Unmatched Classes : "
						+ comparedClasses.get(i));
			}
		}
	}
	
	/*
	 * The aloneClasses list that might be useful
	 * 
	 * private void setAloneClasses(){
	 * 		for(int i = 0; i < comparedClasses.size(); i++) {
	 * 			if(!matchedClasses.contains(comparedClasses.get(i)) 
	 * 				&& !similarClasses.contains(comparedClasses.get(i))){
	 * 					aloneClasses.add(comparedClasses.get(i));
	 * 			}
	 * 		}
	 * }
	 */
}
