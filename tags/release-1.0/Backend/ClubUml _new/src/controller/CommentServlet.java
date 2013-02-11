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

import Domain.User;
import Domain.Comment;
import Repository.CommentDAO;
import Repository.DiagramDAO;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content = request.getParameter("comment");
        String diagramId2 = request.getParameter("diagramId");
        int diagramId = Integer.parseInt(diagramId2);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String writtenTime = sdf.format(dt);
        
        Comment comment = new Comment(userId, content, writtenTime, diagramId);
        
        CommentDAO.addComment(comment);
        
      
        ArrayList<Comment> comments = CommentDAO.getComments(diagramId);
        
        request.setAttribute("diagramId1", diagramId);
        request.setAttribute("path1", "upload/" + DiagramDAO.getDiagram(diagramId).getDiagramName());
        request.setAttribute("comments", comments);
        
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
