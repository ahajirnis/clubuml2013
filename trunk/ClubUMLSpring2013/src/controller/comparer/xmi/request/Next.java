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
		JSONArray listOfGeneralization1 = new JSONArray();
		JSONArray listOfGeneralization2 = new JSONArray();
		JSONArray listOfAssociation1;
		JSONArray listOfAssociation2;
						
		listOfAssociation1 = accociationStructure(comparer.getClassDiagram1(), comparer.getSameClass());
		listOfAssociation2 = accociationStructure(comparer.getClassDiagram2(), comparer.getSameClass());

		for(int i=0 ; i < comparer.getSameClass().size() ; i++) {
			XmiMergedClass sameClass = comparer.getSameClass().get(i);
			if (sameClass.getClass1() != null) {
				String parentClass;
				String childClass;
				String elementText; 
				//GeneralizationList
				for(XmiGeneralizationElement general : sameClass.getClass1().getGeneralization()){
					childClass = sameClass.getClass1().getName();
					parentClass = Utility.getClassNameById(comparer.getUniqueClass1(), general.getParent());
					elementText = childClass + " inherits " + parentClass;
						
					//check if parent exist in the arrayList of XmiMergedClass 
					if(!Utility.checkExistXmiMergedClass(comparer.getSameClass(), parentClass)) {
						JSONObject element = new JSONObject();
						element.put(ELEMENT_ID, general.getId());
						element.put(ELEMENT_TEXT, elementText);
						listOfGeneralization1.add(element);
					}	
				}
			}
			//check if the class2 of generalization already exist 
			if (sameClass.getClass2() != null && !sameClass.getClass1().equals(sameClass.getClass2())) {
				String parentClass;
				String childClass;
				String elementText; 
				//GeneralizationList
				for(XmiGeneralizationElement general : sameClass.getClass2().getGeneralization()){
					childClass = sameClass.getClass2().getName();
					parentClass = Utility.getClassNameById(comparer.getUniqueClass2(), general.getParent());
					elementText = childClass + " inherits " + parentClass;
					//check if parent exist in the arrayList of XmiMergedClass 
					if(!Utility.checkExistXmiMergedClass(comparer.getSameClass(), parentClass)) {
						JSONObject element = new JSONObject();
						element.put(ELEMENT_ID, general.getId());
						element.put(ELEMENT_TEXT, elementText);
						listOfGeneralization2.add(element);
					}	
				}
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
		    AggregationValues aggregate;
		    JSONObject element = new JSONObject();

		    if(association.getMemberEnds().size() == 2) {
			    XmiMemberEndElement end1 = association.getMemberEnds().get(0);
			    XmiMemberEndElement end2 = association.getMemberEnds().get(1);
	
			    class1 = Utility.getClassById(classDiagram.getClassElements(), end1.getTypeName());
			    class2 = Utility.getClassById(classDiagram.getClassElements(), end2.getTypeName());
			    aggregate = end1.getAggregation();
			    class1Name = class1.getName();
			    class2Name = class2.getName();

			    //check if the class exists in the arrayList of XmiMergedClass 
				if(Utility.checkExistXmiMergedClass(sameClass, class1Name)
						&& Utility.checkExistXmiMergedClass(sameClass, class2Name)) {	
					String strAgrregation = aggregationValue(aggregate);
					String elementText = class1Name + strAgrregation + class2Name;
					element.put(ELEMENT_ID, association.getId());
					element.put(ELEMENT_TEXT, elementText);
			        if (association.getNavigable().contains(class1.getId())) {
			        	String strNavigable = class1Name + " is navigable to " + class2Name;
			        	element.put(ELEMENT_ID, association.getId());
						element.put(ELEMENT_TEXT, strNavigable);
			        }
				}
		    } else if (association.getMemberEnds().size() == 1) {
		    	XmiMemberEndElement end1 = association.getMemberEnds().get(0);
	
			    class1 = Utility.getClassById(classDiagram.getClassElements(), end1.getTypeName());
			    aggregate = end1.getAggregation();
			    class1Name = class1.getName();

			    if(!class2Name.isEmpty()) {
				    //check if the class exists in the arrayList of XmiMergedClass 
					if(Utility.checkExistXmiMergedClass(sameClass, class1Name)
						&& Utility.checkExistXmiMergedClass(sameClass, class2Name)) {	
						String strAgrregation = aggregationValue(aggregate);
						String elementText = class1Name + strAgrregation + class2Name;
						element.put(ELEMENT_ID, association.getId());
						element.put(ELEMENT_TEXT, elementText);
					}
			        if (association.getNavigable().contains(class1.getId())) {
			        	String strNavigable = class1Name + " is navigable to " + class2Name;
			        	element.put(ELEMENT_ID, association.getId());
						element.put(ELEMENT_TEXT, strNavigable);
			        }
			    }
		    } 
		    if(!element.isEmpty())
		    	listOfAssociation.add(element);
		}	
		return listOfAssociation;
	}
}
