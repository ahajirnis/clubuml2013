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
	private final static String PAPYRUS_PROPERTY_ELEM = "ownedAttribute";
	private final static String PAPYRUS_PARAMETER_ELEM = "ownedParameter";
	private final static String PAPYRUS_GENERALIZATION_ELEM = "generalization";
	
	private final static String PAPYRUS_XMI_ATTRIBUTE_TYPE = "xmi:type";
	private final static String PAPYRUS_ATTRIBUTE_NAME = "name";
	private final static String PAPYRUS_ATTRIBUTE_ID = "xmi:id";
	private final static String PAPYRUS_ATTRIBUTE_TYPE = "type";
	private final static String PAPYRUS_ATTRIBUTE_VISIBILITY = "visibility";
	private final static String PAPYRUS_ATTRIBUTE_DIRECTION = "direction";
	private final static String PAPYRUS_GENERALIZATION_DIRECTION = "general";
	
	private String umlFileName;
	private String notationFileName;

	private ArrayList<XmiBaseElement> elements;
	private ModelFileInfo modelUmlInfo;
	private ModelFileInfo notationmodelInfo;

	private boolean isClassDiag = false;

	// Check for active items in notation that will be found in uml file
	private List<String> activeIdList;
	private XmiElement classXmiDiag;

	// Store elements used by compare algorithm
	private Stack<XmiElement> stack = new Stack<XmiElement>();

	private ArrayList<XmiClassElement> classElements = new ArrayList<XmiClassElement>();

	XmiClassDiagramParser(String umlFile, String notationFile) {
		umlFileName = umlFile;
		notationFileName = notationFile;
		activeIdList = new ArrayList<String>();
		System.out.println("CREATED");
		this.process();
		
		// TESTing
		System.out.println("TEST OUTPUT ELEMENTS");
		for(XmiClassElement Class: classElements) {
			System.out.println(Class.toString());
			for(XmiAttributeElement elelment: Class.getAttributes()) {
				System.out.println(elelment.toString());
			}
			
			for(XmiOperationElement element: Class.getOperations()) {
				System.out.println(element.toString());
			}
			
			for(XmiClassElement elelment: Class.getNestedClass()) {
				System.out.println("Nexted: " + elelment.toString());
			}
			
			for(XmiGeneralizationElement elelment: Class.getGeneralization()) {
				System.out.println("Generalization: " + elelment.toString());
			}
		}
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
				BuildXmiCompareElementStructure(modelUmlInfo);

			}

		}
	}

	/**
	 * Builds a list of Class XmiElements which will be used by the compare
	 * algorithm.
	 */
	private void BuildXmiCompareElementStructure(ModelFileInfo modelUmlInfo) {

		// boolean classFlag = false;

		List<XmiElement> packagedElemList = modelUmlInfo
				.findElementsByName(PAPYRUS_PACKAGED_ELEM);
		for (int i = 0; i < packagedElemList.size(); i++) {

			System.out.println(PAPYRUS_PACKAGED_ELEM + ": "
					+ packagedElemList.get(i) + " "
					+ packagedElemList.get(i).getFoundMatch());

			if (packagedElemList.get(i).getFoundMatch()) {

				// classFlag = false;
				List<Attribute> attrlist = packagedElemList.get(i).getAttrib();
				for (int j = 0; j < attrlist.size(); j++) {
					if (isClassLevel(attrlist.get(j))) {

						System.out.println("Valid class name = "
								+ packagedElemList.get(i).getAttributeValue(
										"name"));

						// classList.add(packagedElemList.get(i));
						classElements
								.add(createXmiClassElement(packagedElemList
										.get(i)));
						stack.push(packagedElemList.get(i));

						packagedElemList.get(i).getChildElemList();
						
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

	private XmiClassElement createXmiClassElement(XmiElement xmiElement) {
		List<XmiElement> childElements = xmiElement.getChildElemList();
		
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);

		XmiClassElement xmiClass = new XmiClassElement(id, name, type, "");

		System.out.println("Create Class Element " + xmiClass.toString());

		for (int j = 0; j < childElements.size(); j++) {
			String tag = childElements.get(j).getElementName();
			System.out.println("TAG: " + tag);
			switch (tag) {
			case PAPYRUS_PROPERTY_ELEM:
				xmiClass.addAttribute(createXmiAttributeElement(childElements.get(j)));
				break;
			case PAPYRUS_OPERATION_ELEM:
				xmiClass.addOperation(createXmiOperationElement(childElements.get(j)));
				break;
			case PAPYRUS_GENERALIZATION_ELEM:
				xmiClass.addGeneralization(createXmiGeneralizationElement(childElements.get(j)));
				break;
			}
			
		}

		return xmiClass;
	}

	/**
	 * Checks if the attribute is for a valid Class level element (Class,
	 * Interface, etc)
	 * 
	 * @param attribute
	 * @return true if valid, else false
	 */
	public boolean isClassLevel(Attribute attribute) {

		String name = attribute.getName();
		String value = attribute.getValue();

		System.out.println("isClassLevel name: " + name + " value: " + value);
		if (name.equals(PAPYRUS_XMI_ATTRIBUTE_TYPE)
				&& (value.equals("uml:Class") || value.equals("uml:Interface"))) {
			return true;
		}

		return false;
	}

	private XmiAttributeElement createXmiAttributeElement(XmiElement xmiElement) {
		List<Attribute> attrlist = xmiElement.getAttrib();

		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		String visibility = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);
		
		XmiAttributeElement xmiClass = new XmiAttributeElement(id, name, type, visibility);

		System.out.println(xmiClass.toString());

		for (int j = 0; j < attrlist.size(); j++) {

		}

		return xmiClass;
	}
	
	private XmiOperationElement createXmiOperationElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		String visibility = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);
		
		XmiOperationElement xmiClass = new XmiOperationElement(id, name, type, visibility);

		System.out.println(xmiClass.toString());

		List<XmiElement> childrenElement = xmiElement.getChildElemList();
		
		for(XmiElement child : childrenElement) {
			String tag = child.getElementName();
			
			switch (tag) {
			case PAPYRUS_PARAMETER_ELEM:
				xmiClass.addParameter(createXmiParameterElement(child));
				break;
			}
		}
		
		return xmiClass;
	}
	
	private XmiParameterElement createXmiParameterElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		String visibility = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);
		String direction = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_DIRECTION);
		
		XmiParameterElement xmiClass = new XmiParameterElement(id, name, type, visibility, direction);
		
		System.out.println(xmiClass.toString());

		return xmiClass;
	}
	
	private XmiGeneralizationElement createXmiGeneralizationElement(XmiElement xmiElement) {
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String generalization = xmiElement.getAttributeValue(PAPYRUS_GENERALIZATION_DIRECTION);

		XmiGeneralizationElement xmiClass = new XmiGeneralizationElement(id, generalization);
		
		System.out.println(xmiClass.toString());

		return xmiClass;
	}
	
	/**
	 * Returns the list of Active Class elements
	 * 
	 * @return List object of XmiElement
	 */
	public List<XmiClassElement> getClassElements() {
		return classElements;
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
	 * 
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
