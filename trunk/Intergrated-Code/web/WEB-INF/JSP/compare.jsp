<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	<title>compare page</title>
    </head>

    <body>
	<div class="file1" style="float:left;">
	    <img  src="${requestScope.path1}"/>
	</div>
	<div class="compare">
	    <form action="Compare" method="post" >
		<input type="hidden" name="file1" value="${requestScope.diagramId1}"/>
		<input type="hidden" name="file2" value="${requestScope.diagramId2}"/>
		<input  name="submit" type="submit" value="Compare" />
	    </form>
	</div>


	<div class="file2" style="float:left;">
	    <img src="${requestScope.path2}" />
	</div>

    </body>

</html>
