package controller.comparer.xmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import controller.compare.ComparerIntf;
import controller.comparer.xmi.request.Request;
import controller.merge.xmi.xclass.XmiMergedAssociation;
import controller.merge.xmi.xclass.XmiMergedClass;
import controller.upload.FileInfo;
import controller.upload.UploadProcessorFactory;
import controller.util.FileUtil;

public class XmiClassDiagramComparer implements ComparerIntf {
	private final static String REQUEST_PACKAGE = "controller.comparer.xmi.request";
	private final static String KEY_REQUEST = "Request";

	// These request will be removed in final version
	private final static String REQUEST_REFRESH = "Refresh";
	private final static String REQUEST_CONSOLIDATE = "Consolidate";
	private final static String REQUEST_ADD = "Add";
	private final static String REQUEST_BREAK = "Break";
	private final static String REQUEST_COMPARE = "Compare";

	// Parser elements that contain the metamodel
	private XmiClassDiagramParser ClassDiagram1;
	private XmiClassDiagramParser ClassDiagram2;

	// Store merged classes
	private ArrayList<XmiMergedClass> sameClass = new ArrayList<XmiMergedClass>();

	// Classes unique to diagram 1
	private ArrayList<XmiClassElement> uniqueClass1 = new ArrayList<XmiClassElement>();

	// Classes unique to diagram 2
	private ArrayList<XmiClassElement> uniqueClass2 = new ArrayList<XmiClassElement>();

	// Associations used by merge process to generate association elements
	private ArrayList<XmiMergedAssociation> associationUml = new ArrayList<XmiMergedAssociation>();
	
	/**
	 * Constructor
	 * 
	 * @param XmiFiles1
	 * @param XmiFiles2
	 */
	public XmiClassDiagramComparer(List<FileInfo> XmiFiles1,
			List<FileInfo> XmiFiles2) {

		// Process the first file
		FileInfo classDiagram1Notation = FileUtil.getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles1);
		FileInfo classDiagram1Uml = FileUtil.getFile(
				UploadProcessorFactory.UML_EXTENSION, XmiFiles1);

		ClassDiagram1 = new XmiClassDiagramParser(
				classDiagram1Uml.getDestFilePath()
						+ classDiagram1Uml.getFileName(),
				classDiagram1Notation.getDestFilePath()
						+ classDiagram1Notation.getFileName());

		// Process the second file
		FileInfo classDiagram2Notation = FileUtil.getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles2);
		FileInfo classDiagram2Uml = FileUtil.getFile(
				UploadProcessorFactory.UML_EXTENSION, XmiFiles2);

		ClassDiagram2 = new XmiClassDiagramParser(
				classDiagram2Uml.getDestFilePath()
						+ classDiagram2Uml.getFileName(),
				classDiagram2Notation.getDestFilePath()
						+ classDiagram2Notation.getFileName());

		GenerateClassLists();
	}

	/**
	 * Based on the JSON object's request, this method invokes the desired
	 * request and returns a JSON object.
	 * 
	 * JSON structure is documented in the JSON documentation.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject action(JSONObject jsonObj) {

		String request = REQUEST_PACKAGE + "." + (String) jsonObj.get(KEY_REQUEST);
		// controller.comparer.xmi.request. ...
		// Create request object via Reflection
		Request requestObj;

		try {
			requestObj = (Request) Class.forName(request).newInstance();
			return requestObj.request(jsonObj, this);
		} catch (Exception e) {
			JSONObject failResponse = new JSONObject();
			failResponse.put("Response", "Fail");
			failResponse.put("Message", e.getMessage());
			e.printStackTrace();
			return failResponse;
		}

	}

	// *************************************************************************
	// Unofficial stubs below - Decoupling Request objects and
	// XmiClassDiagramComparer
	// *************************************************************************

	/**
	 * Based on the JSON object's request, this method invokes the desired
	 * request and returns a JSON object.
	 * 
	 * JSON structure is documented in the JSON documentation.
	 */
	public JSONObject actionTest(JSONObject jsonObj) {
		String request = (String) jsonObj.get(KEY_REQUEST);

		switch (request) {
		case REQUEST_REFRESH:
			return Refresh(jsonObj);
		case REQUEST_COMPARE:
			return Compare(jsonObj);
		case REQUEST_CONSOLIDATE:
			return Consolidate(jsonObj);
		case REQUEST_ADD:
			return Add(jsonObj);
		case REQUEST_BREAK:
			return Break(jsonObj);
		}

		JSONObject jObj = new JSONObject();
		jObj.put("Response", "Fail");
		return jObj;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject Refresh(JSONObject jsonObj) {

		JSONObject jObj = new JSONObject();
		ArrayList<String> class1 = new ArrayList<String>();
		class1.add("Vehicle");

		ArrayList<String> class2 = new ArrayList<String>();
		class2.add("Vehicle");
		class2.add("Bus");

		ArrayList<String> same = new ArrayList<String>();
		same.add("Car");
		same.add("Bike");

		jObj.put("Class1", class1);
		jObj.put("Class2", class2);
		jObj.put("Same", same);
		jObj.put("Response", "Success");
		return jObj;
	}

	private JSONObject Compare(JSONObject jsonObj) {
		JSONObject jObj = new JSONObject();

		jObj.put("Class1", "Vehicle");
		jObj.put("Class2", "Vehicle");

		ArrayList<String> arraylist = new ArrayList<String>();

		HashMap<String, ArrayList<String>> attr = new HashMap<String, ArrayList<String>>();
		arraylist.add("<Undefined> Color");
		attr.put("Class2", arraylist);

		arraylist = new ArrayList<String>();

		HashMap<String, ArrayList<String>> op = new HashMap<String, ArrayList<String>>();
		arraylist.add("Start()");
		op.put("Class1", arraylist);
		arraylist = new ArrayList<String>();
		arraylist.add("Stop()");
		op.put("Class2", arraylist);

		jObj.put("Attributes", attr);
		jObj.put("Operations", op);
		jObj.put("Response", "Success");
		return jObj;
	}

	private JSONObject Consolidate(JSONObject jsonObj) {
		JSONObject jObj = new JSONObject();
		jObj.put("Response", "Success");
		return jObj;
	}

	private JSONObject Add(JSONObject jsonObj) {
		JSONObject jObj = new JSONObject();

		jObj.put("Class2", "Bus");
		ArrayList<String> arraylist = new ArrayList<String>();
		arraylist.add("<Undefined> Benches");
		jObj.put("Attributes", arraylist);
		arraylist = new ArrayList<String>();
		arraylist.add("StopSign()");
		jObj.put("Operations", arraylist);
		jObj.put("Response", "Success");
		return jObj;
	}

	private JSONObject Break(JSONObject jsonObj) {
		JSONObject jObj = new JSONObject();
		jObj.put("Response", "Success");
		return jObj;
	}

	// ********** END TEST STUBS **********************************
	
	/**
	 * @return the sameClass
	 */
	public ArrayList<XmiMergedClass> getSameClass() {
		return sameClass;
	}

	/**
	 * @param sameClass
	 *            the sameClass to set
	 */
	public void setSameClass(ArrayList<XmiMergedClass> sameClass) {
		this.sameClass = sameClass;
	}

	/**
	 * @return the uniqueClass1
	 */
	public ArrayList<XmiClassElement> getUniqueClass1() {
		return uniqueClass1;
	}

	/**
	 * @param uniqueClass1
	 *            the uniqueClass1 to set
	 */
	public void setUniqueClass1(ArrayList<XmiClassElement> uniqueClass1) {
		this.uniqueClass1 = uniqueClass1;
	}

	/**
	 * @return the uniqueClass2
	 */
	public ArrayList<XmiClassElement> getUniqueClass2() {
		return uniqueClass2;
	}

	/**
	 * @param uniqueClass2
	 *            the uniqueClass2 to set
	 */
	public void setUniqueClass2(ArrayList<XmiClassElement> uniqueClass2) {
		this.uniqueClass2 = uniqueClass2;
	}

	/**
	 * @return the classDiagram1
	 */
	public final XmiClassDiagramParser getClassDiagram1() {
		return ClassDiagram1;
	}

	/**
	 * @return the classDiagram2
	 */
	public final XmiClassDiagramParser getClassDiagram2() {
		return ClassDiagram2;
	}

	/**
	 * Create the list of unique and same class lists.
	 */
	private void GenerateClassLists() {

		// Keep track of class elements to remove (avoid concurrency issues)
		ArrayList<XmiClassElement> trackRemoveClass1 = new ArrayList<XmiClassElement>();
		ArrayList<XmiClassElement> trackRemoveClass2 = new ArrayList<XmiClassElement>();

		// populate unique elements then search for classes that are perfectly
		// the same
		for (XmiClassElement class1 : ClassDiagram1.getClassElements()) {
			uniqueClass1.add(class1);
		}

		for (XmiClassElement class2 : ClassDiagram2.getClassElements()) {
			uniqueClass2.add(class2);
		}

		for (XmiClassElement class1 : uniqueClass1) {
			for (XmiClassElement class2 : uniqueClass2) {
				if (class1.equals(class2)) {
					trackRemoveClass1.add(class1);
					trackRemoveClass2.add(class2);
					sameClass.add(new XmiMergedClass(class1, class2));
				}
			}
		}

		// remove not unique classes
		for (XmiClassElement class1 : trackRemoveClass1) {
			uniqueClass1.remove(class1);
		}

		for (XmiClassElement class2 : trackRemoveClass2) {
			uniqueClass2.remove(class2);
		}
	}


	/**
	 * Used for testing to see the information stored
	 */
	public static void TestPrintOutput(String fileName, ArrayList<XmiMergedClass> mergedList) {

		// TESTing shows all elements
		System.out.println("Diagram: " + fileName);
		
		for (XmiMergedClass mergedClass : mergedList) {
			
			if (mergedClass.getClass1() != null) {
				System.out.println("Class " + mergedClass.getClass1().toString());
			}
				
			if (mergedClass.getAttributes() != null) {
				for (XmiAttributeElement element : mergedClass.getAttributes()) {
					System.out.println("Attribute1 " + element.toString());
				}
			}	
			
			if (mergedClass.getAttributes2() != null) {
				for (XmiAttributeElement element : mergedClass.getAttributes2()) {
					System.out.println("Attribute2 " + element.toString());
				}
			}
				
			if (mergedClass.getOperations() != null) {
				for (XmiOperationElement element : mergedClass.getOperations()) {
					System.out.println("Operation1 " + element.toString());
				}
			}
			
			if (mergedClass.getOperations2() != null) {
				for (XmiOperationElement element : mergedClass.getOperations2()) {
					System.out.println("Operation2 " + element.toString());
				}
			}
			
			if (mergedClass.getGeneralizations() != null) {
				for (XmiGeneralizationElement element : mergedClass.getGeneralizations()) {
					System.out.println("Generalization1 " + element.toString());
				}
			}
			
			if (mergedClass.getGeneralizations2() != null) {
				for (XmiGeneralizationElement element : mergedClass.getGeneralizations2()) {
					System.out.println("Generalization2 " + element.toString());
				}
			}	
			
			if (mergedClass.getAssociations() != null) {
				for (XmiAssociationElement element : mergedClass.getAssociations()) {
					System.out.println("Association " + element.toString());
				}
			}	
			
			if (mergedClass.getAssociations2() != null) {
				for (XmiAssociationElement element : mergedClass.getAssociations2()) {
					System.out.println("Association2 " + element.toString());
				}
			}	
		}
	}

	/**
	 * @return the associationUml
	 */
	public ArrayList<XmiMergedAssociation> getAssociationUml() {
		return associationUml;
	}

	/**
	 * @param associationUml the associationUml to set
	 */
	public void setAssociationUml(ArrayList<XmiMergedAssociation> associationUml) {
		this.associationUml = associationUml;
	}
	
}
