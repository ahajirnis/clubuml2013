package controller.upload;
import uml2parser.*;
import logging.Log;

import java.util.*;
/**
 * 
 * @author shuklp
 *
 */
public class UmlUploadProcessor implements UploadProcessor {
		
	List <FileInfo> fileList; 
	private final static String PAPYRUS_CLASS_DIAG = "PapyrusUMLClassDiagram";
	private final static String PAPYRUS_NOTATION_ELEM = "notation:Diagram"; 
	private boolean isClassDiag = false;
	private boolean isSeqDiag = false;
	private List<String> activeIdList;
	private XmiElement classXmiDiag;  
	
	public UmlUploadProcessor(List <FileInfo> list) {
		fileList = list;
		activeIdList = new ArrayList<String>();
	}
	
	@Override
	public void process() {
		
		// first process the .notation file 
		FileInfo info = getFile(UploadProcessorFactory.NOTATION_EXTENSION);
		ModelFileInfo notationmodelInfo = new ModelFileInfo(info.getFileName());
		ParseXmi notationXmi = new ParseXmi(notationmodelInfo);
		
		notationXmi.parseDoc();
		
		// Parse the UML File 
		FileInfo umlInfo = getFile(UploadProcessorFactory.UML_EXTENSION);
		ModelFileInfo modelUmlInfo  = new ModelFileInfo(umlInfo.getFileName());
		ParseXmi umlXmi = new ParseXmi(modelUmlInfo);
		
		umlXmi.parseDoc();
		

		// Does the diagram support Class diagrams & sequence diagrams

		List<XmiElement> elemList = notationmodelInfo.findElementsByName(PAPYRUS_NOTATION_ELEM);
			
		//Log.LogCreate().Info("found element " + String.valueOf(elemList.size()) );
			
			if (elemList.size() > 0 ) {
				
				for (int i = 0 ; i < elemList.size(); i++) {
				
					XmiElement xmi = elemList.get(i);
				
					List<Attribute> attriblist = xmi.getAttrib();
				
					for (int j= 0; j < attriblist.size(); j++) {
					
						Attribute attr = attriblist.get(j);
					
						if (attr.getName().equals("type")){
							switch(attr.getValue()){
								case PAPYRUS_CLASS_DIAG:
								{
									classXmiDiag =xmi;
									isClassDiag = true;
								}
								break;
								// Add other Diagrams 
								default:
								{
									break;	
								}
							}
						}
					}
				}
			}
		// need to find the active elements in the class diagram
		if (isClassDiag) {
			ElementIterator(classXmiDiag);
			
			boolean foundError = false;
			// now find the elements in the class 
			for (int i = 0 ; i < activeIdList.size(); i++) {
				if (modelUmlInfo.fileElementsById(activeIdList.get(i)) == false) {
					foundError = true;
					break;
				}
				
			}
			if (foundError == false) {
				// Convert this into a java file. 
			}
			
		}		
	}
	/**
	 * 
	 * @param name
	 */
	private void  ElementIterator(XmiElement name) { 
		List <XmiElement> childElementlist = name.getChildElemList();
		if(childElementlist != null) {
			// iterate the xmiElement which contains all the information 
			for (int i = 0; i < childElementlist.size(); i++) {
				ElementIterator(childElementlist.get(i));
			}
			
		}
		List <Attribute> attribList = name.getAttrib();
		for (int j = 0; j < attribList.size(); j++) {
			Attribute attrib = attribList.get(j);
			if (attrib.getName().equals("href")) {
				int beginIndex = attrib.getValue().indexOf('#');
				String value = attrib.getValue().substring(beginIndex +1);
				//Log.LogCreate().Info("Active IDs " + value);
				activeIdList.add(value);
			}
		}
		

	}
	
	
	private FileInfo getFile(String extension) {
		FileInfo info = null;
		for (int i = 0; i < fileList.size(); i++) {
			String extn = fileList.get(i).getFileName().substring(
					fileList.get(i).getFileName().lastIndexOf(".") + 1, fileList.get(i).getFileName().length());
			if (extn.equals(extension)){
				info = fileList.get(i);
			}
		}
		return info;
	}
	
	private class Uml2ClassUploadProcessor {
		
	}

}
