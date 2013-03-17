package controller.comparer.xmi;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

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
		
		String formalParams;
		String returnValue = "void";
		ArrayList<XmiParameterElement> listParam = new ArrayList<XmiParameterElement>();
		
		for (XmiParameterElement param: parameters) {
			if (param.getDirection() == XmiParameterElement.DIRECTION_RETURN) {
				returnValue = param.getName();
			} else {
				listParam.add(param);
			}
		}
		
		formalParams = StringUtils.join(listParam, ", ");
		
		return "Operation: " + super.getVisibility() + " " + returnValue + "(" + formalParams + ")";
	}
}
