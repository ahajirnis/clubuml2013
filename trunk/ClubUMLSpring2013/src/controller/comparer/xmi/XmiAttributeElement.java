package controller.comparer.xmi;

public class XmiAttributeElement extends XmiBaseElement {

	private XmiValueElement lowerValue;
	private XmiValueElement upperValue; 
	private XmiValueElement defaultValue; 
	
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
	public XmiAttributeElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * @return the lowerValue
	 */
	public XmiValueElement getLowerValue() {
		return lowerValue;
	}

	/**
	 * @param lowerValue the lowerValue to set
	 */
	public void setLowerValue(XmiValueElement lowerValue) {
		this.lowerValue = lowerValue;
	}

	/**
	 * @return the upperValue
	 */
	public XmiValueElement getUpperValue() {
		return upperValue;
	}

	/**
	 * @param upperValue the upperValue to set
	 */
	public void setUpperValue(XmiValueElement upperValue) {
		this.upperValue = upperValue;
	}

	/**
	 * @return the defaultValue
	 */
	public XmiValueElement getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(XmiValueElement defaultValue) {
		this.defaultValue = defaultValue;
	}
}
