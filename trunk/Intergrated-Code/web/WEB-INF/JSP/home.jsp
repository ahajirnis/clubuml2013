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
		    <style>
			body {background-color:#FFCC66;}
		    </style>
		    </head>
		    <div>
			<hr>
			<p><font color="white">Hello ${username}!</font></p>
			<div style="float:right; margin-top: -45px;">
			    <a href="Display" style="text-decoration: none;"><button>Proceed to Project</button></a>
			</div>
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
					ClubUML is the leading open source UML modeling tool and includes support for all standard UML 1.4 diagrams. It runs on any Java platform and is available in ten languages. See the feature list for more details.
					ClubUML 0.26 and 0.26.2 were downloaded over 80,000 times and are in use all over the world. See the download statistics.
					ClubUML is distributed under the  Public License (PL) 1.0.
					</font></p>
				    <p class="spec"><a href="#" class="rm">Read more</a> <a href="#" class="com">Comments (3)</a></p>
				</div>
			    </div>
			</div>
		    </div>
		    </body>
		    </html>
