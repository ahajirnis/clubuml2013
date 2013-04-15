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
		jsonObj.put(TITLE_RESPONSE, "Success");
		return jsonObj;
	}
	private void read (JSONObject jsonObj, XmiClassDiagramComparer comparer )
	{
		HashMap classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		ArrayList<String> idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		for(String id: idList)
			activateGeneralization(id,comparer.getSameClass());
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_GENERALIZAITIONS);
		for(String id: idList)
			activateGeneralization(id,comparer.getSameClass());
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_1);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		for(String id: idList)
			activateAssociation(id,comparer.getSameClass());
		classInfo=(HashMap)jsonObj.get(TITLE_DIAGRAM_2);
		idList=(ArrayList<String>)classInfo.get(TITLE_ASSOCIATIONS);
		for(String id: idList)
			activateAssociation(id,comparer.getSameClass());
	
	
	}
	private void activateGeneralization(String id, ArrayList<XmiMergedClass> mergedList)
	{
		for(XmiMergedClass mergedClass: mergedList){
			for(XmiGeneralizationElement generalization
					:mergedClass.getClass1().getGeneralization())
				if(generalization.getId().equals(id))
					mergedClass.getGeneralizations().add(generalization);					
		}
		for(XmiMergedClass mergedClass: mergedList){
			for(XmiGeneralizationElement generalization
					:mergedClass.getClass2().getGeneralization())
				if(generalization.getId().equals(id))
					mergedClass.getGeneralizations2().add(generalization);
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
