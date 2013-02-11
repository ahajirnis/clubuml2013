<%-- 
    Document   : upload
    Created on : Nov 11, 2012, 12:11:25 PM
    Author     : Kai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Upload ECORE using Commons File Upload</h1>
        <h3>ID from Session: <font style="color: red;">${sessionScope.id}</font></h3>
        <h3>System Time: <font style="color: red;"><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%></font></h3>
        <form method="post" action="uploadServlet" enctype="multipart/form-data">
            Select an ECORE file: <br/>
            <input type="file" name="file"/><br/><br/>
            <input type="submit" name="submit" value="Submit"/>
        </form>
        
    </body>
</html>
