package controller.comparer.xmi;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import controller.compare.ComparerIntf;
import controller.comparer.xmi.request.Request;
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
	
	// Classes unqiey to diagram 2
	private ArrayList<XmiClassElement> uniqueClass2 = new ArrayList<XmiClassElement>();

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
	 * Based on the JSON object's request, this method invokes the
	 * desired request and returns a JSON object.
	 * 
	 * JSON structure is documented in the JSON documentation.
	 */
	@Override
	public JSONObject action(JSONObject jsonObj) {
		String request = (String)jsonObj.get(KEY_REQUEST);
		
		switch(request) {
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
		
		return null;
	}

	// Note: This method will replace the action() method above when we're ready to connect 
	// the real request elements
	//
	// Request classes that needs to be created under controller.comparer.xmi.request package:
	//  Refresh
	//  Compare
	//	Consolidate
	//	Add 
	//	Break
	public JSONObject actionTest(JSONObject jsonObj) {

		String request = (String)jsonObj.get(REQUEST_PACKAGE + KEY_REQUEST);
		
		try {
			// Create request object via Reflection
			Request requestObj = (Request) Class.forName(request).newInstance();
			return requestObj.request(jsonObj, this);
		} catch (Exception e) {
			// Return null object for any error, no request
			// Possible errors:
			// 	1. request variable string is null
			//	2. Attempting to instantiate a class that doesn't exist
			return null;
		}
	}
	
	// *************************************************************************
	// Unofficial stubs below - Decoupling Request objects and XmiClassDiagramComparer
	// *************************************************************************

	@SuppressWarnings("unchecked")
	private JSONObject Refresh(JSONObject jsonObj) {
		return null;
	}
	
	private JSONObject Compare(JSONObject jsonObj) {
		return null;
	}
	
	private JSONObject Consolidate(JSONObject jsonObj) {
		return null;
	}
	
	private JSONObject Add(JSONObject jsonObj) {
		return null;
	}
	
	private JSONObject Break(JSONObject jsonObj) {
		return null;
	}

	/**
	 * @return the sameClass
	 */
	public ArrayList<XmiMergedClass> getSameClass() {
		return sameClass;
	}

	/**
	 * @param sameClass the sameClass to set
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
	 * @param uniqueClass1 the uniqueClass1 to set
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
	 * @param uniqueClass2 the uniqueClass2 to set
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
		
		// populate unique elements then search for classes that are perfectly the same
		for (XmiClassElement class1 : ClassDiagram1.getClassElements() ){
			uniqueClass1.add(class1);
		}
		
		for (XmiClassElement class2 : ClassDiagram2.getClassElements() ){
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
		for (XmiClassElement class1 : trackRemoveClass1 ){
			uniqueClass1.remove(class1);
		}
		
		for (XmiClassElement class2 : trackRemoveClass2 ){
			uniqueClass2.remove(class2);
		}
	}
	
}
