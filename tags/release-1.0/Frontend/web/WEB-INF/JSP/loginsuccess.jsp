<%-- 
    Document   : loginsuccess
    Created on : Oct 17, 2012, 8:56:42 AM
    Author     : Zhang Junyu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <title><font color="#61210B">ClubUML</font></title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>
        <script type="text/javascript" src = "js/register.js"></script>
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
  <div class="hbg">
                    <head> <title>login success</title> 
                        <style type="text/css"> 
                            <!--   #navbar ul 
                            {    
                                margin: 0;
                                padding: 5px;
                                list-style-type: none;         
                                text-align: center;
                                background-color: #780000 ;
                            }
                            
                            #navbar ul li 
                            {          display: inline;
                            }  #navbar ul li a 
                            {    
                                text-decoration: none;
                                padding: .2em 1em;         
                                color: #fff;         
                                background-color: #000; 
                            }
                            #navbar ul li a:hover
                            {        
                                color: #000; 
                                background-color: #fff;     
                            }  
                            --> 
                        </style> 
                    </head>
                    
    <body>
            <h1>Welcome to ClubUML!</h1>

            <style>
                body {background-color:#FFCC66;} 
            </style>
            </head>


            <div id="navbar"> 
                <ul>
                    <form action="Display" method="get">
                    <li><a href="index.jsp">Home</a></li> 
                    <li><input type ="submit" value="display"></li>
                    <li><a href="index.jsp">Project</a></li>  
                    <li><a href="index.jsp">User Info</a></li> 
                    <li><a href="index.jsp"">About Us</a></li> 
                    <li><a href="index.jsp">Blog</a></li> 
                    </form>
                </ul> 
            </div>

            <div> 
                <hr>

                    <p><font color="blue">Hello ${username}!</font></p>
            </div>
            <div class="content">
                <div class="content_resize">
                    <div class="mainbar">
                        <div class="article">
                            <h2>About Club</h2>
                            <div class="clr"></div>
                            <p><font color="black>"<span class="date">October 23, 2012</span> &nbsp;|&nbsp;</font></p>
                            <img src="images/Edraw_UML_Diagram-136763.jpg" width="263" height="146" alt="" class="fl" />
                            <p><font color="black"> <br />
                                    ClubUML is a web application which allows users to register and store his/her information to the ClubUML database. Upload more than one diagram. Display this diagram user can compare between two diagrams. Also add comments on the diagram. In this way so many user can improve their uml model for any project. Basically this tool is designed for Software engineers. And this tool can be used in any software engineering project
                                </font></p>
                            <p class="spec"><a href="#" class="rm">Read more</a> <a href="#" class="com">Comments (3)</a></p>
                        </div>


                        </body>
        
       
</html>
