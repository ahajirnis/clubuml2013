package controller.comparer.xmi.request;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiOperationElement;
import controller.merge.xmi.xclass.XmiMergedClass;

/**
 * Add()
 * Add Request
 * @author Minhee Song
 *
 */

public class Add implements Request {

	private final static String TITLE_RESPONSE = "Response";
	private final static String CLASS_DIAGRAM_1 = "Class1";
	private final static String CLASS_DIAGRAM_2 = "Class2";
	private final static String OPERATION = "Operations";
	private final static String ATTRIBUTE = "Attributes";
	
	private JSONObject response;
	
	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		response = BuildJSONStructure(jsonObj, comparer);
		return response;
	}
	
	private static JSONObject BuildJSONStructure(JSONObject jsonObj, 
			XmiClassDiagramComparer comparer) {
		String className;
		XmiClassElement classElement;
		
		JSONObject obj = new JSONObject();				
		ArrayList<String> attribute = new ArrayList<String>();
		ArrayList<String> operation = new ArrayList<String>();

		try {   
			if (jsonObj.get(CLASS_DIAGRAM_1) != null) {
				className = (String)jsonObj.get(CLASS_DIAGRAM_1);
			    classElement = Utility.getClassByName(comparer.getUniqueClass1(), className);
			    obj.put(CLASS_DIAGRAM_1, className);			 		    
			    if(classElement != null) {
					for(XmiAttributeElement attr : classElement.getAttributes()){
						attribute.add(attr.getName());
					}
					for(XmiOperationElement oper : classElement.getOperations()){
						operation.add(oper.getName());
					} 
				    //obj.put(ATTRIBUTE,classElement.getAttributes());
					//obj.put(OPERATION,classElement.getOperations());
				    obj.put(ATTRIBUTE, attribute);
				    obj.put(OPERATION, operation);
				    obj.put(TITLE_RESPONSE,"Success");
			    } else {
			    	obj.put(ATTRIBUTE,null);
			 	    obj.put(OPERATION,null);
			 	    obj.put(TITLE_RESPONSE,"Fail");
			    }
			} else if (jsonObj.get(CLASS_DIAGRAM_2) != null) {
				className = (String)jsonObj.get(CLASS_DIAGRAM_2);
				classElement = Utility.getClassByName(comparer.getUniqueClass2(), className);
				obj.put(CLASS_DIAGRAM_2, className);
				if(classElement != null) {
					for(XmiAttributeElement attr : classElement.getAttributes()){
						attribute.add(attr.getName());
					}
					for(XmiOperationElement oper : classElement.getOperations()){
						operation.add(oper.getName());
					}
				    //obj.put(ATTRIBUTE,classElement.getAttributes());
					//obj.put(OPERATION,classElement.getOperations());
				    obj.put(ATTRIBUTE, attribute);
				    obj.put(OPERATION, operation);
				    obj.put(TITLE_RESPONSE,"Success");
				} else {
					obj.put(ATTRIBUTE,null);
			 	    obj.put(OPERATION,null);
			 	   obj.put(TITLE_RESPONSE,"Fail");
				}
			} else {
				obj.put(TITLE_RESPONSE,"Fail");
			}
		} catch (NullPointerException npe) {   
			obj.put(TITLE_RESPONSE,"Fail");
			System.out.println("Add request : " + npe);
		} 	
		return obj;
	}
}
