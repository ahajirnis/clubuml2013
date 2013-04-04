package controller.comparer.xmi.request;



import java.util.ArrayList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiOperationElement;


public class Compare implements Request{
	
	private XmiClassElement classA = null;
	private XmiClassElement classB = null;
	private ArrayList<String> atts1 = new ArrayList<String>();
	private ArrayList<String> atts2 = new ArrayList<String>();
	private ArrayList<String> atts_same = new ArrayList<String>();
	private ArrayList<String> opts1 = new ArrayList<String>();
	private ArrayList<String> opts2 = new ArrayList<String>();
	private ArrayList<String> opts_same = new ArrayList<String>();

	
	
	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		
			JSONObject response = new JSONObject();
		
			String class1Name = (String)jsonObj.get("Class1");	
			classA = Utility.getClassByName(comparer.getClassDiagram1().getClassElements(), class1Name);
			String class2Name = (String)jsonObj.get("Class2");			
			classB = Utility.getClassByName(comparer.getClassDiagram2().getClassElements(), class2Name);
			
						
		    try {			
				if(classA != null && classB != null){
					//success
					
					//read attributes from Class1
					for(XmiAttributeElement a : classA.getAttributes()){
						atts1.add(a.getName());
					}
					
					//read attributes from Class2
					for(XmiAttributeElement a : classB.getAttributes()){
						atts1.add(a.getName());
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
						
						response.put("same attributes", atts_same);
						response.put("unique attributes in Class1",atts1);
						response.put("unique attributes in Class2", atts2);
					}
					
					//get same and different operations in two classes
					for(XmiOperationElement o1 : classA.getOperations()){
						if(classB.getOperations().contains(o1)){
							opts_same.add(o1.getName());
							opts1.remove(o1.getName());
							opts2.remove(o1.getName());
						}
						
						response.put("same operations", opts_same);
						response.put("unique operations in Class1",opts1);
						response.put("unique operations in Class2", opts2);
					} 	
					
					response.put("response", "successful");
					
				}else{
					//fail
					response.put("response", "fail");
					response.put("same attributes", null);
					response.put("unique attributes in Class1",null);
					response.put("unique attributes in Class2", null);
					response.put("same operations", null);
					response.put("unique operations in Class1",null);
					response.put("unique operations in Class2", null);
					
				}
				
			}catch(Exception e){
				
			}
			
			
		return response;
			
	}
	
	

}
