/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.UserDAO;

/**
 *
 * @author wintor12
 */
public class RegisterServlet extends HttpServlet {

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

	RequestDispatcher dispatcher = null;
	response.setContentType("text/html;charset=UTF-8");

	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String question = request.getParameter("securityQuestion");
	String answer = request.getParameter("securityAnswer");

	User userObj = new User(username, password, email, question, answer, 2);

	try {
	    UserDAO.addUser(userObj);
	    dispatcher = request.getRequestDispatcher("WEB-INF/JSP/home.jsp");
	    dispatcher.forward(request, response);
	} catch (IllegalArgumentException ex) {
	    dispatcher = request.getRequestDispatcher("register.jsp");
	    dispatcher.forward(request, response);
	}
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
	protected void doGet
	(HttpServletRequest request, HttpServletResponse response
	)
            throws ServletException
	, IOException {
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
	protected void doPost
	(HttpServletRequest request, HttpServletResponse response
	)
            throws ServletException
	, IOException {
	    processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo

	    () {
        return "Short description";
	}// </editor-fold>
    }
