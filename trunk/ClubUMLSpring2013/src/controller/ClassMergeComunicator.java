package controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import controller.comparer.xmi.XmiClassDiagramComparer;
import controller.upload.FileInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author Yanwu shen
 */
@WebServlet("/ClassMergeComunicator")
public class ClassMergeComunicator extends HttpServlet {
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
	
	public  ClassMergeComunicator(List<FileInfo> XmiFiles1,
			List<FileInfo> XmiFiles2)
	{
			comparer= new XmiClassDiagramComparer( XmiFiles1, XmiFiles2);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws ServletException, IOException {
		
		JSONObject obj,reqobj;
		RequestDispatcher dispatcher;
		
		String jsonString= request.getParameter("request");
		// test
		System.out.print("get massage:"+jsonString);
		// test
		reqobj = (JSONObject) JSONValue.parse(jsonString);
		
		obj=comparer.action(reqobj);
		jsonString= (String) reqobj.get("Request");
		request.setAttribute("response", obj);
		
		switch (jsonString) 
		{
		    
			case REQUEST_COMPARE:
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/mergeClass.jsp");
				dispatcher.forward(request, response);
				break;
			case REQUEST_REFRESH:
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				break;		
			case REQUEST_CONSOLIDATE:
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				break;	
			case REQUEST_ADD:
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/refineClass.jsp");
				dispatcher.forward(request, response);
				break;
			case REQUEST_BREAK:
				dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/selectClass.jsp");
				dispatcher.forward(request, response);
				break;
			}
		}
	
		
	}

