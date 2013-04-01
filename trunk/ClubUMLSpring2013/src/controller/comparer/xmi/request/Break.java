package controller.comparer.xmi.request;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.comparer.xmi.XmiClassElement;
import controller.merge.xmi.xclass.XmiMergedClass;

public class Break implements Request{

	@Override
	public JSONObject request(JSONObject jsonObj,
			XmiClassDiagramComparer comparer) {
		
		JSONObject response = new JSONObject();
		
		//gets the name
		String name = (String)jsonObj.get("Class");
		
		//read in arraylist for comparison
		ArrayList<XmiMergedClass> sameClass = comparer.getSameClass();
		
		//loop through arraylist to find match
		for (int i = 0; i < sameClass.size(); i++) {
			XmiMergedClass o = comparer.getSameClass().get(i);
			if (o.getNewName().equals(name)) {
				
				//check if it belongs to class 1 and/or class 2
				if (o.getClass1() != null) {
					ArrayList<XmiClassElement> list = comparer.getUniqueClass1();
					list.add(o.getClass1());
					comparer.setUniqueClass1(list);
				}
				if (o.getClass2() != null) {
					ArrayList<XmiClassElement> list = comparer.getUniqueClass2();
					list.add(o.getClass2());
					comparer.setUniqueClass2(list);
				}
				
				//remove it from arraylist
				sameClass.remove(i);
				comparer.setSameClass(sameClass);
				response.put("Response", "Sucess");
			}
		}
		return response;
	}

}
