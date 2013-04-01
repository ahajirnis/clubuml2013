package controller.comparer.xmi;

public class XmiAttributeElement extends XmiBaseElement {

	public XmiAttributeElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);
	}

	@Override
	public String toString() {
		return "Attribute: " + super.toString();
	}
}
