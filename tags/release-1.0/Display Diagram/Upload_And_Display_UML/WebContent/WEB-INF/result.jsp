<%-- 
    Document   : result
    Created on : Nov 11, 2012, 6:35:47 PM
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
        <h1>Result Page</h1>
        <p>ID from Session: <font style="color: red;">${sessionScope.id}</font></p>
        <p>System Time: <font style="color: red;"><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%></font></p>
        <p>Original File Name: <font style="color: red;">${requestScope.originalFileName}</font></p>
        <p>New File Name: <font style="color: red;">${requestScope.newFileName}</font></p>
        <p>Size: <font style="color: red;">${requestScope.size} bytes</font></p>
        <p>Upload Directory: <font style="color: red;">file:///c:/Users/Kai/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/Upload_And_Display_UML/uploads/</font></p>
        <p>Ecore Absolute Path: <font style="color: red;">${requestScope.absolutePath}</font></p>
        <p>Ecore File: <a href="${requestScope.relativePath}">${requestScope.relativePath}</a></p>
        <p>Java File: <a href="${requestScope.relativePath}.java">${requestScope.relativePath}.java</a></p>
        <p>Dot File: <a href="${requestScope.relativePath}.dot">${requestScope.relativePath}.dot</a></p>
        <p>Png File: <a href="${requestScope.relativePath}.png">${requestScope.relativePath}.png</a></p>
        
        <img src="${requestScope.relativePath}.png" alt="UML Diagram" height="300">
    </body>
</html>
