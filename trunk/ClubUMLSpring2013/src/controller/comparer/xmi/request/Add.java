package controller.comparer.xmi.request;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.Utility;
import controller.merge.xmi.xclass.XmiMergedClass;

public class Add implements Request {

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
		
		XmiMergedClass mergedClass = new XmiMergedClass();
		String className;
		XmiClassElement classElement;
		
		JSONObject obj = new JSONObject();		
		obj.put("Response","Success");

		try {   
			if (jsonObj.get(CLASS_DIAGRAM_1) != null) {
				className = (String)jsonObj.get(CLASS_DIAGRAM_1);
			    classElement = Utility.getClassByName(comparer.getUniqueClass1(), className);
			    mergedClass.setNewName(className);
			    obj.put(CLASS_DIAGRAM_1, className);			    
			    if(classElement != null) {
				    mergedClass.setClass1(classElement);
				    if(classElement.getAttributes() != null) {
				    	mergedClass.setAttributes(classElement.getAttributes());
				    	obj.put(ATTRIBUTE,classElement.getAttributes());
				    } else {
				    	obj.put(ATTRIBUTE,null);
				    }
				    if(classElement.getOperations() != null) {
				    	mergedClass.setOperations(classElement.getOperations());
				    	obj.put(OPERATION,classElement.getOperations());
				    } else {
				    	obj.put(OPERATION,null);
				    }
			    } else {
			    	obj.put(ATTRIBUTE,null);
			 	    obj.put(OPERATION,null);
			    }
			} else if (jsonObj.get(CLASS_DIAGRAM_2) != null) {
				className = (String)jsonObj.get(CLASS_DIAGRAM_2);
				classElement = Utility.getClassByName(comparer.getUniqueClass2(), className);
				mergedClass.setNewName(className);
				obj.put(CLASS_DIAGRAM_2, className);
				if(classElement != null) {
					mergedClass.setClass2(classElement);
				    if(classElement.getAttributes() != null) {
				    	mergedClass.setAttributes2(classElement.getAttributes());
				    	obj.put(ATTRIBUTE,classElement.getAttributes());
				    } else {
				    	obj.put(ATTRIBUTE,null);
				    }
				    if(classElement.getOperations() != null) {
				    	mergedClass.setOperations2(classElement.getOperations());
				    	obj.put(OPERATION,classElement.getOperations());
				    } else {
				    	obj.put(OPERATION,null);
				    }
				} else {
					obj.put(ATTRIBUTE,null);
			 	    obj.put(OPERATION,null);
				}
			}
		} catch (NullPointerException npe) {   
			System.out.println(npe);
		} 	
		comparer.getSameClass().add(mergedClass);
		return obj;
	}
}
