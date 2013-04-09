package controller.comparer.xmi;

public class XmiValueElement extends XmiBaseElement
{
	private String value;
	
	// True if value is null
	private boolean isNull;
	
	public XmiValueElement(String id, String type,
			boolean isNull) {
		super(id, "", type, "");
		setNull(isNull);
	}
	
	public XmiValueElement(String id, String name, String type,
			String value) {
		super(id, name, type, "");
		this.setValue(value);
	}
	
	public XmiValueElement(String id, String type,
			String value) {
		this(id, "", type, value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the isNull
	 */
	public boolean isNull() {
		return isNull;
	}

	/**
	 * @param isNull the isNull to set
	 */
	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

}
