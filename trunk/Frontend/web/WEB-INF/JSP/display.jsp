<%-- 
    Document   : display
    Created on : Oct 20, 2012, 2:25:33 PM
    Author     : wintor12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
     <head>
        <p></p>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>
        <script type="text/javascript" src = "js/register.js"></script>

</head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details Display</title>
        <link href="css/display.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
         <div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo">
          
       <p><a href="#">Welcome to ClubUML</a></p>
        
      </div>
        <div class="clr"></div>
    </div>
  </div>
        <div id="header">
            <h1 id="welcome">Welcome to ClubUML!</h1>
	</div>
	<div id="diagram">
            <p id="show_diagram">display digram here!
        <img border="1px" src="c:/upload/classDiagramInitial.jpg" width="100" height="100"/>
        </div>
        
        <div id="right">
            <form action="UploadServlet" method="post" enctype="multipart/form-data">
                <input type="file" name="file" size="50" />
                <input class="submit" type="submit" value="upload">
            </form>
            
            <table>
                <form action="DisplayDiagram" method="post">
                <c:forEach items="${requestScope.diagrams}" var="diagram">
                    <tr>
                        <td><input type="checkbox" name="check" value="(${diagram.path}/${diagram.diagramName}) "/>
                            ${diagram.diagramName} </td>
                        <td>${diagram.time}</td>
                        

                    </tr>
                </c:forEach>
                    <input  type="submit" value="Display"/>
                </form>
            </table>
            
	</div>
        
	<div id="comment">
                <form method="get">
                    <input type="text" class="texts" name="comment">
                    <input type="submit" value="comment">
                </form>
	</div>
        
        <div id="compare">
            <form action ="Compare" method="post">
                <input class="compare_button" type="submit" value="compare">
            </form>
	</div>
         		
    </body>
</html>
