<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html style="background-color: #F3F3F3" xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<link rel="stylesheet" type="text/css" href="css/display.css"/>
	<link rel="stylesheet" type="text/css" href="style.css"/>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	
	<title>Compare Diagrams</title>
    
    <script type="text/javascript">
    $(document).ready(function(){
		
		// resize images proportionally
		$('img').each(function() {
			var maxWidth = 450;
	        var maxHeight = 450;
	        
	        var ratio = 0;  // Used for aspect ratio
	        var width = $(this).width();    // Current image width
	        var height = $(this).height();  // Current image height
			
	        /* Make height/width at least the max size to begin with */
	        // Check if the current width is smaller than max
	        if(width < maxWidth){
	            ratio = maxWidth / width;   // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        // Check if current height is smaller than min
	        if(height < maxHeight){
	            ratio = maxHeight / height; // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        
	        /* Then reduce height/width to fit the max */
	        // Check if the current width is larger than the max
	        if(width > maxWidth){
	            ratio = maxWidth / width;   // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        // Check if current height is larger than max
	        if(height > maxHeight){
	            ratio = maxHeight / height; // get ratio for scaling image
	            height = height * ratio;    // Reset height to match scaled image
	            width = width * ratio;    // Reset width to match scaled image
	        }
	        
	        // Set CSS
	        $(this).css("height", height);   // Set new height
	        $(this).css("width", width);    // Scale width based on ratio
		});
    });
	</script>
	
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
