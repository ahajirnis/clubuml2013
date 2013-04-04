package controller.comparer.xmi.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiOperationElement;
import controller.merge.xmi.xclass.XmiMergedClass;

/**
 * Refresh()
 * Read the elements in three lists
 * @author Prashant Shukla
 *
 */

public class Consolidate implements Request {

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {

		final String JSON_REQ 				= "Request";
		final String JSON_REQ_CONSOLIDATE	= "Consolidate";
		
		final String JSON_REQ_CONSOLIDATE_CLASS_1 = "Class1";
		final String JSON_REQ_CONSOLIDATE_CLASS_2 = "Class2";
		final String JSON_REQ_CONSOLIDATE_CLASS_NAME = "class";
		final String JSON_REQ_CONSOLIDATE_CLASS_ATTR = "Attribute";
		final String JSON_REQ_CONSOLIDATE_CLASS_OPER = "Operation";
		final String JSON_REQ_CONSOLIDATE_NEW_CLASS = "Name";
		JSONObject response = new JSONObject();
		try {
			
	
			String requestVal = (String) jsonObj.get(JSON_REQ);
			
			// Verify we received the correct request message
			if (requestVal.equals(JSON_REQ_CONSOLIDATE)){
				
				
				// Extracting the values from class1
				HashMap class1_info = (HashMap)jsonObj.get(JSON_REQ_CONSOLIDATE_CLASS_1);
				
				String class1Name = (String)class1_info.get(JSON_REQ_CONSOLIDATE_CLASS_NAME);
				//XmiClassElement classElement1 =  new XmiClassElement("",class1Name,"","");
			
				//Get the list of attributes
				LinkedList attrList1 = (LinkedList)class1_info.get(JSON_REQ_CONSOLIDATE_CLASS_ATTR);
				
				for (int i =0; i < attrList1.size(); i++) {
					Object Attrval = attrList1.get(i);
					//classElement1.addAttribute(new XmiAttributeElement("",Attrval.toString(),"",""));
				}
				
				// Get the list of operation
				LinkedList operlist1 = (LinkedList)class1_info.get(JSON_REQ_CONSOLIDATE_CLASS_OPER);
				
				// Get the list of attributes
				for (int i =0; i < operlist1.size(); i++) {
					Object operval  = operlist1.get(i);
					//classElement1.addOperation(new XmiOperationElement("",operval.toString(),"",""));
				}
				//comparer.setUniqueClass1(classElement1);
				// Extracting the values from Class2 
				HashMap class2_info = (HashMap)jsonObj.get(JSON_REQ_CONSOLIDATE_CLASS_2);
				String class2Name = (String)class2_info.get(JSON_REQ_CONSOLIDATE_CLASS_NAME);			
				//XmiClassElement classElement2 =  new XmiClassElement("",class2Name,"","");
				// Get the list of attributes
				LinkedList attrlist2 = (LinkedList)class2_info.get(JSON_REQ_CONSOLIDATE_CLASS_ATTR);
				
				for (int i =0; i < attrlist2.size(); i++) {
					Object Attrval = attrlist2.get(i);
					//classElement2.addAttribute(new XmiAttributeElement("",Attrval.toString(),"",""));
				}
				
				// Get the list of operation
				LinkedList operlist2 = (LinkedList)class2_info.get(JSON_REQ_CONSOLIDATE_CLASS_OPER);
				
				// Get the list of attributes
				for (int i =0; i < operlist2.size(); i++) {
					Object operval = operlist2.get(i);
					//classElement2.addOperation(new XmiOperationElement("",operval.toString(),"",""));
				}
				//comparer.setUniqueClass1(classElement2);
				response.put("Response", "Success");
			}
			
		
		} catch (Exception ex) {
			// Set up failed response
			response.put("Response", "Failed");
		}
		return response;

	}
	
	
	
	
}