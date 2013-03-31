package controller.comparer.xmi;

public class XmiParameterElement extends XmiBaseElement {

	public static final String DIRECTION_IN = "in";
	public static final String DIRECTION_OUT = "out";
	public static final String DIRECTION_INOUT = "inout";
	public static final String DIRECTION_RETURN = "return";

	private static final String DIRECTION_DEFAULT = DIRECTION_IN;

	private String direction;

	public XmiParameterElement(String id, String name, String type,
			String visibility, String direction) {
		super(id, name, type, visibility);

		if (direction == null) {
			this.setDirection(DIRECTION_DEFAULT);
		} else if (direction.isEmpty()) {
			this.setDirection(DIRECTION_DEFAULT);
		}
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return this.direction + " " + super.getVisibility() + " "
				+ super.getTypeName() + " " + super.getName();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
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
		if (!(obj instanceof XmiParameterElement)) {
			return false;
		}
		XmiParameterElement other = (XmiParameterElement) obj;
		if (direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			return false;
		}
		return true;
	}

}
