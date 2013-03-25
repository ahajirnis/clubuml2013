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
	
	@Override
	public boolean equals(Object object) {
		XmiTypeElement o = (XmiTypeElement) object;
		if (this.getName() == o.getName()
				&& this.getVisibility() == o.getVisibility()
				&& this.getTypeName() == o.getTypeName()) {
			return true;
		} else
			return false;
	}
}
