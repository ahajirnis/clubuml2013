package controller.comparer.xmi.request;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.merge.xmi.xclass.XmiMergedClass;
import controller.similaritycheck.SimilarityCheck;

/**
 * Refresh()
 * Read the elements in three lists
 * @author Dong Guo
 *
 */

public class Refresh implements Request {
	
	//  Titles in our JSON response
	private final static String TITLE_RESPONSE = "Response";
	private final static String TITLE_DIAGRAM1 = "Diagram1";
	private final static String TITLE_DIAGRAM2 = "Diagram2";
	private final static String TITLE_SAME = "Same";
	private final static String TITLE_SIMILAR = "Similar";

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		
		// Implements the refresh JSON structure
		
		JSONObject response = new JSONObject();
		
		ArrayList<String> diagram1 = new ArrayList<String>();
		ArrayList<String> diagram2 = new ArrayList<String>();
		ArrayList<String> same = new ArrayList<String>();
		ArrayList<String> similar = new ArrayList<String>();
		
		//  Read elements in uniqueClass1
		for(XmiClassElement element : comparer.getUniqueClass1()){
			diagram1.add(element.getName());
		}
		
		//  Read elements in uniqueClass2
		for(XmiClassElement element : comparer.getUniqueClass2()){
			diagram2.add(element.getName());
		}
		
		//  Read elements in sameClass
		for(XmiMergedClass element : comparer.getSameClass()){
			same.add(element.getNewName());
		}
		
		//  Run SimilarityCheck to find similar class names
		for(String d1: diagram1){
			for (String d2: diagram2) {
				SimilarityCheck simCheck = new SimilarityCheck(d1, d2);
				if (simCheck.doSimilarityCheck()){
					similar.add(d1 + " is similar to " + d2);
				}
			}
		}
		
		//  Input three ArrayLists in our return obj
		response.put(TITLE_RESPONSE, "Success");
		response.put(TITLE_DIAGRAM1, diagram1);
		response.put(TITLE_DIAGRAM2, diagram2);
		response.put(TITLE_SAME, same);
		response.put(TITLE_SIMILAR, similar);
		
		return response;
	}
}
