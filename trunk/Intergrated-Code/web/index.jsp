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
        <link href="style.css" rel="stylesheet" type="text/css" />
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
		    <div class="mainbar">
			<div class="article">
			    <h2>About MVCH</h2>
			    <div class="clr"></div>
			    <p><span class="date">October 23, 2012</span> &nbsp;|&nbsp;</p>
			    <img src="images/N0-UML-cluttered.jpg" width="263" height="146" alt="" class="fl" />
			    <p><font color="black"><br />
				A UML tool or UML modeling tool is a software application that supports some or all of the notation and semantics associated with the Unified Modeling Language (UML), which is the industry standard general purpose modeling language for software engineering. </font></p>
			    <p class="spec"><a href="#" class="rm">Read more</a> <a href="#" class="com">Comments (3)</a></p>
			</div>
			<div class="article">
			    <h2><span>MVCH story</span></h2>
			    <div class="clr"></div>
			    <p><span class="date">October 23, 2012</span> &nbsp;|&nbsp;</p>
			    <img src="images/img2.jpg" width="263" height="146" alt="" class="fl" />
			    <p><font color="black">ClubUML is the leading open source UML modeling tool and includes support for all standard UML 1.4 diagrams. It runs on any Java platform and is available in ten languages. See the feature list for more details.</font></p>
			    <p><font color="black">ClubUML 0.26 and 0.26.2 were downloaded over 80,000 times and are in use all over the world. See the download statistics.</font></p>
			    <p class="spec"><a href="#" class="rm">Read more</a> <a href="#" class="com">Comments (7)</a></p>
			</div>
		    </div>
		    <div class="sidebar">
			<div class="gadget">
			    <h2 class="star"> Menu</h2>
			    <div class="clr"></div>
			    <ul class="sb_menu">
				<li>Services</li>
				<li>History of the ClubUML</li>
				<li>Userss</li>
				<li>Specialities</li>
				<li></li>
				<li></li>
			    </ul>
			</div>
			<div class="gadget">
			    <h2 class="star">Founders</h2>
			    <div class="clr"></div>
			    <ul class="ex_menu">
				<li><font color="black">In September 2012, some students  announced that they were  ceasing work on BOUML other than bug fixes due to "copyright violations and mismanagement from wikipedia administrators".
				    The . several patches were released after the announcement, the last being ClubUML 4.23 ultimate patch 7 in October 2012..</font></li>
			    </ul>
			</div>
		    </div>
		    <div class="clr"></div>
		</div>
	    </div>
	    <div class="fbg">
		<div class="fbg_resize">
		    <div class="col c1">
			<h2><span><font color="black">ClubUML Gallery</font></span></h2>
			<a href="#"><img src="images/pix1.jpg" width="56" height="56" alt="" /></a> <a href="#"><img src="images/pix2.jpg" width="56" height="56" alt="" /></a> <a href="#"><img src="images/pix3.jpg" width="56" height="56" alt="" /></a> <a href="#"><img src="images/pix4.jpg" width="56" height="56" alt="" /></a> <a href="#"><img src="images/pix5.jpg" width="56" height="56" alt="" /></a> <a href="#"><img src="images/pix6.jpg" width="56" height="56" alt="" /></a> </div>
		    <div class="col c2">
			<h2>&nbsp;</h2>
		    </div>
		    <div class="col c3">
			<h2>&nbsp;</h2>
		    </div>
		    <div class="clr"></div>
		</div>
	    </div>
	    <div class="footer">
		<div class="footer_resize">
		    <p class="lf">&copy; Copyright Ruma &amp; Tong</p>
		    <p class="rf">&nbsp;</p>
		    <div class="clr"></div>
		</div>
	    </div>
	</div>
    </body>
</html>
