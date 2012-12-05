<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="background-color: #F3F3F3" xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<title>compare page</title>
	<script type="text/javascript">
	    var comment = "";
	    $(document).ready(function(){

		var report = "reports/";
		var link = $("#reportLink").html();
		var reportLink = link.substring(link.lastIndexOf("/"))
		report = report + reportLink;
		window.open(report,'_blank','fullscreen=yes');
		$("#button1").click(function(){
		    comment = $.trim($("#comment1").val());
		});
		$("#button2").click(function(){
		    comment = $.trim($("#comment2").val());
		});
	    });
	    function checkComments(){
		if(comment == ""){
		    alert("Please enter comment before promoting");
		    return false;
		}
		return true;
	    }
	</script>
    </head>

    <body style="background-color: #F3F3F3">
	<div id="myHeader">
	    <h1 id="banner">Promote Diagram</h1>
	</div>
	<div id="myContainer">
	    <div class="file1" style="float:left;">
		<img  src="${requestScope.path1}"/>
		<form action="Promote" method="POST" onsubmit="return checkComments()">
		    Comment : <input type="text" id="comment1" name="comment"/><br/>
		    <input type="hidden" name="imageId" value="${requestScope.val1}"/><br/>
		    <input type="submit" id="button1" value="Promote"/>
		</form>
	    </div>
	    <div id="reportLink" style="display: none">${requestScope.reportPath}</div>
	    <div class="file2">
		<img src="${requestScope.path2}" />
		<form action="Promote" method="POST"  onsubmit="return checkComments()">
		    Comment : <input type="text" id="comment2" name="comment"/><br/>
		    <input type="hidden" name="imageId" value="${requestScope.val2}"/><br/>
		    <input type="submit" id="button2" value="Promote"/>
		</form>
	    </div>
	</div>
    </body>

</html>
