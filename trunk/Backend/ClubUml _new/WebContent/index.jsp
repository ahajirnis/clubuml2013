<%-- 
    Document   : index
    Created on : Oct 17, 2012, 1:09:40 AM
    Author     : wintor12
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
    <p>User Login here!!</p>
<p>
     <form id="form1" name="form1" method="post" action="LoginServlet">
         <p>
        <label><font color="#61210B">Username :</font></label>
     <input type="text" name="username"  />
     </p>
    <p><font color="#61210B">Password :</font>
      <label>
        <input type="password" name="password" id="Password" />
        <tr><td colspan=2><font face="verdana,arial" size=-1>Not member yet? Click <a href="register.jsp"><font color="blue">here</font></a> to register.</td></tr>
</label>
    </p>
    
     
    <label>
          <input type="submit" name="Login" id="Submit" value="Login" />
        </label>
         
    </form>
          
          
    <p>
      <label>
          
      </label> 
    </p>
    </div>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <p><font color="#61210B">About ClubUML</font></p>
          <div class="clr"></div>
          <p><span class="date"><font color="#61210B">October 23, 2012</font></span> &nbsp;|&nbsp;</p>
          <img src="images/N0-UML-cluttered.jpg" width="263" height="146" alt="" class="fl" />
          <p><font color="#61210B"><br />
        A UML tool or UML modeling tool is a software application that supports some or all of the notation and semantics associated with the Unified Modeling Language (UML), which is the industry standard general purpose modeling language for software engineering. </font></p>
          <p class="spec"><a href="#" class="rm">Read more</a> <a href="#" class="com">Comments (3)</a></p>
        </div>
        <div class="article">
          <h2><span>ClubUML story</span></h2>
          <div class="clr"></div>
          <p><span class="date">October 23, 2012</span> &nbsp;|&nbsp;</p>
          <img src="images/img2.jpg" width="263" height="146" alt="" class="fl" />
          <p><font color="black">ClubUML is the open source UML modeling tool and includes support for all standard UML  diagrams. It runs on any Java platform and is available in ten languages. See the feature list for more details.</font></p>
          <p><font color="black">ClubUML tool can be used to upload UML diagram display this diagrams and compare between two UML diagrams</font></p>
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
            <li>Users</li>
            <li>Specialities</li>
            <li></li>
            <li></li>
          </ul>
        </div>
          <div class="gadget">
          <h2 class="star">Founders</h2>
          <div class="clr"></div>
          <ul class="ex_menu">
            <li><font color="black">By using this tool , user can comments on other UML diagrams and all the uploaded diagrams and related comments will be stored into the CulbUML database </font></li>
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
      <p class="lf">&copy; Copyright Ruma  Nayak </p>
      <p class="rf">&nbsp;</p>
      <div class="clr"></div>
    </div>
  </div>
</div>
    
         
</body>  
</html>
