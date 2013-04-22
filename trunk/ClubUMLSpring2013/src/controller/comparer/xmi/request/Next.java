package controller.comparer.xmi.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.comparer.xmi.AggregationValues;
import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiAssociationElement;
import controller.comparer.xmi.XmiAttributeElement;
import controller.comparer.xmi.XmiBaseElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassDiagramParser;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiGeneralizationElement;
import controller.comparer.xmi.XmiMemberEndElement;
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
		JSONArray listOfAssociation1;
		JSONArray listOfAssociation2;
		JSONArray listOfGeneralization1;
		JSONArray listOfGeneralization2;
		
		listOfAssociation1 = accociationStructure(comparer.getClassDiagram1(), comparer.getSameClass());
		listOfAssociation2 = accociationStructure(comparer.getClassDiagram2(), comparer.getSameClass());		
		checkSameElement(listOfAssociation1, listOfAssociation2);

		listOfGeneralization1 = generalizationStructure(comparer.getClassDiagram1(), comparer.getSameClass());
		listOfGeneralization2 = generalizationStructure(comparer.getClassDiagram2(), comparer.getSameClass());
		checkSameElement(listOfGeneralization1, listOfGeneralization2);	

		diagram1.put(GENERALIZATION, listOfGeneralization1);
		diagram1.put(ASSOCIATION, listOfAssociation1);

		diagram2.put(GENERALIZATION, listOfGeneralization2);
		diagram2.put(ASSOCIATION, listOfAssociation2);

		obj.put(TITLE_RESPONSE, "Success");
		obj.put(TITLE_DIAGRAM1, diagram1);
		obj.put(TITLE_DIAGRAM2, diagram2);		
		return obj;
	}
	
	private static void checkSameElement(JSONArray list1, JSONArray list2) {
		for(int i=0 ; i < list1.size(); i++) {
			HashMap<String, Object> association1 = (HashMap<String, Object>) list1.get(i);
			String text1 = (String) association1.get(ELEMENT_TEXT);
			for(int j=0 ; j < list2.size(); j++) {
				HashMap<String, Object> association2 = (HashMap<String, Object>) list2.get(j);
				String text2 = (String) association2.get(ELEMENT_TEXT);
				if(text1.equals(text2)) {
					list2.remove(j);
				}
			}
		}
	}
	
	private static String aggregationValue(AggregationValues aggregate) {
	    String valueText;
        if(aggregate == AggregationValues.SHARED) {
        	valueText = " has ";
        } else if (aggregate == AggregationValues.COMPOSITE) {
        	valueText = " contains ";
        } else {
        	valueText = " associated to ";
        }
        return valueText;
	}
	
	private static JSONArray accociationStructure(XmiClassDiagramParser classDiagram,ArrayList<XmiMergedClass> sameClass) {
		JSONArray listOfAssociation = new JSONArray();
		
		String class1Name = "";
	    String class2Name = "";
		for (XmiAssociationElement association : classDiagram.getAssociationElements()) {
		    XmiClassElement class1; 
		    XmiClassElement class2;
		    AggregationValues aggregate1;
		    AggregationValues aggregate2;

		    if(association.getMemberEnds().size() == 2) {
			    XmiMemberEndElement end1 = association.getMemberEnds().get(0);
			    XmiMemberEndElement end2 = association.getMemberEnds().get(1);
			    
			    System.out.println("2 Ends in assocation: " + end1.getTypeName() + " - " + end2.getTypeName());
			
			    class1 = Utility.getClassById(classDiagram.getClassElements(), end1.getUmlType());
			    class2 = Utility.getClassById(classDiagram.getClassElements(), end2.getUmlType());
			    
			    // Testing
			    if (class1 == null) {
			    	 System.out.println("CLASS1: NULL CLASS");
			    }
			    
			    if (class2 == null) {
			    	 System.out.println("CLASS2: NULL CLASS");
			    }
			    // For any reason the class doesn't exist, skip this association
			    // as it will not work.
			    if (class1 == null || class2 == null) {
			    	continue;
			    } else {
			    	  System.out.println("2 Ends in assocation: NULL CLASS");
			    }
			    
			    aggregate1 = end1.getAggregation();
			    aggregate2 = end2.getAggregation();
			    class1Name = class1.getName();
			    class2Name = class2.getName();
			    String strAssociation;
 
			    System.out.println("Classes: " + class1Name + " - " + class2Name);
			    //check if the class exists in the arrayList of XmiMergedClass 
				if(Utility.getXmiMergedClassByClassId(sameClass, class1.getId(), 0) != null
						&& Utility.getXmiMergedClassByClassId(sameClass, class2.getId(), 0) != null) {

					strAssociation = aggregationValue(aggregate1);
					String elementText = class2Name + strAssociation + class1Name + ". ";

				    strAssociation = aggregationValue(aggregate2);
					elementText = elementText + "<br> " + class1Name + strAssociation + class2Name + ". ";

			        /*if (association.getNavigable().contains(end1.getId())) {
			        	elementText = elementText + "<br> " + class1Name + " is navigable from " + class2Name + ". ";
			        }
			        
			        if (association.getNavigable().contains(end2.getId())) {
			        	elementText = elementText + "<br> " + class2Name + " is navigable from " + class1Name + ". ";
			        }
			        */
			        JSONObject elementAggr = new JSONObject();
					elementAggr.put(ELEMENT_ID, association.getId());
					elementAggr.put(ELEMENT_TEXT, elementText);
				    listOfAssociation.add(elementAggr);
				}
		    } else if (association.getMemberEnds().size() == 1) {
		    	// Related to classifier, but not supported yet
		    	XmiMemberEndElement end = association.getMemberEnds().get(0);

			    class1 = Utility.getClassById(classDiagram.getClassElements(), end.getTypeName());

			    aggregate1 = end.getAggregation();
			    class1Name = class1.getName();

			    String strAssociation;
			    String elementText;

			    if(!class1Name.isEmpty()) {
				    //check if the class exists in the arrayList of XmiMergedClass 
					if(Utility.checkExistXmiMergedClass(sameClass, class1Name)) {	

						strAssociation = aggregationValue(aggregate1);
						elementText = class1Name + strAssociation + class1Name + ".";

						if (association.getNavigable().contains(end.getId())) {
							elementText = "<br> " + elementText + class1Name + " is navigable from " + class1Name + ". ";
				        }

						JSONObject elementAggr1 = new JSONObject();
						elementAggr1.put(ELEMENT_ID, association.getId());
						elementAggr1.put(ELEMENT_TEXT, elementText);
						listOfAssociation.add(elementAggr1);
					}
			    }
		    } 
		}	
		return listOfAssociation;
	}
	
	private static JSONArray generalizationStructure(XmiClassDiagramParser classDiagram,ArrayList<XmiMergedClass> sameClass) {
		JSONArray listOfGeneralization = new JSONArray();
		
		for(int i=0 ; i < sameClass.size() ; i++) {
			XmiMergedClass mergedClass = sameClass.get(i);
			if (mergedClass.getClass1() != null) {

				//GeneralizationList
				for(XmiGeneralizationElement general : mergedClass.getClass1().getGeneralization()){
					String childClass = mergedClass.getClass1().getName();
					String parentClass = Utility.getClassNameById((ArrayList<XmiClassElement>) classDiagram.getClassElements(), general.getParent());
					
					XmiClassElement parentElement = Utility.getClassById(classDiagram.getClassElements(), general.getParent());	
					
					
					// skip to next element if parent element doesn't exist
					if (parentElement == null ) {
						continue;
					}
					
					XmiMergedClass parentMergedClass = Utility.getXmiMergedClassByClassId(sameClass, parentElement.getId(), 1);
					
					if (parentMergedClass == null) {
						continue;
					}
					
					String parentName;
					
					if (parentMergedClass.getNewName().length() > 0) {
						parentName = parentMergedClass.getNewName();
					} else {
						parentName = parentClass;
					}
					
					String elementText = childClass + " inherits " + parentName;
					
					System.out.println("Add Gen1: " + elementText);
					JSONObject element = new JSONObject();
					element.put(ELEMENT_ID, general.getId());
					element.put(ELEMENT_TEXT, elementText);
					listOfGeneralization.add(element);
						
				}
			}
			
			if (mergedClass.getClass2() != null) {

				for(XmiGeneralizationElement general : mergedClass.getClass2().getGeneralization()){
					String childClass = mergedClass.getClass2().getName();
					String parentClass = Utility.getClassNameById((ArrayList<XmiClassElement>) classDiagram.getClassElements(), general.getParent());
	
					XmiClassElement parentElement = Utility.getClassById(classDiagram.getClassElements(), general.getParent());
					
					// skip to next element if parent element doesn't exist
					if (parentElement == null) {
						continue;
					}
					
					XmiMergedClass parentMergedClass = Utility.getXmiMergedClassByClassId(sameClass, parentElement.getId(), 2);
					
					if (parentMergedClass == null) {
						continue;
					}
					
					String parentName;
					
					if (parentMergedClass.getNewName().length() > 0) {
						parentName = parentMergedClass.getNewName();
					} else {
						parentName = parentClass;
					}
					
					String elementText = childClass + " inherits " + parentName;
					System.out.println("Add Gen2: " + elementText);
					JSONObject element = new JSONObject();
					element.put(ELEMENT_ID, general.getId());
					element.put(ELEMENT_TEXT, elementText);
					listOfGeneralization.add(element);

				}
			}
		}
		return listOfGeneralization;
	}
}
