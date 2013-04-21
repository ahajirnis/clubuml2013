package controller.comparer.xmi.request;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiAssociationElement;
import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiGeneralizationElement;
import controller.comparer.xmi.XmiMemberEndElement;
import controller.merge.xmi.xclass.XmiMergedClass;

public class Done implements Request {
	private final static String TITLE_RESPONSE = "Response";
	private final static String TITLE_DIAGRAM_1 ="Diagram1";
	private final static String TITLE_DIAGRAM_2 ="Diagram2";
	private final static String TITLE_ASSOCIATIONS ="Associations";
	private final static String TITLE_GENERALIZAITIONS ="Generalizations";

	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		read(jsonObj,comparer);
		
		JSONObject response = new JSONObject();
		
		response.put(TITLE_RESPONSE, "Success");
		return response;
	}
	private void read (JSONObject jsonObj, XmiClassDiagramComparer comparer )
	{
		HashMap classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		ArrayList<String> idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		
		if (idList != null) {
			for(String id: idList){
				System.out.println("TEST1 - Display generalization ID " + id);
				activateGeneralization(id,comparer.getSameClass());
			}
		}
		
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		if (idList != null) {
			for(String id: idList) {
				System.out.println("TEST2 - Display generalization ID " + id);
				activateGeneralization(id,comparer.getSameClass());
			}
		}
		
		/*classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		for(String id: idList)
			activateAssociation(id,comparer.getSameClass());
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		for(String id: idList)
			activateAssociation(id,comparer.getSameClass());*/
	}
	
	private void activateGeneralization(String id, ArrayList<XmiMergedClass> mergedList)
	{
		for(XmiMergedClass mergedClass: mergedList){
			
			ArrayList<XmiGeneralizationElement> genList = new ArrayList<XmiGeneralizationElement>();
			ArrayList<XmiGeneralizationElement> genList2 = new ArrayList<XmiGeneralizationElement>();
			
			if (mergedClass.getClass1() != null) {			
				if (mergedClass.getClass1().getGeneralization() != null) {
					for(XmiGeneralizationElement generalization : mergedClass.getClass1().getGeneralization()) {
						if(generalization.getId().equals(id)) {
							//genList.add(generalization);
							if (!mergedClass.getGeneralizations().contains(generalization)) {
								mergedClass.getGeneralizations().add(generalization);
							}
						}
					}
				}
				//mergedClass.setGeneralizations(genList);
			}
			
			if (mergedClass.getClass2() != null) {			
				if (mergedClass.getClass2().getGeneralization() != null) {
					for(XmiGeneralizationElement generalization : mergedClass.getClass2().getGeneralization()) {
						if(generalization.getId().equals(id)){
							//genList2.add(generalization);
							if (!mergedClass.getGeneralizations2().contains(generalization)) {
								mergedClass.getGeneralizations2().add(generalization);
							}
						}
					}
				}
				//mergedClass.setGeneralizations2(genList2);
			}
		}
	}
	
	private void activateAssociation(String id, ArrayList<XmiMergedClass> mergedList){
		for(XmiMergedClass mergedClass: mergedList){
			for(XmiMemberEndElement member
					:mergedClass.getClass1().getClassifer())
				if(member.getAssociationId().equals(id))
					mergedClass.getAssociations().add(member.getAssociation());
		}
			for(XmiMergedClass mergedClass: mergedList){
				for(XmiMemberEndElement member
						:mergedClass.getClass2().getClassifer())
					if(member.getAssociationId().equals(id))
						mergedClass.getAssociations2().add(member.getAssociation());
			}

	}
	
	

}
