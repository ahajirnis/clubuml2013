package controller.comparer.xmi;

/**
 *  Look into refactoring this and UploadXmiParser because both use the same code to find the active elements in uml file
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import logging.Log;
import uml2parser.Attribute;
import uml2parser.ModelFileInfo;
import uml2parser.ParseXmi;
import uml2parser.XmiElement;

public class XmiClassDiagramParser {

	private final static String PAPYRUS_CLASS_DIAG = "PapyrusUMLClassDiagram";
	private final static String PAPYRUS_NOTATION_ELEM = "notation:Diagram";
	private final static String PAPYRUS_PACKAGED_ELEM = "packagedElement";
	private final static String PAPYRUS_OPERATION_ELEM = "ownedOperation";

	private final static String PAPYRUS_ATTRIBUTE_ELEM = "ownedAttribute";

	private final static String PAPYRUS_GENERALIZATION_ELEM = "generalization";

	private String umlFileName;
	private String notationFileName;

	private ArrayList<XmiCompareElement> elements;
	private ModelFileInfo modelUmlInfo;
	private ModelFileInfo notationmodelInfo;

	private boolean isClassDiag = false;

	// Check for active items in notation that will be found in uml file
	private List<String> activeIdList;
	private XmiElement classXmiDiag;

	// Store elements used by compare algorithm
	private Stack<XmiElement> stack = new Stack<XmiElement>();
	private List<XmiElement> classList = new ArrayList<XmiElement>();

	XmiClassDiagramParser(String umlFile, String notationFile) {
		umlFileName = umlFile;
		notationFileName = notationFile;
		activeIdList = new ArrayList<String>();
		System.out.println("CREATED");
		this.process();
	}

	// Process is the same as the Upload Xmi functionality (Refactor code)
	private void process() {
		// Parse the UML File

		// first process the .notation file
		notationmodelInfo = new ModelFileInfo(notationFileName);
		ParseXmi notationXmi = new ParseXmi(notationmodelInfo);
		notationXmi.parseDoc();
		
		// second process the .uml file
		modelUmlInfo = new ModelFileInfo(umlFileName);
		ParseXmi umlXmi = new ParseXmi(modelUmlInfo);
		umlXmi.parseDoc();

		// Does the diagram support Class diagrams & sequence diagrams
		List<XmiElement> elemList = notationmodelInfo
				.findElementsByName(PAPYRUS_NOTATION_ELEM);

		if (elemList.size() > 0) {

			for (int i = 0; i < elemList.size(); i++) {

				XmiElement xmi = elemList.get(i);

				List<Attribute> attriblist = xmi.getAttrib();

				for (int j = 0; j < attriblist.size(); j++) {

					Attribute attr = attriblist.get(j);

					if (attr.getName().equals("type")) {
						switch (attr.getValue()) {
						case PAPYRUS_CLASS_DIAG: {
							// TODO: We are currently only supporting one class
							// diagram per
							// Notation file. We can enhance this in future.
							classXmiDiag = xmi;
							isClassDiag = true;
						}
							break;
						// Add other Diagrams
						default: {
							break;
						}
						}
					}
				}
			}
		}
		// need to find the active elements in the class diagram
		if (isClassDiag) {
			System.out.println("ClassXmiDia: " + classXmiDiag);
			ActiveElementIterator(classXmiDiag);

			boolean foundError = false;
			// now find the elements in the class
			for (int i = 0; i < activeIdList.size(); i++) {
				System.out
						.println("Find Element By ID: " + activeIdList.get(i));
				if (modelUmlInfo.findElementsById(activeIdList.get(i)) == false) {
					foundError = true;
					break;
				}

			}
			if (foundError == false) {
				// Build a list of Class.
				System.out.println("BUILD COMPARE ELEMENT STRUCTURE");
				BuildXmiCompareElementStructure();

			}

		}
	}

	/**
	 * Builds a list of Class XmiElements which will be used by the compare algorithm.
	 */
	private void BuildXmiCompareElementStructure() {

		//boolean classFlag = false;

		List<XmiElement> packagedElemList = modelUmlInfo
				.findElementsByName(PAPYRUS_PACKAGED_ELEM);
		for (int i = 0; i < packagedElemList.size(); i++) {

			System.out.println(PAPYRUS_PACKAGED_ELEM + ": "
					+ packagedElemList.get(i) + " "
					+ packagedElemList.get(i).getFoundMatch());

			if (packagedElemList.get(i).getFoundMatch()) {

				//classFlag = false;
				List<Attribute> attrlist = packagedElemList.get(i).getAttrib();
				for (int j = 0; j < attrlist.size(); j++) {
					if (isClassLevel(attrlist.get(j))) {
						
						System.out.println("Valid class name = "
								+ packagedElemList.get(i).getAttributeValue(
										"name"));
						classList.add(packagedElemList.get(i));

						stack.push(packagedElemList.get(i));
						//classFlag = true;
					}
				}

				/*
				 * if (classFlag) { List<XmiElement> target =
				 * findElementsByName( PAPYRUS_ATTRIBUTE_ELEM, stack.peek());
				 * for (XmiElement element : target) {
				 * 
				 * System.out.println("Attribute: " +
				 * element.getAttributeValue("name")); } }
				 */
			}
		}
	}

	/**
	 * Checks if the attribute is for a valid Class level element (Class, Interface, etc)
	 * @param attribute
	 * @return true if valid, else false
	 */
	public boolean isClassLevel(Attribute attribute) {
		if (attribute.getName().equals("xmi:type")
				&& (attribute.getValue().equals("uml:Class") || attribute
						.getValue().equals("uml:Interface"))) {
			return true;
		}

		return false;
	}

	/**
	 * Returns the list of Active Class elements
	 * @return List object of XmiElement
	 */
	public List<XmiElement> getClassList() {
		return classList;
	}
	
	/**
	 * 
	 * @param ElemName
	 * @return
	 */
	public List<XmiElement> findElementsByName(String TargetName,
			XmiElement xmiParent) {
		List<XmiElement> elementList = new ArrayList<XmiElement>();

		ElementIterator(xmiParent, TargetName, elementList);

		return elementList;

	}

	/**
	 * 
	 * @param name
	 * @param elemname
	 * @param elementList
	 */
	private void ElementIterator(XmiElement name, String elemname,
			List<XmiElement> elementList) {

		List<XmiElement> childElementlist = name.getChildElemList();
		// Log.LogCreate().Info("ElementIterator:  Element name " +
		// name.getElementName());
		if (name.getElementName().equals(elemname)) {
			elementList.add(name);
		}

		if (childElementlist != null) {
			for (int i = 0; i < childElementlist.size(); i++) {
				ElementIterator(childElementlist.get(i), elemname, elementList);

			}
		}
		return;
	}

	/**
	 * Sets the active flag for elements found in notation and uml files
	 * @param element
	 */
	private void ActiveElementIterator(XmiElement element) {
		List<XmiElement> childElementlist = element.getChildElemList();
		if (childElementlist != null) {
			// iterate the xmiElement which contains all the information
			for (int i = 0; i < childElementlist.size(); i++) {
				ActiveElementIterator(childElementlist.get(i));
			}

		}
		List<Attribute> attribList = element.getAttrib();
		for (int j = 0; j < attribList.size(); j++) {
			Attribute attrib = attribList.get(j);
			if (attrib.getName().equals("href")) {
				int beginIndex = attrib.getValue().indexOf('#');
				String value = attrib.getValue().substring(beginIndex + 1);
				// Log.LogCreate().Info("Active IDs " + value);
				activeIdList.add(value);
			}
		}

	}

}
