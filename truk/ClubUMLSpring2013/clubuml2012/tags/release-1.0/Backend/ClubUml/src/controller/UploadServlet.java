package controller;

/**
 * @author
 * Tong Wang
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Domain.Diagram;
import Domain.User;
import Repository.CommentDAO;
import Repository.DiagramDAO;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute("user");
        String username = user.getUserName();
        int userID = user.getUserId();
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

            path = "C:\\Users\\wintor12\\Documents\\NetBeansProjects\\ClubUml2\\web\\upload\\";//get the store path

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
                        Diagram diagram = new Diagram(fileName, currentTime, true, userID, path);
                        DiagramDAO.addDiagram(diagram);
                        
                        ArrayList<Diagram> diagrams = DiagramDAO.getDiagramList();
                       
                        session.setAttribute("diagrams", diagrams);
                        request.setAttribute("firstPath", "upload/"+ diagrams.get(0).getDiagramName());
                        request.setAttribute("firstDiagramId", diagrams.get(0).getDiagramId());

                        ArrayList<Domain.Comment> comments = CommentDAO.getComments(diagrams.get(0).getDiagramId());

                        request.setAttribute("comments", comments);

                        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/JSP/display.jsp");
                        dispatcher.forward(request, response);
                        
                        
                    }
                    }catch(Exception ex) {
                        
                    }

         }
 
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
