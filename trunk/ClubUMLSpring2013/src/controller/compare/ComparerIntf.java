package controller.compare;

import org.json.simple.JSONObject;

public interface ComparerIntf {	
	
	/**
	 * Take two path string which points to the actual diagram file
	 * 
	 * @return A list of generic element, here I just put Object there which can hold any type of the element.
	 */
	public JSONObject action(JSONObject jsonObj);
	
}
