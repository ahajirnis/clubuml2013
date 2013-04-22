package controller.comparer.xmi.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import controller.comparer.xmi.Utility;
import controller.comparer.xmi.XmiAssociationElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.comparer.xmi.XmiGeneralizationElement;
import controller.comparer.xmi.XmiMemberEndElement;
import controller.merge.xmi.xclass.XmiMergedAssociation;
import controller.merge.xmi.xclass.XmiMergedClass;

public class Done implements Request {
	private final static String TITLE_RESPONSE = "Response";
	private final static String TITLE_DIAGRAM_1 ="Diagram1";
	private final static String TITLE_DIAGRAM_2 ="Diagram2";
	private final static String TITLE_ASSOCIATIONS ="Associations";
	private final static String TITLE_GENERALIZAITIONS ="Generalizations";

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		read(jsonObj,comparer);
		
		JSONObject response = new JSONObject();
		
		response.put(TITLE_RESPONSE, "Success");
		return response;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void read (JSONObject jsonObj, XmiClassDiagramComparer comparer )
	{
		HashMap classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		ArrayList<String> idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		
		if (idList != null) {
			for(String id: idList){
				activateGeneralization(id,comparer.getSameClass());
			}
		}
		
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		if (idList != null) {
			for(String id: idList) {
				activateGeneralization(id,comparer.getSameClass());
			}
		}
		
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		if (idList != null) {
			for(String id: idList) {
				System.out.println("TEST1 - Display assoc ID " + id);
				activateAssociation(id,comparer.getSameClass(), comparer, comparer.getClassDiagram1().getAssociationElements(), 1);
			}
		}
		
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		if (idList != null) {
			for(String id: idList) {
				System.out.println("TEST2 - Display assoc ID " + id);
				activateAssociation(id,comparer.getSameClass(), comparer, comparer.getClassDiagram2().getAssociationElements(), 2);
			}
		}
	}
	
	private void activateGeneralization(String id, ArrayList<XmiMergedClass> mergedList)
	{
		for(XmiMergedClass mergedClass: mergedList){
			
			if (mergedClass.getClass1() != null) {			
				if (mergedClass.getClass1().getGeneralization() != null) {
					for(XmiGeneralizationElement generalization : mergedClass.getClass1().getGeneralization()) {
						if(generalization.getId().equals(id)) {
							if (!mergedClass.getGeneralizations().contains(generalization)) {
								mergedClass.getGeneralizations().add(generalization);
							}
						}
					}
				}
			}
			
			if (mergedClass.getClass2() != null) {			
				if (mergedClass.getClass2().getGeneralization() != null) {
					for(XmiGeneralizationElement generalization : mergedClass.getClass2().getGeneralization()) {
						if(generalization.getId().equals(id)){
							if (!mergedClass.getGeneralizations2().contains(generalization)) {
								mergedClass.getGeneralizations2().add(generalization);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Create the association elements that'll be used when generating the uml files
	 * @param id
	 * @param mergedList
	 * @param comparer
	 * @param associationList
	 * @param diagramNumber - stores the diagram number this method is invoked from
	 */
	private void activateAssociation(String id, ArrayList<XmiMergedClass> mergedList, XmiClassDiagramComparer comparer, List<XmiAssociationElement> associationList, int diagramNumber ) {
		
		XmiClassElement class1 = null;
		XmiClassElement class2 = null;
		XmiAssociationElement association = Utility.getAssociationById((ArrayList<XmiAssociationElement>) associationList, id);
		
		// Kick out if we can't find the association
		if (association == null) {
			return;
		}
		
		// Only dealing with associations without classifiers
		if (association.getMemberEnds().size() != 2) {
			return;
		}
		
		XmiMemberEndElement end1 = association.getMemberEnds().get(0);
		XmiMemberEndElement end2 = association.getMemberEnds().get(1);

		for (XmiMergedClass mergedClass : mergedList) {
				
			XmiClassElement mergedClass1 = mergedClass.getClass1();
			XmiClassElement mergedClass2 = mergedClass.getClass2();
			
			
			
			if (mergedClass1 != null) {
				if (end1.getUmlType().equals(mergedClass1.getId())) {
					class1 = mergedClass1;
				}
				
				if (end2.getUmlType().equals(mergedClass1.getId())) {
					class2 = mergedClass1;
				}
			} 
			
			// If the match was found for Class2 but Class1 exists,
			// then use Class1 data over Class2
			if (mergedClass2 != null) {
				if (end1.getUmlType().equals(mergedClass2.getId())) {
					if (mergedClass1 != null) {
						class1 = mergedClass1;
					} else {
						class1 = mergedClass2;
					}
				}
				
				if (end2.getUmlType().equals(mergedClass2.getId())) {
					if (mergedClass1 != null) {
						class2 = mergedClass1;
					} else {
						class2 = mergedClass2;
					}
				}
			}
			
			// create association since we found the target classes
			if (class1 != null && class2 != null) {
				XmiMergedAssociation mergedAssociation = new XmiMergedAssociation(association, class1.getId(), class2.getId(), class1.getName(), class2.getName(), diagramNumber);
				
				if (!comparer.getAssociationUml().contains(mergedAssociation)) {
					comparer.getAssociationUml().add(mergedAssociation);
				}
				
				return;
			}
		}
	}
}
	
	
	/*private void activateAssociation(String id, ArrayList<XmiMergedClass> mergedList){
		
		for(XmiMergedClass mergedClass : mergedList){
			for(XmiMemberEndElement member :mergedClass.getClass1().getClassifer()) {
				if(member.getAssociationId().equals(id))
					mergedClass.getAssociations().add(member.getAssociation());
			}
		}
		
		for(XmiMergedClass mergedClass: mergedList){
			for(XmiMemberEndElement member : mergedClass.getClass2().getClassifer()) {
				if(member.getAssociationId().equals(id)) {
					mergedClass.getAssociations2().add(member.getAssociation());
				}
			}
		}
	 
	}*/
	
