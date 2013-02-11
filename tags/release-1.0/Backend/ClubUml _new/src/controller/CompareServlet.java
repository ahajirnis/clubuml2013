package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompareServlet
 */
@WebServlet("/CompareServlet")
public class CompareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//showPdf(fileName, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public  ByteArrayOutputStream getByteArrayOutputStream(String fileName) throws IOException {
	     
        String filePath = "C:\\upload";
        String folderPath=filePath + "\\" + fileName;
        File file = new File(folderPath);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
         try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    System.out.println("read " + readNum + " bytes,");
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
         
        return bos;
     }
	
	 public void showPdf(String fileName,HttpServletRequest request, HttpServletResponse response) throws IOException{
         
         response.setContentType("application/pdf");
         response.setHeader("Content-disposition", "inline;attachment;filename="+fileName);
         ByteArrayOutputStream baos = getByteArrayOutputStream(fileName);
         response.setContentLength(baos.size());
         ServletOutputStream sos = response.getOutputStream();
         baos.writeTo(sos);
         sos.flush();
      
     }



}
