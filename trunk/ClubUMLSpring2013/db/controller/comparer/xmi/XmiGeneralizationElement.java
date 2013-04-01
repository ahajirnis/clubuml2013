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
		return "Generalization: Parent=" + parent + ", " + super.toString();
	}
}
