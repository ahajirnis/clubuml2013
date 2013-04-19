<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="merge.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Refine Class</title>
<script>
	function save() {
		var c1or2 = document.getElementById("c1or2");
		var sc = document.getElementsByName("sc")[0];
		var sca = document.getElementsByName("sca");
		var scm = document.getElementsByName("scm");

		var requestForm = document.getElementById("requestForm");
		var request = document.getElementsByName("request")[0];
		var newName = document.getElementsByName("newClassName")[0];
		// create message JSON Object
		var req = {};
		req.Request = "Consolidate";
		// set Something from Class1
		req[c1or2.value] = {};
		req[c1or2.value].Class = sc.value;
		req[c1or2.value].Attributes = [];
		for(var i=0; i<sca.length; i++) {
			if(sca[i].checked) {
				req[c1or2.value].Attributes.push(sca[i].value);
			}
		}
		req[c1or2.value].Operations = [];
		for(var i=0; i<scm.length; i++) {
			if(scm[i].checked) {
				req[c1or2.value].Operations.push(scm[i].value);
			}
		}
		
		// add new name to req
		req.Name = newName.value;
		// file this JSON String to message and submit the form
		//return req;
		request.value = JSON.stringify(req);
		requestForm.submit();
	}
	function getName(o) {
		var newClassName = document.getElementsByName("newClassName")[0];
		newClassName.value = o.innerHTML.toLowerCase();
		
	}
</script>
</head>
<body>

<!-- The data source name is response -->
<c:choose>
	<c:when test="${not empty response.Class1 }">
		<c:set var="cn" value="Class1" />
	</c:when>
	<c:when test="${not empty response.Class2 }">
		<c:set var="cn" value="Class2" />
	</c:when>
</c:choose>

<table>
	<tr><td>
		<input Type=Hidden id=c1or2 value="${cn }" />
		<input Type=Hidden name=sc value="${response[cn] }" />
	</td></tr>
	<tr><th>
		Define New Name
	</th></tr>
	<tr><td>
		<input type=text value="${response[cn] }" name="newClassName" onclick="this.select();" />
	</td><tr>
	<tr><th>Attributes</th></tr>
	<tr><td>
	<c:forEach items="${response.Attributes }" var="entry" >
		<label><input type=checkbox name="sca" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td></tr>
	<tr><th>Methods</th></tr>
	<tr><td>
	<c:forEach items="${response.Operations }" var="entry" >
		<label><input type=checkbox name="scm" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td></tr>
	<tr><td id=errorMsg class=hidden ></td></tr>
	<tr><td><button onclick=save() >Save</button></td></tr>
</table>
<form id=requestForm class=hidden action="ClassMergeComunicator" method=post >
	<input name=request type=text value="" />
</form>
</body>
</html>