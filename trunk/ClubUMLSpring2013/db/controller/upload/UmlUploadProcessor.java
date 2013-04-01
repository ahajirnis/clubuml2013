package controller.upload;
import uml2parser.*;
import logging.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;

/**
 * 
 * @author shuklp
 *
 */
public class UmlUploadProcessor extends ClassUploadProcessor {
		
	List <FileInfo> fileList; 
	private final static String PAPYRUS_CLASS_DIAG = "PapyrusUMLClassDiagram";
	private final static String PAPYRUS_NOTATION_ELEM = "notation:Diagram"; 
	private final static String PAPYRUS_PACKAGED_ELEM = "packagedElement";
	private boolean isClassDiag = false;
	private boolean isSeqDiag = false;
	private List<String> activeIdList;
	private XmiElement classXmiDiag;  
	ModelFileInfo modelUmlInfo;
	private String Umlfilename; 
	
	public UmlUploadProcessor(List <FileInfo> list) {
		fileList = list;
		activeIdList = new ArrayList<String>();
	}
	
	@Override
	public void process() {
		

				
		// first process the .notation file 
		FileInfo info = getFile(UploadProcessorFactory.NOTATION_EXTENSION);
		ModelFileInfo notationmodelInfo = new ModelFileInfo(info.getDestFilePath() + info.getFileName());
		ParseXmi notationXmi = new ParseXmi(notationmodelInfo);
		
		notationXmi.parseDoc();
		
		// Parse the UML File 
		FileInfo umlInfo = getFile(UploadProcessorFactory.UML_EXTENSION);
		modelUmlInfo = new ModelFileInfo(umlInfo.getDestFilePath() + umlInfo.getFileName());
		ParseXmi umlXmi = new ParseXmi(modelUmlInfo);
		Umlfilename = umlInfo.getFileName();
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
									// TODO: We are currently only supporting one class diagram per
									// Notation file. We can enhance this in future. 
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
				if (modelUmlInfo.findElementsById(activeIdList.get(i)) == false) {
					foundError = true;
					break;
				}
				
			}
			if (foundError == false) {
				// Convert this into a java file.
				
				// Build a list of Class. 
				List <XmiElement> classList = new ArrayList<XmiElement> ();
				List<XmiElement> packagedElemList =  modelUmlInfo.findElementsByName(PAPYRUS_PACKAGED_ELEM);
				for (int i = 0 ; i < packagedElemList.size(); i++) {
					if (packagedElemList.get(i).getFoundMatch()) {
						List <Attribute> attrlist = packagedElemList.get(i).getAttrib();
						for (int k = 0; k < attrlist.size(); k++) {
							if (attrlist.get(k).getName().equals("xmi:type") && 
									attrlist.get(k).getValue().equals("uml:Class")) {
								//Log.LogCreate().Info("Valid class name = " + packagedElemList.get(i).getAttributeValue("name"));
								classList.add(packagedElemList.get(i));
							}
						}	

					}
				}
				//Log.LogCreate().Info("Calling CreateJava file");
				CreateJavaFile(classList);
				createPngFile(Umlfilename, Umlfilename + ".java", umlInfo.getDestFilePath(), umlInfo.getLibPath());
			}
			
		}		
	}
	
	private void CreateJavaFile(List <XmiElement> classList ) {
		// Create Stream Writer
		BufferedWriter out; 
				try {
					// for each package

						File javaFile = new File(fileList.get(0).getDestFilePath() + Umlfilename + ".java");
						FileWriter fstream = new FileWriter(javaFile);
						out = new BufferedWriter(fstream);

						out.write("import java.util.Date;\n");
						out.write("import java.util.*; \n");
						out.write("import java.io.*; \n");

						// iterate thru an array list of classes

						for (int i = 0; i < classList.size(); i++) {
							
							XmiElement cls =  classList.get(i);
							List <XmiElement> childlist = cls.getChildElemList();
							
							String className ="";
							className = cls.getAttributeValue("name");
							//To determine if we have any generalizaton
							for (int j = 0 ; j < childlist.size(); j++ ) {
								// Check if there is any element named "generalizaion
								XmiElement childElem = childlist.get(j);
								if (childElem.getElementName().equals("generalization")){

									// Parent element xmiElement 
									String id = childElem.getAttributeValue("general");
									XmiElement parentElement  = modelUmlInfo.getXmiElementFromId(id);
									className += " extends " + parentElement.getAttributeValue("name");
								}
							}
							out.write("public class " + className + "{\n");
							
							out.write("\n");
							//out.write("/**\n");
							
							for (int j = 0 ; j < childlist.size(); j++ ) {
								XmiElement childElem = childlist.get(j);
								if (childElem.getFoundMatch() ) {
									// Operations
									if (childElem.getElementName().equals("ownedOperation")) {
										List <Attribute>attriblistchild  = childElem.getAttrib();
										for (int idx = 0;idx  < attriblistchild.size(); idx++) {
											if (attriblistchild.get(idx).getName().equals("name")) {
												String operationName = attriblistchild.get(idx).getValue();
												out.write("\tvoid " + operationName + "();\n" );
												break;
											}
										}
									} else if(childElem.getElementName().equals("ownedAttribute")) {
										// Here we only will process the Attributes	
										List <Attribute>attriblistchild  = childElem.getAttrib();
										String attrStr = "";
										String type = "";
										String name ="";
										for (int idx = 0;idx  < attriblistchild.size(); idx++) {

											if (attriblistchild.get(idx).getName().equals("name")) {
												name = attriblistchild.get(idx).getValue();	
												break;
											}
											 
										}
										List <XmiElement> attrList = childElem.getChildElemList();
										for (int idx = 0; idx < attrList.size(); idx++) {
											if (attrList.get(idx).getElementName().equals("type")) {
												XmiElement elem  = attrList.get(idx);
												if (elem.getAttributeValue("xmi:type").equals("uml:PrimitiveType")) {
													String hrefVal = elem.getAttributeValue("href");
													hrefVal = hrefVal.substring(hrefVal.indexOf('#') + 1, hrefVal.length());
													switch (hrefVal) {
														case "String":
															type = "String";
															break;
														case "Integer" :
															type = "int";
															break;
														case "Real":
															type = "double";
															break;
														case "Boolean":
															type = "boolean";
															break;
														case "UnlimitdNatural":
															type = "long";
															break;
														
													}
												}
												
												
											}
										}
										if (!type.isEmpty())
											out.write("\t" + type +" " + name  + ";\n");
										else 
											out.write("\t int" +" " + name  + ";\n");
									} 
									 
								}else if(childElem.getElementName().equals("ownedAttribute")) {
									
									String xmiID = childElem.getAttributeValue("type");
									XmiElement elem = modelUmlInfo.getXmiElementFromId(xmiID);
									String type = elem.getAttributeValue("name");
									out.write("\t "+ type + " " + childElem.getAttributeValue("name") + ";\n");
								}									
							}	
							out.write("}\n");

						}
						out.close();
						fstream.close();
				} catch (Exception e) {
					Log.LogCreate().Info("Got an error creating the file...."
							+ e.getMessage());
				}

				
	}
	
	//private 
	
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
	
	/**
	 * 
	 * Still thinking if we need to break this up into two class 
	 * Sequence diagram and the Class diagram. 
	 *
	 */
	private class Uml2ClassUploadProcessor {
		
	}
	
	private class Uml2SequenceDiagramUploadProcessor {
		
	}

}
