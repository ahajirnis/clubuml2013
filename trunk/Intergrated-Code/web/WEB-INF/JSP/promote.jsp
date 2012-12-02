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
	    <form action="Promote" method="POST">
		Comment : <input type="text" name="comment"/><br/>
		<input type="hidden" name="imageId" value="${requestScope.val1}"/><br/>
		<input type="submit" value="Promote"/>
	    </form>
	</div>
	<div class="file2" style="float:left;">
	    <img src="${requestScope.path2}" />
	    <form action="Promote" method="POST">
		Comment : <input type="text" name="comment"/><br/>
		<input type="hidden" name="imageId" value="${requestScope.val2}"/><br/>
		<input type="submit" value="Promote"/>
	    </form>
	</div>

    </body>

</html>
