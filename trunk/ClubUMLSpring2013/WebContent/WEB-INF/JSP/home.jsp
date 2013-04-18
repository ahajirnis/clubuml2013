<%--
    Document   : loginsuccess
    Created on : Oct 17, 2012, 8:56:42 AM
    Author     : Zhang Junyu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/cufon-yui.js"></script>
        <script type="text/javascript" src="js/arial.js"></script>
        <script type="text/javascript" src="js/cuf_run.js"></script>

	<style type="text/css">
		body{
		background-color:#FFCC66;
		}
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
        <div class="main">
          <div class="header">
            <div class="header_resize">
                    <div class="logo">
                    </div>
		    <h1 style="color: white">ClubUML</h1>
		    <div>
	   	  </div>
   	  	</div>
			<hr>
			<p style="margin-left: 45px"><font color="white">Hello ${username}!</font></p>
			<div style="float:right; margin-top: -45px;margin-right: 45px;" >
			    <a href="Display" style="text-decoration: none;"><button>Proceed to Project</button></a>
			</div>
		    </div>
		    <div class="content">
			<div class="content_resize">
			    <div class="mainbar">
				<div class="article">
				    <h2>About ClubUML 2013</h2>
				    <div class="clr"></div>
				   <!--  <p><font color="black>"<span class="date">October 23, 2012</span> &nbsp;|&nbsp;</font></p> -->
				    <img src="images/Edraw_UML_Diagram-136763.jpg" width="263" height="146" alt="" class="fl" />
				    <p><font color="black"> <br />
					This ClubUML project is developed by the CSYE7945 Software Engineering Project class in the Spring 2013 semester.  The purpose of the project is to develop an application which provides a way to compare different versions of UML diagrams in order to decide on a final version of the diagram.  An initial version of the ClubUML software was developed in Fall 2012, so the work this semester consisted of maintenance.  The Spring 2013 version of the software supports uploading UML 2.0 class or sequence diagrams and the ability to merge two class diagrams into a new version, among other enhancements.
					</font></p>
				</div>
			    </div>
			</div>
		    </div>
	    </div>
		    </body>
		    </html>
