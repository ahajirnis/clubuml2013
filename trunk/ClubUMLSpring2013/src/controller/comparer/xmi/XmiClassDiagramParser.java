package controller.comparer.xmi;

/**
 *  Look into refactoring this and UploadXmiParser because both use the same code to find the active elements in uml file
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import uml2parser.Attribute;
import uml2parser.ModelFileInfo;
import uml2parser.ParseXmi;
import uml2parser.XmiElement;

public class XmiClassDiagramParser {

	// Class diagram signifier
	private final static String PAPYRUS_CLASS_DIAG = "PapyrusUMLClassDiagram";

	// Tags
	private final static String PAPYRUS_NOTATION_ELEM = "notation:Diagram";
	private final static String PAPYRUS_PACKAGED_ELEM = "packagedElement";
	private final static String PAPYRUS_OPERATION_ELEM = "ownedOperation";
	private final static String PAPYRUS_PROPERTY_ELEM = "ownedAttribute";
	private final static String PAPYRUS_PARAMETER_ELEM = "ownedParameter";
	private final static String PAPYRUS_GENERALIZATION_ELEM = "generalization";
	private final static String PAPYRUS_MEMVBER_END = "ownedEnd";
	private final static String PAPYRUS_LOWER_VALUE = "lowerValue";
	private final static String PAPYRUS_UPPER_VALUE = "upperValue";

	// UML Types
	private final static String PAPYRUS_PACKAGE_TYPE_CLASS = "uml:Class";
	private final static String PAPYRUS_PACKAGE_TYPE_INTERFACE = "uml:Interface";
	private final static String PAPYRUS_PACKAGE_TYPE_PRIMITIVE = "uml:PrimitiveType";
	private final static String PAPYRUS_PACKAGE_TYPE_ASSOCIATION = "uml:Association";
	private final static String PAPYRUS_PACKAGE_TYPE_LITERNAL_INTEGER = "uml:LiteralInteger";
	private final static String PAPYRUS_PACKAGE_TYPE_LITERNAL_UNLILMITED = "uml:LiteralUnlimitedNatural";

	// Attributes
	private final static String PAPYRUS_XMI_ATTRIBUTE_TYPE = "xmi:type";
	private final static String PAPYRUS_ATTRIBUTE_NAME = "name";
	private final static String PAPYRUS_ATTRIBUTE_ID = "xmi:id";
	private final static String PAPYRUS_ATTRIBUTE_TYPE = "type";
	private final static String PAPYRUS_ATTRIBUTE_VISIBILITY = "visibility";
	private final static String PAPYRUS_ATTRIBUTE_DIRECTION = "direction";
	private final static String PAPYRUS_GENERALIZATION_DIRECTION = "general";
	private final static String PAPYRUS_ATTRIBUTE_ASSOCIATION = "association";
	private final static String PAPYRUS_ATTRIBUTE_VALUE = "value";
	private final static String PAPYRUS_MEMBER_END = "memberEnd";
	private final static String PAPYRUS_AGGREGATION = "aggregation";

	private String umlFileName;
	private String notationFileName;

	private ModelFileInfo modelUmlInfo;
	private ModelFileInfo notationmodelInfo;

	private boolean isClassDiag = false;

	// Check for active items in notation that will be found in uml file
	private List<String> activeIdList;
	private XmiElement classXmiDiag;

	// Store elements used by compare algorithm
	private Stack<XmiElement> stack = new Stack<XmiElement>();

	private ArrayList<XmiBaseElement> rootElements = new ArrayList<XmiBaseElement>();
	private ArrayList<XmiClassElement> classElements = new ArrayList<XmiClassElement>();
	private ArrayList<XmiTypeElement> primitiveElements = new ArrayList<XmiTypeElement>();
	private ArrayList<XmiAssociationElement> associationElements = new ArrayList<XmiAssociationElement>();

	XmiClassDiagramParser(String umlFile, String notationFile) {
		umlFileName = umlFile;
		notationFileName = notationFile;
		activeIdList = new ArrayList<String>();

		System.out.println("CREATED");
		this.process();
		this.postProcess();

		// TESTing shows all elements
		System.out.println("TEST OUTPUT ELEMENTS");
		for (XmiBaseElement Class : rootElements) {

			if (Class instanceof XmiClassElement) {

				System.out.println("Class " + Class.toString());

				for (XmiAttributeElement elelment : ((XmiClassElement) Class)
						.getAttributes()) {
					System.out.println("Attribute " + elelment.toString());
				}

				for (XmiOperationElement element : ((XmiClassElement) Class)
						.getOperations()) {
					System.out.println("Operation " + element.toString());
				}

				for (XmiClassElement elelment : ((XmiClassElement) Class)
						.getNestedClass()) {
					System.out.println("Nested: " + elelment.toString());
				}

				for (XmiGeneralizationElement elelment : ((XmiClassElement) Class)
						.getGeneralization()) {
					System.out.println("Generalization " + elelment.toString());
				}
			} else if (Class instanceof XmiTypeElement) {
				System.out.println("Primitive " + Class.toString());
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
			ActiveElementIterator(classXmiDiag);

			// Check for error
			boolean foundError = false;
			// now find the elements in the class
			for (int i = 0; i < activeIdList.size(); i++) {

				if (modelUmlInfo.findElementsById(activeIdList.get(i)) == false) {
					foundError = true;
					break;
				}

			}
			if (!foundError) {
				// Build a list of Class.
				BuildXmiCompareElementStructure(modelUmlInfo);
			}
		}
	}

	/**
	 * postProcess
	 * 
	 */
	private void postProcess() {
		DefineReferenceTypeName();

	}

	/**
	 * DefineReferenceTypeName
	 * 
	 * Runs through elements and finds the names for the types if they are
	 * reference. A type that is a reference begings with an underscore.
	 */
	private void DefineReferenceTypeName() {

		for (XmiClassElement Class : classElements) {

			for (XmiAttributeElement element : Class.getAttributes()) {
				if (element.getUmlType().startsWith("_")) {
					element.setTypeName(Utility.getBaseNameById(
							rootElements, element.getUmlType()));
				}
			}

			for (XmiOperationElement element : Class.getOperations()) {
				if (element.getUmlType().startsWith("_")) {
					element.setTypeName(Utility.getBaseNameById(
							rootElements, element.getUmlType()));
				}
			}

			for (XmiClassElement element : Class.getNestedClass()) {
				if (element.getUmlType().startsWith("_")) {
					element.setTypeName(Utility.getBaseNameById(
							rootElements, element.getUmlType()));
				}
			}

			for (XmiGeneralizationElement element : Class.getGeneralization()) {
				if (element.getUmlType().startsWith("_")) {
					element.setTypeName(Utility.getBaseNameById(
							rootElements, element.getUmlType()));
				}
			}
		}
	}

	/**
	 * DefineReferenceTypeName
	 * 
	 * Runs through elements and finds the names for the types if they are
	 * reference. A type that is a reference begings with an underscore.
	 */
	private void DefineMissingAssociations() {

		for (XmiClassElement Class : classElements) {

			for (XmiMemberEndElement element : Class.getClassifer()) {
				if (element.getAssociation() == null) {
					element.setAssociation(Utility.getAssociationById(
							associationElements, element.getAssociationId()));
				}
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

			if (packagedElemList.get(i).getFoundMatch()) {

				// classFlag = false;
				List<Attribute> attrlist = packagedElemList.get(i).getAttrib();
				for (int j = 0; j < attrlist.size(); j++) {
					if (isClassLevel(attrlist.get(j))) {

						String tag = attrlist.get(j).getValue();

						switch (tag) {
						case PAPYRUS_PACKAGE_TYPE_PRIMITIVE: {
							XmiTypeElement typeElement = createXmiTypeElement(packagedElemList
									.get(i));

							rootElements.add(typeElement);

							primitiveElements.add(typeElement);

							break;
						}
						case PAPYRUS_PACKAGE_TYPE_CLASS: {

							XmiClassElement classElement = createXmiClassElement(packagedElemList
									.get(i));

							rootElements.add(classElement);

							classElements.add(classElement);

							break;
						}
						case PAPYRUS_PACKAGE_TYPE_INTERFACE: {

							XmiClassElement classElement = createXmiClassElement(packagedElemList
									.get(i));

							rootElements.add(classElement);

							classElements.add(classElement);
							break;
						}
						case PAPYRUS_PACKAGE_TYPE_ASSOCIATION: {

							XmiAssociationElement assocationElement = createXmiAssociationElement(packagedElemList
									.get(i));

							rootElements.add(assocationElement);

							associationElements.add(assocationElement);
							break;
						}
						}
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

	private XmiTypeElement createXmiTypeElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);

		return new XmiTypeElement(id, name, type, "");
	}

	private XmiClassElement createXmiClassElement(XmiElement xmiElement) {
		List<XmiElement> childElements = xmiElement.getChildElemList();

		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);

		XmiClassElement xmiClass = new XmiClassElement(id, name, type, "");

		for (int j = 0; j < childElements.size(); j++) {
			String tag = childElements.get(j).getElementName();
			String association = childElements.get(j).getAttributeValue(
					PAPYRUS_ATTRIBUTE_ASSOCIATION);
			switch (tag) {
			case PAPYRUS_PROPERTY_ELEM:
				if (association == null) {
					xmiClass.addAttribute(createXmiAttributeElement(childElements
							.get(j)));
				} else {
					xmiClass.addClassifer(createXmiMemberEndElement(childElements
							.get(j)));
				}

				break;
			case PAPYRUS_OPERATION_ELEM:
				xmiClass.addOperation(createXmiOperationElement(childElements
						.get(j)));
				break;
			case PAPYRUS_GENERALIZATION_ELEM:
				xmiClass.addGeneralization(createXmiGeneralizationElement(childElements
						.get(j)));
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

		if (name.equals(PAPYRUS_XMI_ATTRIBUTE_TYPE)
				&& (value.equals(PAPYRUS_PACKAGE_TYPE_CLASS)
						|| value.equals(PAPYRUS_PACKAGE_TYPE_INTERFACE) || value
							.equals(PAPYRUS_PACKAGE_TYPE_PRIMITIVE))) {
			return true;
		}

		return false;
	}

	private XmiAttributeElement createXmiAttributeElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		String visibility = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);

		String association = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_ASSOCIATION);

		XmiAttributeElement xmiClass = new XmiAttributeElement(id, name, type,
				visibility);

		return xmiClass;
	}

	private XmiOperationElement createXmiOperationElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		String visibility = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);

		XmiOperationElement xmiClass = new XmiOperationElement(id, name, type,
				visibility);

		List<XmiElement> childrenElement = xmiElement.getChildElemList();

		for (XmiElement child : childrenElement) {
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
		String visibility = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_VISIBILITY);
		String direction = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_DIRECTION);

		XmiParameterElement xmiClass = new XmiParameterElement(id, name, type,
				visibility, direction);

		return xmiClass;
	}

	private XmiGeneralizationElement createXmiGeneralizationElement(
			XmiElement xmiElement) {
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String generalization = xmiElement
				.getAttributeValue(PAPYRUS_GENERALIZATION_DIRECTION);

		XmiGeneralizationElement xmiClass = new XmiGeneralizationElement(id,
				generalization);

		return xmiClass;
	}

	private XmiAssociationElement createXmiAssociationElement(
			XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);

		XmiAssociationElement xmiClass = new XmiAssociationElement(id, name,
				type);

		List<XmiElement> childrenElement = xmiElement.getChildElemList();

		for (XmiElement child : childrenElement) {
			String tag = child.getElementName();

			switch (tag) {
			case PAPYRUS_MEMVBER_END:
				XmiMemberEndElement memberEndChild = createXmiMemberEndElement(child);
				xmiClass.addMemberEnd(memberEndChild);
				memberEndChild.setAssociation(xmiClass);
				break;
			}
		}

		return null;
	}

	private XmiMemberEndElement createXmiMemberEndElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String name = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_NAME);
		
		String aggregationValue = xmiElement
				.getAttributeValue(PAPYRUS_AGGREGATION);
		
		String associationId = xmiElement
				.getAttributeValue(PAPYRUS_ATTRIBUTE_ASSOCIATION);

		XmiMemberEndElement xmiClass = new XmiMemberEndElement(id, name, type);
		
		if (aggregationValue != null) {
			xmiClass.setAggregation(AggregationValues.valueOf(aggregationValue));
		}
		
		xmiClass.setAssociationId(associationId);
		
		List<XmiElement> childrenElement = xmiElement.getChildElemList();

		for (XmiElement child : childrenElement) {
			String tag = child.getElementName();

			switch (tag) {
			case PAPYRUS_LOWER_VALUE:
				xmiClass.setLowerValue(createXmiValueElement(child));
				break;
			case PAPYRUS_UPPER_VALUE:
				xmiClass.setUpperValue(createXmiValueElement(child));
				break;
			}
		}

		return xmiClass;
	}

	private XmiValueElement createXmiValueElement(XmiElement xmiElement) {
		String type = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_TYPE);
		String id = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_ID);
		String value = xmiElement.getAttributeValue(PAPYRUS_ATTRIBUTE_VALUE);

		XmiValueElement xmiValue = new XmiValueElement(id, type, value);

		return xmiValue;
	}

	/**
	 * Returns the list of Active Root elements
	 * 
	 * @return List object of XmiElement
	 */
	public List<XmiBaseElement> getRootElements() {
		return rootElements;
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
	 * Returns the list of Active Type elements
	 * 
	 * @return List object of XmiElement
	 */
	public List<XmiTypeElement> getTypeElements() {
		return primitiveElements;
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
