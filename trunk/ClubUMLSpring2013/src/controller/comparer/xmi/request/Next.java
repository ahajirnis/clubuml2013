package controller.comparer.xmi.request;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiBaseElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiGeneralizationElement;
import controller.comparer.xmi.XmiOperationElement;
import controller.merge.xmi.xclass.XmiMergedClass;

/**
 * Next()
 * Next Request to obtain lists of Association and Generalization
 * @author Minhee Song
 *
 */

public class Next implements Request {

	private final static String TITLE_RESPONSE = "Response";
	private final static String TITLE_DIAGRAM1 = "Diagram1";
	private final static String TITLE_DIAGRAM2 = "Diagram2";
	private final static String ASSOCIATION = "Associations";
	private final static String GENERALIZATION = "Generalizations";
	private final static String ELEMENT_ID = "id";
	private final static String ELEMENT_TEXT = "text";
	
	private JSONObject response;
	
	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		response = BuildJSONStructure(jsonObj, comparer);
		return response;
	}
	
	private static JSONObject BuildJSONStructure(JSONObject jsonObj, 
			XmiClassDiagramComparer comparer) {
		JSONObject obj = new JSONObject();	
						
		HashMap<String, Object> diagram1 = new HashMap<String, Object>();
		HashMap<String, Object> diagram2 = new HashMap<String, Object>();
		JSONArray listOfGeneralization1 = new JSONArray();
		JSONArray listOfGeneralization2 = new JSONArray();
		JSONArray listOfAssociation1 = new JSONArray();
		JSONArray listOfAssociation2 = new JSONArray();
		
		for(int i=0 ; i < comparer.getSameClass().size() ; i++) {
			System.out.println("Search class: " + comparer.getSameClass().get(i).getNewName());
			XmiMergedClass sameClass = comparer.getSameClass().get(i);

			if (sameClass.getClass1() != null) {
				//GeneralizationList
				for(XmiGeneralizationElement general : sameClass.getClass1().getGeneralization()){
					JSONObject element = new JSONObject();
					element.put(ELEMENT_ID, general.getId());
					element.put(ELEMENT_TEXT, general.toString());
					listOfGeneralization1.add(element);
					System.out.println("Diagram1");
					System.out.println("id: " + general.getId());
					System.out.println("text: " + general.toString());
				}
				//AssociationList
			}
			if (sameClass.getClass2() != null) {
				//GeneralizationList
				for(XmiGeneralizationElement general : sameClass.getClass2().getGeneralization()){
					JSONObject element = new JSONObject();
					element.put(ELEMENT_ID, general.getId());
					element.put(ELEMENT_TEXT, general.toString());
					listOfGeneralization2.add(element);
					System.out.println("Diagram2");
					System.out.println("id: " + general.getId());
					System.out.println("text: " + general.toString());
				}
				//AssociationList
			}
			diagram1.put(ASSOCIATION, listOfAssociation1);
			diagram1.put(GENERALIZATION, listOfGeneralization1);
			diagram2.put(ASSOCIATION, listOfAssociation2);
			diagram2.put(GENERALIZATION, listOfGeneralization2);
		}

		obj.put(TITLE_RESPONSE, "Success");
		obj.put(TITLE_DIAGRAM1, diagram1);
		obj.put(TITLE_DIAGRAM2, diagram2);		

		return obj;
	}
}
