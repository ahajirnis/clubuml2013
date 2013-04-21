package controller.merge.xmi.xclass;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmiMergeClassDiProcessor {
	
	// fields for DOM
	private DocumentBuilderFactory docFactory = DocumentBuilderFactory
			.newInstance();
	private DocumentBuilder docBuilder;

	private Document umlDoc;
	private Element umlRootElement;
	
	// Add nodes to these two
	Element availablePage;
	Element children;
	
	public XmiMergeClassDiProcessor() {
		try {
			Initialize();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void addEmfPageIdentifier(String fileName, String Id) {

		String href = fileName + ".notation#" + Id;
		
		Element emfPageIdentifier = umlDoc.createElement("emfPageIdentifier");
		emfPageIdentifier.setAttribute("href", href);

		availablePage.appendChild(emfPageIdentifier);

		// Create another element
		emfPageIdentifier = umlDoc.createElement("emfPageIdentifier");
		emfPageIdentifier.setAttribute("href", href);

		children.appendChild(emfPageIdentifier);
	}
	
	private void Initialize() throws ParserConfigurationException {
		docBuilder = docFactory.newDocumentBuilder();

		// UML Header
		umlDoc = docBuilder.newDocument();
		umlRootElement = umlDoc.createElement("di:SashWindowsMngr");
		umlRootElement.setAttribute("xmi:version", "2.0");
		
		umlRootElement.setAttribute("xmlns:xmi",
				"http://www.omg.org/XMI");
		
		umlRootElement.setAttribute("xmlns:xsi",
				"http://www.w3.org/2001/XMLSchema-instance");
		
		umlRootElement.setAttribute("xmlns:di",
				"http://www.eclipse.org/papyrus/0.7.0/sashdi");
		umlDoc.appendChild(umlRootElement);
		
		Element pageList = umlDoc.createElement("pageList");
		umlRootElement.appendChild(pageList);
		
		// Hook nodes to this element
		availablePage = umlDoc.createElement("availablePage");
		pageList.appendChild(availablePage);
		
		Element sashModel = umlDoc.createElement("sashModel");
		sashModel.setAttribute("currentSelection", "//@sashModel/@windows.0/@children.0");
		umlRootElement.appendChild(sashModel);
		
		Element windows = umlDoc.createElement("windows");
		sashModel.appendChild(windows);
		
		Element childrenxsi = umlDoc.createElement("children");
		childrenxsi.setAttribute("xsi:type", "di:TabFolder");
		windows.appendChild(childrenxsi);
		
		// Hook nodes to this element
		children = umlDoc.createElement("children");
		childrenxsi.appendChild(children);
	}
	
	public File GenerateFile(String fileName) {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer;
		
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(umlDoc);
			File file = new File("C:\\temp\\" + fileName + ".di");
			StreamResult result = new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

			System.out.println("Di file created!");
			
			return file;
		} catch (TransformerException e) {
			System.out.println("Failed Di file");
			e.printStackTrace();
		}
		
		return null;

	}
}
