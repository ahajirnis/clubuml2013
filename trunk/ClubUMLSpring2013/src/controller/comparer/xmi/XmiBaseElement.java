package controller.comparer.xmi;

public class XmiBaseElement {

	private static final String DEFAULT_VISIBILITY = "public";
	public static final String DEFAULT_TYPE = "<Undefined>";

	private String id;
	private String name;
	private String type;
	private String typeName;
	private String visibility;

	/**
	 * Constructor to initialize necessary class members
	 *
	 * @param id
	 * 			The ID of the element
	 * @param name
	 * 			The name of the element
	 * @param type
	 * 			The type of the element
	 * @param visibility
	 * 			The visibility of the element
	 */
	public XmiBaseElement(String id, String name, String type, String visibility) {

		this.id = id;
		this.name = name;
		setUmlType(type);
		setVisibility(visibility);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {

		if (visibility == null) {
			this.visibility = DEFAULT_VISIBILITY;
		} else if (visibility.isEmpty()) {
			this.visibility = DEFAULT_VISIBILITY;
		} else {
			this.visibility = visibility;
		}
	}

	public String getUmlType() {
		return type;
	}

	public void setUmlType(String umlType) {
		if (umlType == null) {
			this.type = DEFAULT_TYPE;
		} else {
			this.type = umlType;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		if (this.typeName == null) {
			return type;
		} else if (this.typeName.isEmpty()) {
			return type;
		} else {
			return typeName;
		}
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return getVisibility() + " " + getTypeName() + " " + getName();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		result = prime * result
				+ ((visibility == null) ? 0 : visibility.hashCode());
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
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof XmiBaseElement)) {
			return false;
		}
		XmiBaseElement other = (XmiBaseElement) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (typeName == null) {
			if (other.typeName != null) {
				return false;
			}
		} else if (!typeName.equals(other.typeName)) {
			return false;
		}
		if (visibility == null) {
			if (other.visibility != null) {
				return false;
			}
		} else if (!visibility.equals(other.visibility)) {
			return false;
		}
		return true;
	}

	
}
