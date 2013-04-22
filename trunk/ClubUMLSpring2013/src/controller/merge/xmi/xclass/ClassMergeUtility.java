package controller.merge.xmi.xclass;

import java.util.ArrayList;

import org.w3c.dom.Element;

public final class ClassMergeUtility {

	// Tags
	private final static String PAPYRUS_PACKAGED_ELEMENT = "packagedElement";
	private final static String PAPYRUS_UML_MODEL = "uml:Model";
	private final static String PAPYRUS_NOTATION_DIAGRAM = "notation:Diagram";
	private final static String PAPYRUS_MEMBER_END = "ownedEnd";

	// Attributes
	private final static String PAPYRUS_ATTRIBUTE_TYPE = "type";
	private final static String PAPYRUS_ATTRIBUTE_XMI_TYPE = "xmi:type";
	private final static String PAPYRUS_ATTRIBUTE_ID = "xmi:id";
	private final static String PAPYRUS_ATTRIBUTE_ASSOCIATION = "association";
	private final static String PAPYRUS_ATTRIBUTE_GENERAL = "general";
	private final static String PAPYRUS_ATTRIBUTE_TARGET = "target";
	private final static String PAPYRUS_ATTRIBUTE_SOURCE = "source";
	private final static String PAPYRUS_ATTRIBUTE_HREF = "href";
	private final static String PAPYRUS_ATTRIBUTE_MEMBER_END = "memberEnd";

	/**
	 * Creates the href value based on the XmiNotationElement and the new
	 * fileName
	 * 
	 * @param element
	 * @param fileName
	 * @return href value
	 */
	public final static String buildHref(XmiNotationElement element,
			String fileName) {
		return fileName + ".uml#" + element.getNewId();
	}

	/**
	 * Creates the href value based on the Id and the new fileName
	 * 
	 * @param id
	 * @param fileName
	 * @return href value
	 */
	public final static String buildHref(String Id, String fileName) {
		return fileName + ".uml#" + Id;
	}

	/**
	 * Builds a list of the specified notation element type
	 * 
	 * @param element
	 *            to pull the children elements from
	 * @param notationType
	 *            int value for type
	 * @return arraylist of XmiNotationElement for specified type
	 */
	public final static ArrayList<XmiNotationElement> getNotationElements(
			XmiNotationElement element, int notationType) {

		ArrayList<XmiNotationElement> elements = new ArrayList<XmiNotationElement>();
		for (XmiNotationElement notation : element.getElementList()) {
			if (notation.getType() == notationType) {
				elements.add(notation);
			}
		}

		return elements;
	}

	public final static void changeAllId(Element element) {
		if (element.hasChildNodes()) {
			changeAllId((Element) element.getFirstChild());
		}

		if (element.getNextSibling() != null) {
			changeAllId((Element) element.getNextSibling());
		}

		// Do not change Id for these scenarios
		if (element.getTagName().equals(PAPYRUS_UML_MODEL)) {
			return;
		}

		if (element.getTagName().equals(PAPYRUS_NOTATION_DIAGRAM)) {
			return;
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_XMI_TYPE)) {
			if (element.getAttribute(PAPYRUS_ATTRIBUTE_XMI_TYPE).equals(
					PAPYRUS_UML_MODEL)) {
				return;
			}
		}

		// Change Ids
		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_ID)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_ID);
			element.setAttribute(PAPYRUS_ATTRIBUTE_ID, mixId(id));
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_ASSOCIATION)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_ASSOCIATION);
			element.setAttribute(PAPYRUS_ATTRIBUTE_ASSOCIATION, mixId(id));
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_GENERAL)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_GENERAL);
			element.setAttribute(PAPYRUS_ATTRIBUTE_GENERAL, mixId(id));
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_TARGET)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_TARGET);
			element.setAttribute(PAPYRUS_ATTRIBUTE_TARGET, mixId(id));
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_SOURCE)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_SOURCE);
			element.setAttribute(PAPYRUS_ATTRIBUTE_SOURCE, mixId(id));
		}

		if (element.getTagName().equals(PAPYRUS_MEMBER_END)
				&& element.hasAttribute(PAPYRUS_ATTRIBUTE_TYPE)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_TYPE);
			element.setAttribute(PAPYRUS_ATTRIBUTE_TYPE, mixId(id));
		}

		if (element.getTagName().equals(PAPYRUS_PACKAGED_ELEMENT)
				&& element.hasAttribute(PAPYRUS_ATTRIBUTE_MEMBER_END)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_MEMBER_END);
			element.setAttribute(PAPYRUS_ATTRIBUTE_MEMBER_END,
					mixMemberEndId(id));
		}

		if (element.hasAttribute(PAPYRUS_ATTRIBUTE_HREF)) {
			String id = element.getAttribute(PAPYRUS_ATTRIBUTE_HREF);
			element.setAttribute(PAPYRUS_ATTRIBUTE_HREF, mixHrefId(id));
		}
	}

	/**
	 * Create a randomized id leaving the first 4 characters alone
	 * 
	 * @param id
	 * @return
	 */

	public final static String mixId(String id) {

		if (id.length() <= 4) {
			return id;
		}

		String result;
		StringBuilder newString = new StringBuilder();

		for (char letter : id.substring(4).toCharArray()) {
			char newLetter = (char)(((letter + 13) % 25) + 'a');
			newString.append(newLetter);
		}

		result = id.substring(0, 4) + newString.toString();

		return result;
	}

	public final static String mixHrefId(String id) {

		int indexfile = id.indexOf("#");

		String file = id.substring(0, indexfile);

		String result = mixId(id.substring(indexfile) + 1);

		return file + result;
	}

	public final static String mixMemberEndId(String id) {

		if (id.length() <= 4) {
			return id;
		}

		int indexSpace = id.indexOf(" ");

		String id1 = mixId(id.substring(0, indexSpace - 1));
		String id2 = mixId(id.substring(indexSpace + 1));

		return id1 + " " + id2;

	}
}
