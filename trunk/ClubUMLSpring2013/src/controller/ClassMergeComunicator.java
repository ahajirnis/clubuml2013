package controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import repository.DiagramDAO;

import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.upload.FileInfo;
import domain.Diagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Yanwu shen
 */

public class ClassMergeComunicator extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request
     * 			servlet request
     * @param response
     * 			servlet response
     * @throws ServletException
     * 			If a servlet-specific error occurs
     * @throws IOException
     * 			If an I/O error occurs
     */
	private final static String REQUEST_REFRESH = "Refresh";
	private final static String REQUEST_CONSOLIDATE = "Consolidate";
	private final static String REQUEST_ADD = "Add";
	private final static String REQUEST_BREAK = "Break";
	private final static String REQUEST_COMPARE = "Compare";
		
	private XmiClassDiagramComparer comparer;
	
	public ClassMergeComunicator() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		
		// Get diagram IDs from checked boxes
		//String[] checked = (String[]) request.getParameterValues("check");
		//int diagramId1 = Integer.parseInt(checked[0]);
		//int diagramId2 = Integer.parseInt(checked[1]);
		
		JSONObject obj,reqobj;
		RequestDispatcher dispatcher;
		
		String reqString= request.getParameter("request");
		reqobj = (JSONObject) JSONValue.parse(reqString);
		
		String reqType = (String) reqobj.get("Request");
		
		
		switch (reqType) 
		{
			case REQUEST_COMPARE:
				obj=comparer.action(reqobj);
				request.setAttribute("response", obj);
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/mergeClass.jsp");
				dispatcher.forward(request, response);
				break;
			case REQUEST_REFRESH:
				if(comparer==null){
					// Initialize comparer using diagram IDs
					int cd1ID = Integer.parseInt((String) reqobj.get("Diagram1"));
					int cd2ID = Integer.parseInt((String) reqobj.get("Diagram2"));
					Diagram cd1 = DiagramDAO.getDiagram(cd1ID);
					Diagram cd2 = DiagramDAO.getDiagram(cd2ID);
					
					// to get the .uml file names:
					String cd1UmlFileName = cd1.getFilePath().substring(
							cd1.getFilePath().lastIndexOf("/") + 1,
							cd1.getFilePath().length());
					String cd2UmlFileName = cd2.getFilePath().substring(
							cd2.getFilePath().lastIndexOf("/") + 1,
							cd2.getFilePath().length());
					
					// to get the UML path without the file names:
					String cd1UmlPath = cd1.getFilePath().substring(0,
							cd1.getFilePath().lastIndexOf("/") + 1);
					String cd2UmlPath = cd2.getFilePath().substring(0,
							cd2.getFilePath().lastIndexOf("/") + 1);
					
					List<FileInfo> lfi1 = new ArrayList<FileInfo>();
					List<FileInfo> lfi2 = new ArrayList<FileInfo>();
					FileInfo fi1_not = new FileInfo(cd1.getNotationFilePath(), cd1.getNotationFileName(), "");
					FileInfo fi1_uml = new FileInfo(cd1UmlPath, cd1UmlFileName, "");
					FileInfo fi2_not = new FileInfo(cd2.getNotationFilePath(), cd2.getNotationFileName(), "");
					FileInfo fi2_uml = new FileInfo(cd2UmlPath, cd2UmlFileName, "");
					lfi1.add(fi1_not); lfi1.add(fi1_uml);
					lfi2.add(fi2_not); lfi2.add(fi2_uml);
					
					comparer = new XmiClassDiagramComparer(lfi1, lfi2);
				}
				//obj=comparer.action(reqobj); // action() doesn't work
				obj=comparer.action(reqobj);
				request.setAttribute("response", obj);
				
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				break;		
			case REQUEST_CONSOLIDATE:
				obj=comparer.action(reqobj);
				request.setAttribute("response", obj);
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				/*
				// Now do a Refresh
				String refreshString= "{\"Request\":\"Refresh\"}";
				reqobj = (JSONObject) JSONValue.parse(refreshString);
				obj = comparer.action(reqobj);
				request.setAttribute("response", obj);
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				*/
				break;	
			case REQUEST_ADD:
				obj=comparer.action(reqobj);
				request.setAttribute("response", obj);
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/refineClass.jsp");
				dispatcher.forward(request, response);
				break;
			case REQUEST_BREAK:
				obj=comparer.action(reqobj);
				request.setAttribute("response", obj);
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				break;
			}
		}
	
		
	public static void main(String[] args) {
		
		JSONObject obj,reqobj;
		
		// test Refresh
		int cd1ID = 64;
		int cd2ID = 65;
		String reqString= "{\"Request\":\"Refresh\"}";
		reqobj = (JSONObject) JSONValue.parse(reqString);
		
		Diagram cd1 = DiagramDAO.getDiagram(cd1ID);
		Diagram cd2 = DiagramDAO.getDiagram(cd2ID);
		
		// to get the .uml file names:
		String cd1UmlFileName = cd1.getFilePath().substring(
				cd1.getFilePath().lastIndexOf("/") + 1,
				cd1.getFilePath().length());
		String cd2UmlFileName = cd2.getFilePath().substring(
				cd2.getFilePath().lastIndexOf("/") + 1,
				cd2.getFilePath().length());
		
		// to get the UML path without the file names:
		String cd1UmlPath = cd1.getFilePath().substring(0,
				cd1.getFilePath().lastIndexOf("/") + 1);
		String cd2UmlPath = cd2.getFilePath().substring(0,
				cd2.getFilePath().lastIndexOf("/") + 1);
		
		List<FileInfo> lfi1 = new ArrayList<FileInfo>();
		List<FileInfo> lfi2 = new ArrayList<FileInfo>();
		FileInfo fi1_not = new FileInfo(cd1.getNotationFilePath(), cd1.getNotationFileName(), "");
		FileInfo fi1_uml = new FileInfo(cd1UmlPath, cd1UmlFileName, "");
		FileInfo fi2_not = new FileInfo(cd2.getNotationFilePath(), cd2.getNotationFileName(), "");
		FileInfo fi2_uml = new FileInfo(cd2UmlPath, cd2UmlFileName, "");
		lfi1.add(fi1_not); lfi1.add(fi1_uml);
		lfi2.add(fi2_not); lfi2.add(fi2_uml);
		
		XmiClassDiagramComparer testComparer = new XmiClassDiagramComparer(lfi1, lfi2);
		
		obj = testComparer.action(reqobj);
		
		// DEBUG
		System.out.println(obj.toJSONString());
		
		// test Compare
		String reqString2= "{\"Class1\":\"Vehicle\",\"Class2\":\"Vehicle\",\"Request\":\"Compare\"}";
		reqobj = (JSONObject) JSONValue.parse(reqString2);
		obj = testComparer.action(reqobj);
		System.out.println(obj.toJSONString());
		
		// test Consolidate
		String reqString3 = "{\"Request\":\"Consolidate\",\"Class1\":{\"Class\":\"Vehicle\",\"Attributes\":[],\"Operations\":[\"Start()\"]},\"Class2\":{\"Class\":\"Vehicle\",\"Attributes\":[\"<Undefined> Color\"],\"Operations\":[]},\"Same\":{\"Attributes\":[],\"Operations\":[]},\"Name\":\"Vehicle_Vehicle\"}";
		reqobj = (JSONObject) JSONValue.parse(reqString3);
		obj = testComparer.action(reqobj);
		System.out.println(obj.toJSONString());
	}

}

