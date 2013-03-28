package controller.comparer.xmi;

public class XmiAttributeElement extends XmiBaseElement {

	public XmiAttributeElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public boolean equals(Object object) {
		XmiAttributeElement o = (XmiAttributeElement) object;
		if (this.getName() == o.getName()
				&& this.getVisibility() == o.getVisibility()
				&& this.getTypeName() == o.getTypeName()) {
			return true;
		} else
			return false;
	}
}
