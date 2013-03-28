<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/compare.css"/>
<title>compare page</title>
</head>
    
<body>
    
        <script>
            function validateForm1()
            {
            var x=document.forms["promote1"]["comment"].value;
            if (x==null || x=="")
              {
              alert("Comment must be filled out");
              return false;
              }
            }
            
            function validateForm2()
            {
            var x=document.forms["promote2"]["comment"].value;
            if (x==null || x=="")
              {
              alert("Comment must be filled out");
              return false;
              }
            }
    </script>

    <div class="file1" >
        <img  src="${requestScope.path1}" width="100%" height="80%"/>
        
        <form name="promote1" action="PromoteServlet" method="post" onsubmit="return validateForm1()">            
            <input type="hidden" name="diagramId" value="${requestScope.diagramId1}"/>
            <input type="textfield" name="comment" style="width: 300px"/>
            <input  name="submit" type="submit" value="promote" />  
        </form>

    </div>
            
    <div class="compare">
        <form action="CompareServlet" method="post" >
            <input type="hidden" name="file1" value="${requestScope.diagramId1}"/>
            <input type="hidden" name="file2" value="${requestScope.diagramId2}"/>
            <input  name="submit" type="submit" value="Compare" />  
        </form>
    </div>

        
    <div class="file2">
        <img src="${requestScope.path2}" width="100%" height="80%"/>
             
        <form name="promote2" action="PromoteServlet" method="post" onsubmit="return validateForm2()">         
            <input type="hidden" name="diagramId" value="${requestScope.diagramId2}"/>
            <input type="text" name="comment" style="width: 300px"/>
            <input class="promote" name="submit" type="submit" value="promote" />  
        </form>
            
    </div>

</body>

</html>
