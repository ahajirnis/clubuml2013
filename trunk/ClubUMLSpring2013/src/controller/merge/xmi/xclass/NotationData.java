package controller.merge.xmi.xclass;

import java.util.HashMap;
import java.util.Queue;

public class NotationData {
	
	// Notations to process to genereate .notation file
	private Queue<XmiNotationElement> elements;
	
	// If Class1 exists, we want to use Class2 Id with Class1
	private HashMap<String, String> replaceClass2Id;
	
	// Maps the parent Id to the Source Id
	private HashMap<String, String> mapParentToSource;

	// File name
	private String fileName;
	
	// ID used to identify the UML file from the notation file
	private String umlId;
	
	// Name for notation file
	private String notationName;
	
	// ID for notation file
	private String notationId;
	
	
	public NotationData(Queue<XmiNotationElement> elements, HashMap<String, String> replaceClass2Id, HashMap<String, String> mapParentToSource, String fileName, String umlId, String notationName, String notationId) {
		this.elements = elements;
		this.umlId = umlId;
		this.fileName = fileName;
		this.notationName = notationName;
		this.notationId = notationId;
		this.replaceClass2Id = replaceClass2Id;
		this.mapParentToSource = mapParentToSource;
	}


	/**
	 * @return the elements
	 */
	public Queue<XmiNotationElement> getElements() {
		return elements;
	}


	/**
	 * @return the replaceClass2Id
	 */
	public HashMap<String, String> getReplaceClass2Id() {
		return replaceClass2Id;
	}


	/**
	 * @return the mapParentToSource
	 */
	public HashMap<String, String> getMapParentToSource() {
		return mapParentToSource;
	}


	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * @return the umlId
	 */
	public String getUmlId() {
		return umlId;
	}


	/**
	 * @return the notationName
	 */
	public String getNotationName() {
		return notationName;
	}


	/**
	 * @return the notationId
	 */
	public String getNotationId() {
		return notationId;
	}
	

}
