/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Domain.Diagram;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.Service;

/**
 *
 * @author wintor12
 */
public class Comment extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String content = request.getParameter("comment");
        String diagramId2 = request.getParameter("diagramId");
        int diagramId = Integer.parseInt(diagramId2);
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String writtenTime = sdf.format(dt);
        Service g = new Service();
        
        g.storeComment(userId,content,writtenTime,diagramId);
        
        
        
        ArrayList<Diagram> diagrams = g.getDiagramList();
        ArrayList<Domain.Comment> comments = g.getComments(diagramId);
        
        request.setAttribute("diagramId1", diagramId);
        request.setAttribute("path1", "upload/" + g.getDiagramName(diagramId));
        request.setAttribute("comments", comments);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
        dispatcher.forward(request, response);
        
    }
    
//    public void store(int id, String content, String time, int diagramId)
//    {
//        try {
//            
//            
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubuml2", "root", "");
//            Statement stmnt = con.createStatement();
//            
//                       
////            String sql1 = "INSERT INTO diagram(`diagramName`, `filePath`,`createdTime`, `owner_Id`) VALUES('" + fileName + 
////                    "','" + path +"','" + time +"','"+ userID + "')" ;
//            
//            String sql = "INSERT INTO comment(`user_Id`,`content`,`writtenTime`,`diagram_Id`)"
//                            + " VALUES('"+id+"','"+content+"','"+time+"','"+diagramId+"')";
//            
//            stmnt.execute(sql);
//            
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//    
//    
//    }

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
