package controller;

/**
 * @author
 * Tong Wang
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.Comment;
import Domain.Diagram;
import Repository.CommentDAO;
import Repository.DiagramDAO;


/**
 * Servlet implementation class Display
 */
@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // retrive diagram list from database.
        ArrayList<Diagram> diagrams = DiagramDAO.getDiagramList();
        // set request.
        request.setAttribute("diagrams", diagrams);
        //set the first diagram in diagram list as the default dispaly diagram..
        request.setAttribute("firstPath", "upload/"+ diagrams.get(0).getDiagramName());
        request.setAttribute("firstDiagramId", diagrams.get(0).getDiagramId());
       
        //retrive comment of the dispaly diagram.
        ArrayList<Comment> comments = CommentDAO.getComments(diagrams.get(0).getDiagramId());
        
        request.setAttribute("comments", comments);
        HttpSession session = request.getSession(true);
        session.setAttribute("diagrams", diagrams);
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
