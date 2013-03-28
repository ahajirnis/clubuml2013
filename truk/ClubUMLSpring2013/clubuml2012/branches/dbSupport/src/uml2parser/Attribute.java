package uml2parser;
/**
 * 
 * @author shuklp
 *
 */
public class Attribute {

	private String name;
	private String value;
	
	/**
	 * 
	 * @param attrName
	 * @param attrValue
	 */
	public Attribute(String attrName, String attrValue) {
		name = attrName;
		value = attrValue;
	}
	
	/**
	 * 
	 * @return The name of the Attribute 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return The Value of the Attribute
	 */
	public String getValue() { 
		return value;
	}
}
