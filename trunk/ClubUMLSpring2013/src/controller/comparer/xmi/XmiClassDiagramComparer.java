package controller.comparer.xmi;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import controller.compare.ComparerIntf;
import controller.upload.FileInfo;
import controller.upload.UploadProcessorFactory;

public class XmiClassDiagramComparer implements ComparerIntf {

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
		FileInfo classDiagram1Notation = getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles1);
		FileInfo classDiagram1Uml = getFile(
				UploadProcessorFactory.UML_EXTENSION, XmiFiles1);

		ClassDiagram1 = new XmiClassDiagramParser(
				classDiagram1Uml.getDestFilePath()
						+ classDiagram1Uml.getFileName(),
				classDiagram1Notation.getDestFilePath()
						+ classDiagram1Notation.getFileName());

		// Process the second file
		FileInfo classDiagram2Notation = getFile(
				UploadProcessorFactory.NOTATION_EXTENSION, XmiFiles2);
		FileInfo classDiagram2Uml = getFile(
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

	/**
	 * 
	 */
	@Override
	public JSONObject action(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	// *************************************************************************
	// Implement and change these stubs to however you like
	// *************************************************************************

	// Compare classes
	private JSONObject compareClass(String className1, String className2) {
		List<XmiClassElement> list1 = ClassDiagram1.getClassElements();
		List<XmiClassElement> list2 = ClassDiagram2.getClassElements();

		for (XmiClassElement classElement1 : list1) {
			for (XmiClassElement classElement2 : list2) {

			}
		}

		return null;
	}

}
