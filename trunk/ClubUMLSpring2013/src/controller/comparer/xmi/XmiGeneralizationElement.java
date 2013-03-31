package controller.comparer.xmi;

public class XmiGeneralizationElement extends XmiBaseElement {

	private String parent;
	
	private XmiClassElement parentElement;
	
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

	/**
	 * @return the parentElement
	 */
	public XmiClassElement getParentElement() {
		return parentElement;
	}

	/**
	 * @param parentElement the parentElement to set
	 */
	public void setParentElement(XmiClassElement parentElement) {
		this.parentElement = parentElement;
	}
	
	@Override
	public String toString() {
		return "Generalization - Parent = " + parent + ", " + super.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((parentElement == null) ? 0 : parentElement.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof XmiGeneralizationElement)) {
			return false;
		}
		XmiGeneralizationElement other = (XmiGeneralizationElement) obj;
		if (parentElement == null) {
			if (other.parentElement != null) {
				return false;
			}
		} else if (!parentElement.equals(other.parentElement)) {
			return false;
		}
		return true;
	}


}
