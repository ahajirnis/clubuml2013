package controller.comparer.xmi;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import controller.compare.ComparerIntf;
import controller.upload.FileInfo;
import controller.upload.UploadProcessorFactory;
import controller.util.FileUtil;

public class XmiClassDiagramComparer implements ComparerIntf {

	private final static String KEY_REQUEST = "Request";
	private final static String REQUEST_REFRESH = "Refresh";
	private final static String REQUEST_CONSOLIDATE = "Consolidate";
	private final static String REQUEST_ADD = "Add";
	private final static String REQUEST_BREAK = "Break";
	private final static String REQUEST_COMPARE = "Compare";
	
	private XmiClassDiagramParser ClassDiagram1;
	private XmiClassDiagramParser ClassDiagram2;

	// TODO: Rework
	private ArrayList<XmiClassElement> sameClass = new ArrayList<XmiClassElement>();
	private ArrayList<XmiClassElement> uniqueClass1 = new ArrayList<XmiClassElement>();
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
	}

	/**
	 * Refactor this method since it can be used in other sources (ex:
	 * UmlUploadProcessors)
	 * 
	 * @param extension
	 * @param fileList
	 * @return
	 */
	
	/*  ***************
	 * this method has been changed to be static 
	 * new location is in FileUtil.java under package controller.util 
	 *
	private FileInfo getFile(String extension, List<FileInfo> fileList) {
		FileInfo info = null;
		for (int i = 0; i < fileList.size(); i++) {
			String extn = fileList
					.get(i)
					.getFileName()
					.substring(
							fileList.get(i).getFileName().lastIndexOf(".") + 1,
							fileList.get(i).getFileName().length());
			if (extn.equals(extension)) {
				info = fileList.get(i);
			}
		}
		return info;
	}
	
	* ***************/ 

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

	// *************************************************************************
	// Implement and change these stubs to however you like
	// *************************************************************************

	@SuppressWarnings("unchecked")
	private JSONObject Refresh(JSONObject jsonObj) {
		
		JSONObject obj = new JSONObject();
		
		ArrayList<String> array1 = new ArrayList<String>();
		array1.add("Class A");
		array1.add("Class B");
		obj.put("Class1", array1);
		
		ArrayList<String> array2 = new ArrayList<String>();
		array2.add("Class C");
		array2.add("Class D");
		obj.put("Class2", array2);
		
		ArrayList<String> array3 = new ArrayList<String>();
		array3.add("Class E");
		array3.add("Class F");
		obj.put("Same", array3);
		
		return obj;
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
}
