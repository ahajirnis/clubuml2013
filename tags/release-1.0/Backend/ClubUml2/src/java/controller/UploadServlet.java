/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Domain.Diagram;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.Service;

/**
 *
 * @author wintor12
 */
public class UploadServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        String username =  (String)session.getAttribute("username");
        int userID = (Integer)session.getAttribute("userId");
        String fileName = null;
        String path = null;
        
        /*
         * upload process
         */
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);
        if(isMultipart){
            
            File file ;

            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;

            ServletContext context = this.getServletContext();
            path = context.getInitParameter("file-upload");//get the store path from web.xml

           // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) 
            {

                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("c:\\temp"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax( maxFileSize );
                
                try{ 
                // Parse the request to get file items.
                    List fileItems = upload.parseRequest(request);

                    // Process the uploaded file items
                    Iterator i = fileItems.iterator();
                    
                    while ( i.hasNext () ) 
                    {
                        FileItem fi = (FileItem)i.next();
                       
                        if ( !fi.isFormField () )	
                        {
                        // Get the uploaded file parameters
                             String fieldName = fi.getFieldName();
                           
                             fileName = fi.getName();
                             boolean isInMemory = fi.isInMemory();
                             long sizeInBytes = fi.getSize();
                            // Write the file
                            if( fileName.lastIndexOf("\\") >= 0 )
                            {
                                file = new File( path + 
                                 fileName.substring( fileName.lastIndexOf("\\"))) ;
                             }else{
                                file = new File( path + 
                                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                             }
                           fi.write( file ) ;
                             
                         }
                        
                        path = path.substring(0, path.length()-2);
                        java.util.Date dt = new java.util.Date();
                        java.text.SimpleDateFormat sdf = 
                        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        String currentTime = sdf.format(dt);
                        //store the upload diagram information into databse
                        storeDatabase(username,path,fileName, currentTime, userID);
                        
                        ArrayList<Diagram> diagrams = new Service().getDiagramList();
                       
                        session.setAttribute("diagrams", diagrams);
                        request.setAttribute("firstPath", "upload/"+ diagrams.get(0).getDiagramName());
                        request.setAttribute("firstDiagramId", diagrams.get(0).getDiagramId());

                        ArrayList<Domain.Comment> comments = new Service().getComments(diagrams.get(0).getDiagramId());

                        request.setAttribute("comments", comments);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
                        dispatcher.forward(request, response);
                        
                        
                    }
                    }catch(Exception ex) {
                        
                    }

         }
 
            }
    }
    
    /*
     * function to store upload diagram informatio into database.
     */
    private void storeDatabase(String username, String path, String fileName, String time, int userID){
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubuml2", "root", "");
            Statement stmnt = con.createStatement();      
            String sql1 = "INSERT INTO diagram(`diagramName`, `filePath`,`createdTime`, `owner_Id`) VALUES"
                            + "('" + fileName + "','" + path +"','" + time +"','"+ userID + "')" ;            
            stmnt.execute(sql1);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
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
