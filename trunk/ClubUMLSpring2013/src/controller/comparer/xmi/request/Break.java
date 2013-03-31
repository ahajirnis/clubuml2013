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
		
		String name = (String)jsonObj.get("Class");
		ArrayList<XmiMergedClass> sameClass = comparer.getSameClass();
		
		for (int i = 0; i < sameClass.size(); i++) {
			XmiMergedClass o = comparer.getSameClass().get(i);
			if (o.getNewName() == name) {
				if (o.getClass1() != null) {
					ArrayList<XmiClassElement> list = comparer.getUniqueClass1();
					list.add(o.getClass1());
					comparer.setUniqueClass1(list);
					sameClass.remove(i);
					comparer.setSameClass(sameClass);
				}
				if (o.getClass2() != null) {
					ArrayList<XmiClassElement> list = comparer.getUniqueClass2();
					list.add(o.getClass2());
					comparer.setUniqueClass2(list);
					sameClass.remove(i);
					comparer.setSameClass(sameClass);
				}
				response.put("Sucess", "Sucess");
			}
		}
		return response;
	}

}
