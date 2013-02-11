package controller;

/**
 * @author
 * Tong Wang
 */

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.User;
import Repository.UserDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get Parameters from jsp page. 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = UserDAO.getUser(username, password);
        if(user == null)
        	response.sendRedirect("invalid.jsp");
        else
        {

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/loginsuccess.jsp");
            dispatcher.forward(request, response);
        }
        
       
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
