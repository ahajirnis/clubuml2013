package controller.comparer.xmi;

public class XmiGeneralizationElement extends XmiBaseElement {

	private String parent;
	
	public XmiGeneralizationElement(String id, String parentElement) {
		super(id, "", "", "");
		
		this.setParent(parentElement);
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Generalization - Parent = " + parent + ", " + super.toString();
	}
	
	@Override
	public boolean equals(Object object) {
		XmiGeneralizationElement o = (XmiGeneralizationElement) object;
		if (this.getName() == o.getName()
				&& this.getVisibility() == o.getVisibility()
				&& this.getTypeName() == o.getTypeName()
				&& this.getParent() == o.getParent()) {
			return true;
		} else
			return false;
	}
}
