package controller.comparer.xmi;

import java.util.ArrayList;

public final class Utility {

	public static String getPrimitiveNameById(ArrayList<XmiTypeElement> array, String id) {
		
		for(XmiTypeElement element : array) {
			if (element.getId().equals(id)) {
				return element.getName();
			}
		}
		return "";
	}

	public static String getClassNameById(ArrayList<XmiClassElement> array, String id) {
		
		for(XmiClassElement element : array) {
			if (element.getId().equals(id)) {
				return element.getName();
			}
		}
		return "";
	}

	public static String getBaseNameById(ArrayList<XmiBaseElement> array, String id) {

		for(XmiBaseElement element : array) {
			System.out.println(element.getId());
			if (element.getId().equals(id)) {
				return element.getName();
			}
		}
		return "";
	}
	
}
