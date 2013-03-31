package controller.comparer.xmi;

import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;

public class XmiClassElement extends XmiBaseElement {

	private ArrayList<XmiOperationElement> operations;
	private ArrayList<XmiAttributeElement> attributes;
	private ArrayList<XmiClassElement> nestedClass;
	private ArrayList<XmiGeneralizationElement> generalization;

	// linked to association
	private ArrayList<XmiMemberEndElement> classifer;

	public XmiClassElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);

		this.operations = new ArrayList<XmiOperationElement>();
		this.attributes = new ArrayList<XmiAttributeElement>();
		this.nestedClass = new ArrayList<XmiClassElement>();
		this.generalization = new ArrayList<XmiGeneralizationElement>();
		this.classifer = new ArrayList<XmiMemberEndElement>();
	}

	public ArrayList<XmiOperationElement> getOperations() {
		return operations;
	}

	public void addOperation(XmiOperationElement operation) {
		this.operations.add(operation);
	}

	public ArrayList<XmiAttributeElement> getAttributes() {
		return attributes;
	}

	public void addAttribute(XmiAttributeElement attribute) {
		this.attributes.add(attribute);
	}

	public ArrayList<XmiClassElement> getNestedClass() {
		return nestedClass;
	}

	public void addNestedClass(XmiClassElement nestedClass) {
		this.nestedClass.add(nestedClass);
	}

	@Override
	public String toString() {
		return super.getVisibility() + " " + super.getName();
	}

	public ArrayList<XmiGeneralizationElement> getGeneralization() {
		return generalization;
	}

	public void addGeneralization(XmiGeneralizationElement generalization) {
		this.generalization.add(generalization);
	}

	/**
	 * @return the classifer
	 */
	public ArrayList<XmiMemberEndElement> getClassifer() {
		return classifer;
	}

	/**
	 * @param classifer
	 *            the classifer to set
	 */
	public void addClassifer(XmiMemberEndElement classifer) {
		this.classifer.add(classifer);
	}

	public boolean equals1(Object obj) {
		if (obj == null) {
			System.out.println("FAIL OBJ");
			return false;
		} else if (obj == this) {
			System.out.println("FAIL THIS");
			return false;
		} else if (obj.getClass() != getClass()) {
			System.out.println("CLASS");
			return false;
		}

		XmiClassElement class2 = (XmiClassElement) obj;

		// TODO: Need to add association equals when we get there

		if (!this.attributes.equals(class2.getAttributes())) {
			return false;
		} else if (!this.operations.equals(class2.getOperations())) {
			return false;
		} else if (!this.nestedClass.equals(class2.getNestedClass())) {
			return false;
		} else if (!this.generalization.equals(class2.getGeneralization())) {
			return false;
		} else if (!super.equals(class2)) {
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result
				+ ((classifer == null) ? 0 : classifer.hashCode());
		result = prime * result
				+ ((generalization == null) ? 0 : generalization.hashCode());
		result = prime * result
				+ ((nestedClass == null) ? 0 : nestedClass.hashCode());
		result = prime * result
				+ ((operations == null) ? 0 : operations.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof XmiClassElement)) {
			return false;
		}
		XmiClassElement other = (XmiClassElement) obj;
		if (attributes == null) {
			if (other.attributes != null) {
				return false;
			}
		} else if (!attributes.equals(other.attributes)) {
			return false;
		}
		if (classifer == null) {
			if (other.classifer != null) {
				return false;
			}
		} else if (!classifer.equals(other.classifer)) {
			return false;
		}
		if (generalization == null) {
			if (other.generalization != null) {
				return false;
			}
		} else if (!generalization.equals(other.generalization)) {
			return false;
		}
		if (nestedClass == null) {
			if (other.nestedClass != null) {
				return false;
			}
		} else if (!nestedClass.equals(other.nestedClass)) {
			return false;
		}
		if (operations == null) {
			if (other.operations != null) {
				return false;
			}
		} else if (!operations.equals(other.operations)) {
			return false;
		}
		return true;
	}
}
