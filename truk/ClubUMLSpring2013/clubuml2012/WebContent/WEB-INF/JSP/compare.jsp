<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="background-color: #F3F3F3" xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<title>Compare Diagrams</title>
    </head>

    <body style="background-color: #F3F3F3">
	<div id="myHeader">
	    <h1 id="banner">Compare Diagrams</h1>
	</div>
	<div id="myContainer">
	    <div class="file1" style="float:left;">
		<img  src="${requestScope.path1}"/>
	    </div>
	    <div class="compare" style="padding-left: 60px; padding-top: 100px;">
		<form action="Compare" method="post" >
		    <input type="hidden" name="file1" value="${requestScope.diagramId1}"/>
		    <input type="hidden" name="file2" value="${requestScope.diagramId2}"/>
		    <input  name="submit" type="submit" value="Compare" />
		</form>
	    </div>
	    <div class="file2">
		<img src="${requestScope.path2}" />
	    </div>
	</div>
    </body>
</html>
