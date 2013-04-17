package controller.comparer.xmi.request;


import java.util.ArrayList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiOperationElement;
import controller.similaritycheck.SimilarityCheck;

/**
 * Compare()
 * Compare Attributes and Operations in two different classes
 * @author Rui Hou
 *
 */

public class Compare implements Request{
	
	private ArrayList<String> atts1 = new ArrayList<String>();
	private ArrayList<String> atts2 = new ArrayList<String>();
	private ArrayList<String> atts_same = new ArrayList<String>();
	private ArrayList<String> atts_similar = new ArrayList<String>();
	private ArrayList<String> opts1 = new ArrayList<String>();
	private ArrayList<String> opts2 = new ArrayList<String>();
	private ArrayList<String> opts_same = new ArrayList<String>();
	private ArrayList<String> opts_similar = new ArrayList<String>();
	
	private final static String JSON_REQ = "Request";
	private final static String TITLE_RESPONSE = "Response";
	private final static String JSON_REQ_COMPARE = "Compare";
	private final static String CLASS_DIAGRAM_1 = "Class1";
	private final static String CLASS_DIAGRAM_2 = "Class2";
	private final static String SAME = "same";
	private final static String SIMILAR = "similar";
	private final static String OPERATION = "Operations";
	private final static String ATTRIBUTE = "Attributes";

	
	
	@Override
	@SuppressWarnings("unchecked")
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		
			JSONObject response = new JSONObject();	
			JSONObject attributes = new JSONObject();
			JSONObject operations = new JSONObject();
			
		try {
			String requestVal = (String) jsonObj.get(JSON_REQ);
				if (requestVal.equals(JSON_REQ_COMPARE)){
					
					String class1Name = (String)jsonObj.get( CLASS_DIAGRAM_1);	
					XmiClassElement classA = Utility.getClassByName(comparer.getClassDiagram1().getClassElements(), class1Name);
					String class2Name = (String)jsonObj.get( CLASS_DIAGRAM_2);			
					XmiClassElement classB = Utility.getClassByName(comparer.getClassDiagram2().getClassElements(), class2Name);
					if(classA != null && classB != null){
						//success
						
						//read attributes from Class1
						for(XmiAttributeElement a : classA.getAttributes()){
							atts1.add(a.getName());
						}
						
						//read attributes from Class2
						for(XmiAttributeElement a : classB.getAttributes()){
							atts2.add(a.getName());
						}
						
						//read operations from Class1
						for(XmiOperationElement o : classA.getOperations()){
							opts1.add(o.getName());
						}
						
						//read operations from Class2
						for(XmiOperationElement o : classB.getOperations()){
							opts2.add(o.getName());
						}
						
						//get same and different attributes in two classes
						for(XmiAttributeElement a1 : classA.getAttributes()){
							if(classB.getAttributes().contains(a1)){
								atts_same.add(a1.getName());
								atts1.remove(a1.getName());
								atts2.remove(a1.getName());
							}
						}
						
						
						// check for similar attribute names
						for(String a1 : atts1){
							for (String a2: atts2) {
								SimilarityCheck simCheck = new SimilarityCheck(a1, a2);
								if (simCheck.doSimilarityCheck()){
									atts_similar.add(a1 + " is similar to " + a2);
								}
							}
						}
						
						attributes.put(CLASS_DIAGRAM_1, atts1);
						attributes.put(CLASS_DIAGRAM_2, atts2);
						attributes.put(SAME, atts_same);
						attributes.put(SIMILAR, atts_similar);
						
						//get same and different operations in two classes
						for(XmiOperationElement o1 : classA.getOperations()){
							if(classB.getOperations().contains(o1)){
								opts_same.add(o1.getName());
								opts1.remove(o1.getName());
								opts2.remove(o1.getName());
							}
						} 	
						
						for(String o1 : opts1){
							for (String o2: opts2) {
								SimilarityCheck simCheck = new SimilarityCheck(o1, o2);
								if (simCheck.doSimilarityCheck()){
									opts_similar.add(o1 + " is similar to " + o2);
								}
							}
						}
						
						operations.put(CLASS_DIAGRAM_1, opts1);
						operations.put(CLASS_DIAGRAM_2, opts2);
						operations.put(SAME, opts_same);
						operations.put(SIMILAR, opts_similar);
						
						
						response.put(OPERATION, operations);
						response.put(ATTRIBUTE, attributes);
						response.put(CLASS_DIAGRAM_2, class2Name);
						response.put(CLASS_DIAGRAM_1, class1Name);
						response.put(TITLE_RESPONSE, "Success");
						
					}else{
						//fail
						response.put(OPERATION, null);
						response.put(ATTRIBUTE, null);
						response.put(CLASS_DIAGRAM_2, class2Name);
						response.put(CLASS_DIAGRAM_1, class1Name);
						response.put(TITLE_RESPONSE, "Fail");
						
					}
					
				}			
			}catch(Exception e){
				//fail
				response.put(TITLE_RESPONSE, "Fail");
			}			
		
		return response;			
	}
	
}
