<%-- 
    Document   : display
    Created on : Oct 20, 2012, 2:25:33 PM
    Author     : wintor12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src = "js/display.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details Display</title>
        <link href="css/display.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="header">
            <h1 id="welcome">Welcome to ClubUML!</h1>
        </div>
        <div id="diagram" 
            <p id="show_diagram">

                <c:if test = "${requestScope.diagramId1 != null}">
                    <img border="1px" src="${requestScope.path1}" width="100%" height="90%"/>
                </c:if>
                <c:if test = "${requestScope.diagramId1 == null}">   
                    <img border="1px" src="${requestScope.firstPath}" width="100%" height="90%"/>
                </c:if>
        </div>
        
        <div id="comment">
            
        <form action ="Comment" method="post">
            <input type ="text" name="comment">
            <c:if test = "${requestScope.diagramId1 != null}">
            <input type="hidden" name="diagramId" value="${requestScope.diagramId1}">
            </c:if>
            <c:if test = "${requestScope.diagramId1 == null}"> 
            <input type="hidden" name="diagramId" value="${sessionScope.diagrams.get(0).diagramId}">       
            </c:if>
            <input type="submit" name="value" value="comment"/>
        </form>
        
            <c:forEach items="${requestScope.comments}" var="comment">
                <hr>
                ${comment.userName}: <br> 
                ${comment.content} <br>
                ${comment.commentTime} <br>
                
            </c:forEach>
            
    </div>
        


        <div id="list">
            <form action="UploadServlet" method="post" enctype="multipart/form-data" >
                <input type="file" name="file" size="50" />
                <input class="submit" type="submit" value="upload" >
            </form>



            <table>
                <form action="DisplayDiagram" method="post">
                    <c:forEach items="${sessionScope.diagrams}" var="diagram">
                        <tr>
                            <td><input type="checkbox" name="check" value="${diagram.diagramId}"/>
                                ${diagram.diagramName}</td>
                            <td>${diagram.createdTime}</td>

                        </tr>
                    </c:forEach>


            </table>
            <input type="submit" value="Display" name="submit" />
            <input type="submit" value="Go to compare" name="submit"/>
            <input type="submit" value="Download" name="submit"/>

        </form>


    </div>

    


</body>
</html>
