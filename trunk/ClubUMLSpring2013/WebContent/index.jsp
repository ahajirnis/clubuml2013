<%--
    Document   : index
    Created on : Oct 17, 2012, 1:09:40 AM
    Author     : wintor12
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-color: #F3F3F3">
    <head>
        <title>ClubUML</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link href="login.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>
        <script type="text/javascript" src = "js/register.js"></script>
    </head>
    <body style="background-color: #F3F3F3">
	<div class="main">
	    <div class="header">
		<div class="header_resize">
		    <div class="logo">
			<h1><a href="#">ClubUML</a></h1>
		    </div>
		    <div class="clr"></div>
		</div>
	    </div>
	    <div class="hbg" style="padding-left: 40px; padding-top: 10px;">
		<p class="red">
		    <label>User Login</label>
		<p>
		<form id="form1" name="form1" method="post" action="LoginServlet">
		    <p class="red">
			Username  :
			<input type="text" name="username"/>
		    </p>
		    <p class="red">
			Password :
			<input type="password" name="password" id="Password" />
			<font face="verdana,arial" size=-1>Not member yet? Click <a href="register.jsp"><font color="blue">here</font></a> to register.
		    </p>
		    <label>
			<input type="submit" name="Login" id="Submit" value="Login" />
		    </label>
		</form>
	    </div>
	    <div class="content">
		<div class="content_resize">
			<div class="article1">
			    <h2>About ClubUML</h2>
			    <div class="clr2"><p><span class="date">October 23, 2012</span></p></div>			    
			    <img src="images/N0-UML-cluttered.jpg" width="263" height="146" alt="" class="fl" />
			    <p><font color="black">This ClubUML project is developed by the CSYE7945 Software Engineering Project class in the Spring 2013 semester.  The purpose of the project is to develop an application which provides a way to compare different versions of UML diagrams in order to decide on a final version of the diagram. </font></p>
			</div>
			<div class="article2">
			    <h2><span>ClubUML2013</span></h2>
			    <div class="clr2"><p><span class="date">April 22, 2013</span></p></div>			    
			    <img src="images/img2.jpg" width="263" height="146" alt="" class="fl" />
			    <p><font color="black">An initial version of the ClubUML software was developed in Fall 2012, so the work this semester consisted of maintenance.  The Spring 2013 version of the software supports uploading UML 2.0 class or sequence diagrams and the ability to merge two class diagrams into a new version, among other enhancements.</font></p>
			</div>
		    <div class="clr"></div>
		</div>
	    </div>
	    <div class="footer">
		<div class="footer_resize">
		    <p class="lf">&copy; Copyright ClubUML Team 2013</p>
		    <p class="rf">&nbsp;</p>
		    <div class="clr"></div>
		</div>
	    </div>
	</div>
    </body>
</html>
