package controller.upload;

import repository.DiagramDAO;
import repository.EditingHistoryDAO;
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

import domain.Diagram;
import domain.EditingHistory;

/**
 * 
 * @author shuklp
 * 
 */
public class UpdatedUmlUploadProcessor implements UploadProcessor {

	List<FileInfo> fileList;
	private final static String PAPYRUS_CLASS_DIAG = "PapyrusUMLClassDiagram";
	private final static String PAPYRUS_SEQUENCE_DIAG = "PapyrusUMLSequenceDiagram";
	private final static String PAPYRUS_NOTATION_ELEM = "notation:Diagram";
	private final static String PAPYRUS_PACKAGED_ELEM = "packagedElement";
	private boolean isClassDiag = false;
	private boolean isSeqDiag = false;
	private int flag = 0;
	private List<String> activeIdList;
	private XmiElement classXmiDiag;
	private XmiElement sequenceXmiDiag;
	private ModelFileInfo modelUmlInfo;
	private String Umlfilename;
	private FileInfo umlInfo;
	private String diagramPath;
	private int id;

	public UpdatedUmlUploadProcessor(List<FileInfo> list, String path, int id) {
		fileList = list;
		activeIdList = new ArrayList<String>();
		diagramPath = path;
		this.id = id;
	}

	@Override
	public void process() {

		// first process the .notation file
		FileInfo info = getFile(UploadProcessorFactory.NOTATION_EXTENSION);
		ModelFileInfo notationmodelInfo = new ModelFileInfo(
				info.getDestFilePath() + info.getFileName());
		ParseXmi notationXmi = new ParseXmi(notationmodelInfo);

		notationXmi.parseDoc();

		// Parse the UML File
		umlInfo = getFile(UploadProcessorFactory.UML_EXTENSION);
		modelUmlInfo = new ModelFileInfo(umlInfo.getDestFilePath()
				+ umlInfo.getFileName());
		ParseXmi umlXmi = new ParseXmi(modelUmlInfo);
		Umlfilename = umlInfo.getFileName();
		umlXmi.parseDoc();

		// Does the diagram support Class diagrams & sequence diagrams
		List<XmiElement> elemList = notationmodelInfo
				.findElementsByName(PAPYRUS_NOTATION_ELEM);

		if (elemList.size() > 0) {

			for (int i = 0; i < elemList.size(); i++) {

				XmiElement xmi = elemList.get(i);

				List<Attribute> attriblist = xmi.getAttrib();

				for (int j = 0; j < attriblist.size(); j++) {

					Attribute attr = attriblist.get(j);

					if (attr.getName().equals("type")) {
						switch (attr.getValue()) {
						case PAPYRUS_CLASS_DIAG: {
							// TODO: We are currently only supporting one class
							// diagram per
							// Notation file. We can enhance this in future.
							classXmiDiag = xmi;
							isClassDiag = true;
							flag = 1;
						}
							break;
						// Add Sequence Diagrams Support
						case PAPYRUS_SEQUENCE_DIAG: {
							sequenceXmiDiag = xmi;
							isSeqDiag = true;
							flag = 2;
						}
							break;
						default: {
							break;
						}
						}
					}
				}
			}
		}

		if (isClassDiag) {
			new Uml2ClassUploadProcessor().process();
			String image_file_name = "class_diagram_" + umlInfo.getFileName()
					+ ".png";
			String folder = diagramPath + "/" + umlInfo.getFileName();
			this.storeDatabase(folder, image_file_name, id);
		}
		if (isSeqDiag) {
			new Uml2SequenceDiagramUploadProcessor().process();
			String image_file_name = "class_diagram_" + umlInfo.getFileName()
					+ ".png";
			String folder = diagramPath + "/" + umlInfo.getFileName();
			this.storeDatabase(folder, image_file_name, id);
		}
	}

	/*
	 * function to store upload diagram information into database.
	 */
	private void storeDatabase(String path, String fileName, int userID) {
		try {
			Diagram diagramObj = new Diagram();
			diagramObj.setDiagramName(fileName);
			diagramObj.setEcoreFilePath(path);
			diagramObj.setInEdition(false);
			diagramObj.setOwnerId(userID);
			diagramObj.setProjectId(2);
			DiagramDAO.addDiagram(diagramObj);

			EditingHistory editObj = new EditingHistory();
			editObj.setDiagramId(diagramObj.getDiagramId());
			editObj.setUserId(userID);

			EditingHistoryDAO.addHistory(editObj);
		} catch (IllegalArgumentException e) {
		}
	}

	// private

	/**
	 * 
	 * @param name
	 */
	private void ElementIterator(XmiElement name) {
		List<XmiElement> childElementlist = name.getChildElemList();
		if (childElementlist != null) {
			// iterate the xmiElement which contains all the information
			for (int i = 0; i < childElementlist.size(); i++) {
				ElementIterator(childElementlist.get(i));
			}

		}
		List<Attribute> attribList = name.getAttrib();
		for (int j = 0; j < attribList.size(); j++) {
			Attribute attrib = attribList.get(j);
			if (attrib.getName().equals("href")) {
				int beginIndex = attrib.getValue().indexOf('#');
				String value = attrib.getValue().substring(beginIndex + 1);
				// Log.LogCreate().Info("Active IDs " + value);
				activeIdList.add(value);
			}
		}

	}

	private FileInfo getFile(String extension) {
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
				break;
			}
		}
		return info;
	}

	/**
	 * 
	 * Still thinking if we need to break this up into two class Sequence
	 * diagram and the Class diagram.
	 * 
	 */
	private class Uml2ClassUploadProcessor extends DiagramVisualization {

		public void process() {

			ElementIterator(classXmiDiag);

			boolean foundError = false;
			// now find the elements in the class
			for (int i = 0; i < activeIdList.size(); i++) {
				if (modelUmlInfo.findElementsById(activeIdList.get(i)) == false) {
					foundError = true;
					break;
				}

			}
			if (foundError == false) {
				// Convert this into a java file.

				// Build a list of Class.
				List<XmiElement> classList = new ArrayList<XmiElement>();
				List<XmiElement> packagedElemList = modelUmlInfo
						.findElementsByName(PAPYRUS_PACKAGED_ELEM);
				for (int i = 0; i < packagedElemList.size(); i++) {
					if (packagedElemList.get(i).getFoundMatch()) {
						List<Attribute> attrlist = packagedElemList.get(i)
								.getAttrib();
						for (int k = 0; k < attrlist.size(); k++) {
							if (attrlist.get(k).getName().equals("xmi:type")
									&& attrlist.get(k).getValue()
											.equals("uml:Class")) {
								// Log.LogCreate().Info("Valid class name = " +
								// packagedElemList.get(i).getAttributeValue("name"));
								classList.add(packagedElemList.get(i));
							}
						}

					}
				}
				// Log.LogCreate().Info("Calling CreateJava file");
				CreateJavaFile(classList);
				createPngFile(flag, Umlfilename, Umlfilename + ".java",
						umlInfo.getDestFilePath(), umlInfo.getLibPath());
			}
		}

		private void CreateJavaFile(List<XmiElement> classList) {
			// Create Stream Writer
			BufferedWriter out;
			try {
				// for each package

				File javaFile = new File(fileList.get(0).getDestFilePath()
						+ Umlfilename + ".java");
				FileWriter fstream = new FileWriter(javaFile);
				out = new BufferedWriter(fstream);

				out.write("import java.util.Date;\n");
				out.write("import java.util.*; \n");
				out.write("import java.io.*; \n");

				// iterate thru an array list of classes

				for (int i = 0; i < classList.size(); i++) {

					XmiElement cls = classList.get(i);
					List<XmiElement> childlist = cls.getChildElemList();

					String className = "";
					className = cls.getAttributeValue("name");
					// To determine if we have any generalizaton
					for (int j = 0; j < childlist.size(); j++) {
						// Check if there is any element named "generalizaion
						XmiElement childElem = childlist.get(j);
						if (childElem.getElementName().equals("generalization")) {

							// Parent element xmiElement
							String id = childElem.getAttributeValue("general");
							XmiElement parentElement = modelUmlInfo
									.getXmiElementFromId(id);
							className += " extends "
									+ parentElement.getAttributeValue("name");
						}
					}
					out.write("public class " + className + "{\n");

					out.write("\n");
					// out.write("/**\n");

					for (int j = 0; j < childlist.size(); j++) {
						XmiElement childElem = childlist.get(j);
						if (childElem.getFoundMatch()) {
							// Operations
							if (childElem.getElementName().equals(
									"ownedOperation")) {
								List<Attribute> attriblistchild = childElem
										.getAttrib();
								for (int idx = 0; idx < attriblistchild.size(); idx++) {
									if (attriblistchild.get(idx).getName()
											.equals("name")) {
										String operationName = attriblistchild
												.get(idx).getValue();
										out.write("\tvoid " + operationName
												+ "();\n");
										break;
									}
								}
							} else if (childElem.getElementName().equals(
									"ownedAttribute")) {
								// Here we only will process the Attributes
								List<Attribute> attriblistchild = childElem
										.getAttrib();
								String attrStr = "";
								String type = "";
								String name = "";
								for (int idx = 0; idx < attriblistchild.size(); idx++) {

									if (attriblistchild.get(idx).getName()
											.equals("name")) {
										name = attriblistchild.get(idx)
												.getValue();
										break;
									}

								}
								List<XmiElement> attrList = childElem
										.getChildElemList();
								for (int idx = 0; idx < attrList.size(); idx++) {
									if (attrList.get(idx).getElementName()
											.equals("type")) {
										XmiElement elem = attrList.get(idx);
										if (elem.getAttributeValue("xmi:type")
												.equals("uml:PrimitiveType")) {
											String hrefVal = elem
													.getAttributeValue("href");
											hrefVal = hrefVal.substring(
													hrefVal.indexOf('#') + 1,
													hrefVal.length());
											switch (hrefVal) {
											case "String":
												type = "String";
												break;
											case "Integer":
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
									out.write("\t" + type + " " + name + ";\n");
								else
									out.write("\t int" + " " + name + ";\n");
							}

						} else if (childElem.getElementName().equals(
								"ownedAttribute")) {

							String xmiID = childElem.getAttributeValue("type");
							XmiElement elem = modelUmlInfo
									.getXmiElementFromId(xmiID);
							String type = elem.getAttributeValue("name");
							out.write("\t " + type + " "
									+ childElem.getAttributeValue("name")
									+ ";\n");
						}
					}
					out.write("}\n");

				}
				out.close();
				fstream.close();
			} catch (Exception e) {
				Log.LogCreate().Info(
						"Got an error creating the file...." + e.getMessage());
			}

		}

	}

	private class Uml2SequenceDiagramUploadProcessor extends DiagramVisualization{
		public void process() {
			Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor");
			ElementIterator(sequenceXmiDiag);
			boolean foundError = false;
			// PicElement is a structure specifically for creating .pic statement
			PicElement picElem= new PicElement();
			// now find the elements in the class 
			for (int i = 0 ; i < activeIdList.size(); i++) {
				if (modelUmlInfo.findElementsById(activeIdList.get(i)) == false) {
					Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor : Found error while iterating active element list");
					foundError = true;
					break;
				}
				
			}
			Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor : active list size =  " + String.valueOf(activeIdList.size()) );
			if (foundError == false) {
				Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor : ");
				// Build a list of lifelines, messages and fragments
				// The fragment list sequence presents the message sequence
				List <XmiElement> fragmentList = new ArrayList<XmiElement> ();
				List <XmiElement> lifelineList = new ArrayList<XmiElement> ();
				List <XmiElement> messageList = new ArrayList<XmiElement> ();
				List <String> msgIdList = new ArrayList<String> ();
				List<XmiElement> packagedElemList =  modelUmlInfo.findElementsByName(PAPYRUS_PACKAGED_ELEM);
				Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor : packagedElemList size =  " + String.valueOf(packagedElemList.size()) );
				for (int i = 0 ; i < packagedElemList.size(); i++) {
					List<XmiElement> childElems = packagedElemList.get(i).getChildElemList();
					Log.LogCreate().Info("Uml2SequenceDiagramUploadProcessor : childElems size =  " + String.valueOf(childElems.size()) );
					for (int j = 0; j < childElems.size(); j++)
					if (childElems.get(j).getFoundMatch()) {
							if (childElems.get(j).getElementName().equals("fragment")) {
								Log.LogCreate().Info("Found fragment list =" + childElems.get(j).getElementId());
								fragmentList.add(childElems.get(j));
								if(childElems.get(j).getAttributeValue("xmi:type").equals("MessageOccurrenceSpecification") && childElems.get(j).getAttributeValue("name").startsWith("MessageSend")) {
									msgIdList.add(childElems.get(j).getAttributeValue("message"));																	
								}
							}	
							if (childElems.get(j).getElementName().equals("lifeline")) {
								Log.LogCreate().Info("Found lifeline list =" + childElems.get(j).getElementId());
								lifelineList.add(childElems.get(j));
							}
							if (childElems.get(j).getElementName().equals("message")) {
								Log.LogCreate().Info("Found message list =" + childElems.get(j).getElementId());
								messageList.add(childElems.get(j));
							}													
					}					
				}
			    // Create XmiElementMessage item and put it into messageList of Pic class
				for (int i = 0 ; i < messageList.size() ; i++) {
					XmiElementMessage xmiElemMsg = new XmiElementMessage(messageList.get(i));
					xmiElemMsg.process();
					for (XmiElement fragment:fragmentList) {
						if (fragment.getElementId().equals(messageList.get(i).getAttributeValue("sendEvent"))||fragment.getElementId().equals(messageList.get(i).getAttributeValue("receiveEvent"))) {
							for (XmiElement lifeline:lifelineList)
							{
								if (lifeline.getElementId().equals(fragment.getAttributeValue("covered")) && fragment.getAttributeValue("name").startsWith("MessageSend"))
								{
									xmiElemMsg.setSender(new XmiElementLifeLine(lifeline));
								}
								if (lifeline.getElementId().equals(fragment.getAttributeValue("covered")) && fragment.getAttributeValue("name").startsWith("MessageRecv"))
								{
									xmiElemMsg.setReceiver(new XmiElementLifeLine(lifeline));
								}
							}
						
						}
					}	
					
					// add xmiElementMessage with the correct order 
					if (msgIdList.contains(messageList.get(i).getElementId())) {
						picElem.getMessageList().add(msgIdList.indexOf(messageList.get(i).getElementId()), xmiElemMsg);
					}
				}
				
				for (int i = 0 ; i < lifelineList.size() ; i++) {
					XmiElementLifeLine xmiElemLifLin = new XmiElementLifeLine(lifelineList.get(i));
					xmiElemLifLin.process();
					String coveryByVal = lifelineList.get(i).getAttributeValue("coveredBy");
					String [] coveryByValList = coveryByVal.split(" ");
					List<String> coveryByList = new ArrayList<String>();
					for (int j = 0 ; j < coveryByValList.length; j++) {
						coveryByList.add(coveryByValList[j]);
					}
					for(XmiElement msg:messageList) {
						if (msg.getAttributeValue("messageSort").equals("createMessage") && coveryByList.contains(msg.getElementId())) {
							xmiElemLifLin.setLifelineType("placeholder_object");
						}
						else {
							xmiElemLifLin.setLifelineType("object");
						}
					}
					picElem.getLifelineList().add(xmiElemLifLin);					
				}
				
				/** Then call method to translate PicELement into .pic statement and create .png file**/
				CreatePicFile(picElem);
				createPngFile(flag, Umlfilename, Umlfilename + ".pic",
						umlInfo.getDestFilePath(), umlInfo.getLibPath());
			}
		}
		
		private void CreatePicFile(PicElement PicElem) {
			// Create Stream Writer
			BufferedWriter out;
			try {
				// for each package

				File javaFile = new File(fileList.get(0).getDestFilePath()
						+ Umlfilename + ".pic");
				FileWriter fstream = new FileWriter(javaFile);
				out = new BufferedWriter(fstream);
				PicElement picElem = new PicElement();
				out.write(".PS\n");
				out.write("copy \"sequence.pic\"; \n");
				String[] LifelineNic = new String[picElem.getLifelineList().size()];
				
				out.write("#Define objects \n");
				for (int i = 0; i < picElem.getLifelineList().size(); i++) {
					XmiElementLifeLine  obj= picElem.getLifelineList().get(i);
					String objType,objName;
					objType = obj.getLifelineType();
					objName = obj.getLifelineName();
					LifelineNic[i] = (String) objName.substring(0, 1)+i;
					obj.setLifelineNic(LifelineNic[i]);
					if(objType.equals("object"))
						out.write(objType+"("+LifelineNic[i]+",\""+objName+"\");\n");
					else
						out.write(objType+"("+LifelineNic[i]+");\n");
				}
				out.write("step();");
				
				out.write("#Message sequences \n");
				for(int m = 0; m<picElem.getMessageList().size(); m++)
				{
					XmiElementMessage  msg= picElem.getMessageList().get(m);
					String msgSort,msgName,msgSender,msgReceiver;
					msgSort = msg.getmessageSort();
					msgName = msg.getmessageName();
					msgSender = msg.getSender().getLifelineNic();
					msgReceiver = msg.getReceiver().getLifelineNic();
					out.write(msgSort+"("+msgSender+","+msgReceiver+","+"\""+msgName+"\");\n");					
				}
				
				out.write("#Complete the lifelines \n");
				out.write("step(); \n");
				for(int j = 0; j < picElem.getLifelineList().size(); j++)
				{					
					out.write("complete("+j+");\n");
				}
				out.write(".PE\n");
			
				out.close();
				fstream.close();
			} catch (Exception e) {
				Log.LogCreate().Info(
						"Got an error creating the file...." + e.getMessage());
			}

		}
	}
	
	class PicElement {
		ArrayList<XmiElementLifeLine> lifelineList;
		ArrayList<XmiElementMessage> messageList;
		ArrayList<XmiElementFragment> fragmentList;
		
		PicElement() {
			lifelineList = new ArrayList<XmiElementLifeLine>();
			messageList = new ArrayList<XmiElementMessage>();
			fragmentList = new ArrayList<XmiElementFragment>();
		}

		public ArrayList<XmiElementLifeLine> getLifelineList() {
			return lifelineList;
		}	

		public ArrayList<XmiElementMessage> getMessageList() {
			return messageList;
		}

		public ArrayList<XmiElementFragment> getFragmentList() {
			return fragmentList;
		}

	}

	class XmiElementLifeLine {

		XmiElement elem;
		String lifelineName;		
		/*This lifeline Type should be either object or placeholder_object*/
		String lifelineType;
		String lifelineNic;
		
		XmiElementLifeLine(XmiElement element) {			
			elem = element;
		}

		private void process() {
			List<Attribute> temp = elem.getAttrib();
			for (int i = 0; i < temp.size(); i++) {
				Attribute attr = temp.get(i);
				if (attr.getName().equals("name")) {
					lifelineName = attr.getValue();	
				}
			}
		}

		public String getLifelineType() {
			return lifelineType;
		}

		public void setLifelineType(String lifelineType) {
			this.lifelineType = lifelineType;
		}
		public String getLifelineName() {
			return lifelineName;
		}

		public void setLifelineName(String lifelineName) {
			this.lifelineName = lifelineName;
		}	
		public String getLifelineNic() {
			return lifelineNic;
		}

		public void setLifelineNic(String lifelineNic) {
			this.lifelineNic = lifelineNic;
		}
	}

	class XmiElementMessage {
		
		XmiElement elem;
		String messageName;
		String messageSort;
		XmiElementLifeLine sender;
		XmiElementLifeLine receiver;		
		
		XmiElementMessage(XmiElement element) {
			elem = element;
		}

		private void process() {
			List<Attribute> temp = elem.getAttrib();
			for (int i = 0; i < temp.size(); i++) {
				Attribute attr = temp.get(i);
				if (attr.getName().equals("name")) {
					messageName = attr.getValue();	
					
				}
				if (attr.getName().equals("messageSort")) {
					String attrValue = attr.getValue();
					if(attrValue.equals("createMessage")) {
						messageSort = "create_message";
						break;
					}
					else if(attrValue.equals("reply")) {
						messageSort = "return_message";
						break;
					}
					else if(attrValue.equals("createMessage")) {
						messageSort = "create_message";
						break;
					}
					else if(attrValue.equals("deleteMessage")) {
						messageSort = "destroy_message";
						break;
					}
					else {
						messageSort = "message";
						break;
					}
				}				
			}
		}
		
		public String getmessageName() {
			return messageName;
		}

		public String getmessageSort() {
			return messageSort;
		}
		public XmiElementLifeLine getSender() {
			return sender;
		}

		public void setSender(XmiElementLifeLine sender) {
			this.sender = sender;
		}

		public XmiElementLifeLine getReceiver() {
			return receiver;
		}

		public void setReceiver(XmiElementLifeLine receiver) {
			this.receiver = receiver;
		}
		

	}

	// Still thinking if we need this class since the properties differ based on the fragment type
	class XmiElementFragment {

	}
}
