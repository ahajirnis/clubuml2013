/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Domain.Comment;
import Domain.Diagram;
import Domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository.CommentDAO;
import repository.DiagramDAO;
import repository.EditingHistoryDAO;
import repository.UserDAO;
import service.Service;

/**
 *
 * @author wintor12
 */
public class Display extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// retrive diagram list from database.
	ArrayList<Domain.EditingHistory> editedDiagrams = EditingHistoryDAO.getPriorityList();
	if (!editedDiagrams.isEmpty()) {
	    ArrayList<Domain.Diagram> diagrams = new ArrayList();
	    for (int i = 0; i < editedDiagrams.size(); i++) {
		Diagram diagObj = DiagramDAO.getDiagram(editedDiagrams.get(i).getDiagramId());
		diagObj.setCreatedTime(editedDiagrams.get(i).getEditingTime());
		diagrams.add(diagObj);
	    }
	    if (!diagrams.isEmpty()) {
		request.setAttribute("diagrams", diagrams);
		//set the first diagram in diagram list as the default dispaly diagram..
		request.setAttribute("firstPath", diagrams.get(0).getEcoreFilePath() + ".png");
		request.setAttribute("diagramId1", diagrams.get(0).getDiagramId());
	    }
	    ArrayList<Comment> commentListObj = CommentDAO.getComment(editedDiagrams.get(0).getDiagramId());
	    if (!commentListObj.isEmpty()) {
		for (int i = 0; i < commentListObj.size(); i++) {
		    commentListObj.get(i).setUserName(UserDAO.getUser(commentListObj.get(i).getUserId()).getUserName());
		}
		request.setAttribute("comments", commentListObj);
	    }
	}
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
	dispatcher.forward(request, response);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
}
