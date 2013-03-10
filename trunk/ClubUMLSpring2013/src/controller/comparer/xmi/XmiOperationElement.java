package controller.comparer.xmi;

import java.util.ArrayList;

public class XmiOperationElement extends XmiBaseElement {

	private ArrayList<XmiParameterElement> parameters;
	
	public XmiOperationElement(String id, String name, String type,
			String visibility) {
		super(id, name, type, visibility);

		this.parameters = new ArrayList<XmiParameterElement>();
	}

	public ArrayList<XmiParameterElement> getParameters() {
		return parameters;
	}

	public void addParameter(XmiParameterElement parameter) {
		this.parameters.add(parameter);
	}

	@Override
	public String toString() {
		return "Operation: " + super.toString();
	}
}
