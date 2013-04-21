<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Manage Relationship</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="merge.css">
</head>
<body>
	<table>
		<tr><th colspan=2>Diagram1 Relationships</th><th colspan=2>Diagram2 Relationships</th></tr>
		<tr><td>Generalization</td><td>Association</td><td>Generalization</td><td>Association</td></tr>
		<tr><td>
			<c:forEach items="${response.Diagram1.Generalizations }" var="entry" >
				<label><input type=checkbox name="d1g" value="${entry.id }" />${entry.text }</label>
				<br>
			</c:forEach>
		</td><td>
			<c:forEach items="${response.Diagram1.Associations }" var="entry" >
				<label><input type=checkbox name="d1a" value="${entry.id }" />${entry.text }</label>
				<br>
			</c:forEach>
		</td><td>
			<c:forEach items="${response.Diagram2.Generalizations }" var="entry" >
				<label><input type=checkbox name="d2g" value="${entry.id }" />${entry.text }</label>
				<br>
			</c:forEach>
		</td><td>
			<c:forEach items="${response.Diagram2.Associations }" var="entry" >
				<label><input type=checkbox name="d2a" value="${entry.id }" />${entry.text }</label>
				<br>
			</c:forEach>
		</td></tr>
		<tr><td colspan=4><button onclick="commit()" >Commit</button></td></tr>
	</table>
	<form id="requestForm" style="display: none;" action=ClassMergeComunicator method=POST>
		<input name="request" value="" />
	</form>
	<script>
		function commit() {
			var d1g = document.getElementsByName("d1g");
			var d1a = document.getElementsByName("d1a");
			var d2g = document.getElementsByName("d2g");
			var d2a = document.getElementsByName("d2a");
			var requestForm = document.getElementById("requestForm");
			var req = document.getElementsByName("request")[0];
			
			var reqO = {};
			reqO.Request = "Done";
			reqO.Diagram1 = {};
			reqO.Diagram1.Generalizations = [];
			reqO.Diagram1.Associations = [];
			reqO.Diagram2 = {};
			reqO.Diagram2.Generalizations = [];
			reqO.Diagram2.Associations = [];
			
			for(var i=0; i<d1g.length; i++) {
				if(d1g[i].checked) 
					reqO.Diagram1.Generalizations.push(d1g[i].value);
			}
			for(var i=0; i<d1a.length; i++) {
				if(d1a[i].checked) 
					reqO.Diagram1.Associations.push(d1a[i].value);
			}
			for(var i=0; i<d2g.length; i++) {
				if(d2g[i].checked) 
					reqO.Diagram2.Generalizations.push(d2g[i].value);
			}
			for(var i=0; i<d2a.length; i++) {
				if(d2a[i].checked) 
					reqO.Diagram2.Associations.push(d2a[i].value);
			}
			req.value = JSON.stringify(reqO);
			requestForm.submit();
		}
	</script>
	
</body>
</html>