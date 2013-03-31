package controller.comparer.xmi;

public class XmiTypeElement extends XmiBaseElement {

	public XmiTypeElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);
	}

	@Override
	public String toString() {
		return super.getName();
	}

}
