/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.clubuml2012.diagram.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.neu.clubuml2012.diagram.uml.DiagramFactory;

/**
 *
 * @author Kai
 */
public class UploadServlet extends HttpServlet {

    private static final String TMP_DIR_PATH = "/uploads/";
    private static final String DESTINATION_DIR_PATH = "/uploads/";
    private static final String LIB_DIR_PATH = "/WEB-INF/lib/";
    private static final long serialVersionUID = 1L;
    private File tmpDir;
    private File destinationDir;
    private File libDir;

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
        
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
        
        ServletContext context = getServletContext();
        tmpDir = new File(context.getRealPath(TMP_DIR_PATH));
        destinationDir = new File(context.getRealPath(DESTINATION_DIR_PATH));
        libDir = new File(context.getRealPath(LIB_DIR_PATH));
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setSizeThreshold(1 * 1024 * 1024);//1MB
        dfif.setRepository(tmpDir);

        ServletFileUpload uploadHandler = new ServletFileUpload(dfif);
        try {
            List<?> items = uploadHandler.parseRequest(request);
            Iterator<?> itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if ((!item.isFormField()) && (!item.getName().equals("")) && (!id.equals(""))) {//check if item is a file                   
                    String newName = renameFile(id, item.getName());//rename file                                      
                    File file = new File(destinationDir, newName);
                    item.write(file);
                    
                    String absolutePath = destinationDir + "\\";
                    String relativePath = context.getContextPath() + DESTINATION_DIR_PATH;
                    String libPath = libDir + "\\";
                    
                    request.setAttribute("originalFileName", item.getName());
                    request.setAttribute("newFileName", newName);
                    request.setAttribute("size", item.getSize());
                    request.setAttribute("absolutePath", absolutePath + newName);
                    request.setAttribute("relativePath", relativePath + newName);
                    request.setAttribute("javaFile", relativePath + newName + ".java");
                    
                    DiagramFactory df = new DiagramFactory(absolutePath, newName, libPath);
                    df.process();
                    //String ecorePath = df.process();
                    //request.setAttribute("returnValue", ecorePath);
                }
            }
            
        } catch (Exception e) {
        }

        RequestDispatcher rd = request.getRequestDispatcher("result.htm");
        rd.forward(request, response);

    }
    
    private String renameFile(String userId, String originalFileName){
        String time = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new java.util.Date());
        String newName = userId + "_" + time + "_" + originalFileName;
        return newName;
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
