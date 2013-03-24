package controller.comparer.xmi;

public class XmiValueElement extends XmiBaseElement
{
	private String value;

	public XmiValueElement(String id, String type,
			String value) {
		super(id, "", type, "");
		this.setValue(value);
		// TODO Auto-generated constructor stub
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
