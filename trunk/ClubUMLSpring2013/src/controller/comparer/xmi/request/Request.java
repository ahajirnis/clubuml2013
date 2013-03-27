package controller.comparer.xmi.request;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiClassDiagramComparer;

/**
 * Request interface as the API for all request for Class diagram comparer
 * @author RD2012
 *
 */
public interface Request {
	
	/**
	 * 
	 * @param jsonObj
	 * @param comparer
	 * @return JSONObject (see JSON structure document for details)
	 */
	public JSONObject request(JSONObject jsonObj, XmiClassDiagramComparer comparer);
}
