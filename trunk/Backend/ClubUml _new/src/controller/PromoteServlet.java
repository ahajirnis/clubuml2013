package controller;

/**
 * @author
 * Tong Wang
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Domain.Comment;
import Domain.Diagram;
import Domain.User;
import Repository.CommentDAO;
import Repository.DiagramDAO;

/**
 * Servlet implementation class PromoteServlet
 */
@WebServlet("/PromoteServlet")
public class PromoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int diagramId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		diagramId = Integer.parseInt(request.getParameter("diagramId"));
        String content = request.getParameter("comment");
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        int userId = user.getUserId();
        
        
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writtenTime = sdf.format(dt);
        
        Comment comment = new Comment(userId, content, writtenTime, diagramId);
        CommentDAO.addComment(comment);
        //store comments into database

        update(diagramId);//update promote diagram edited time.
        ArrayList<Diagram> diagrams = DiagramDAO.getDiagramList();
        ArrayList<Domain.Comment> comments = CommentDAO.getComments(diagramId);
        
        
        request.setAttribute("diagramId1", diagramId);
        request.setAttribute("path1", "upload/" + DiagramDAO.getDiagram(diagramId).getDiagramName());
        request.setAttribute("comments", comments);
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
	
	 public  void update(int id){
         
	        java.util.Date dt = new java.util.Date();
	        java.text.SimpleDateFormat sdf = 
	        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String currentTime = sdf.format(dt);
	        
	        try {     
	                Class.forName("com.mysql.jdbc.Driver");
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/clubuml2", "root", ""); 
	                Statement stmnt = con.createStatement();
	                String sql = "UPDATE diagram SET createdTime = '"+currentTime+"'WHERE diagram_Id='"+id+"' ";
	                stmnt.execute(sql);
	              
	            } catch (ClassNotFoundException ex) {
	                Logger.getLogger(PromoteServlet.class.getName()).log(Level.SEVERE, null, ex);
	            } catch (SQLException ex) {
	                Logger.getLogger(PromoteServlet.class.getName()).log(Level.SEVERE, null, ex);
	             }
	     }

}
