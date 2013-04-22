<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="merge.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Merge Class</title>
<script>
	function save() {
		var sc1 = document.getElementsByName("sc1")[0];
		var sc2 = document.getElementsByName("sc2")[0];
		var scac1 = document.getElementsByName("scac1");
		var scmc1 = document.getElementsByName("scmc1");
		var scac2 = document.getElementsByName("scac2");
		var scmc2 = document.getElementsByName("scmc2");
		var scas = document.getElementsByName("scas");
		var scms = document.getElementsByName("scms");
		var requestForm = document.getElementById("requestForm");
		var request = document.getElementsByName("request")[0];
		var newName = document.getElementsByName("newClassName")[0];
		// create message JSON Object
		var req = {};
		req.Request = "Consolidate";
		
		// set Something from Class1
		req.Class1 = {};
		req.Class1.Class = sc1.value;
		req.Class1.Attributes = [];
		for(var i=0; i<scac1.length; i++) {
			if(scac1[i].checked) {
				req.Class1.Attributes.push(scac1[i].value);
			}
		}
		for(var i=0; i<scas.length; i++) {
			if(scas[i].checked) {
				req.Class1.Attributes.push(scas[i].value);
			}
		}
		
		req.Class1.Operations = [];
		for(var i=0; i<scmc1.length; i++) {
			if(scmc1[i].checked) {
				req.Class1.Operations.push(scmc1[i].value);
			}
		}
		for(var i=0; i<scms.length; i++) {
			if(scms[i].checked) {
				req.Class1.Operations.push(scms[i].value);
			}
		}
		
		// set Something from Class2
		req.Class2 = {};
		req.Class2.Class = sc2.value;
		req.Class2.Attributes = [];
		for(var i=0; i<scac2.length; i++) {
			if(scac2[i].checked) {
				req.Class2.Attributes.push(scac2[i].value);
			}
		}
		req.Class2.Operations = [];
		for(var i=0; i<scmc2.length; i++) {
			if(scmc2[i].checked) {
				req.Class2.Operations.push(scmc2[i].value);
			}
		}
		


		// add new name to req
		req.Name = newName.value;
		// file this JSON String to message and submit the form
		//return req;
		//console.log(req);
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

<table>
	<tr><td>
		<input Type=Hidden name=sc1 value="${response.Class1 }" />
	</td><td>
		<input Type=Hidden name=sc2 value="${response.Class2 }" />
	</td></tr>
	<tr><th colspan=2 >
		Define New Name
	</th></tr>
	<tr><td colspan=2>
		<input type=text value="${response.Class1 }_${response.Class2 }" name="newClassName" onfocus="this.select();" />
	</td></tr>
	<tr><td>
		<button onclick=getName(this) >${response.Class1 }</button>
	</td><td>
		<button onclick=getName(this) >${response.Class2 }</button>
	</td></tr>
	
</table>
<table>
	<tr><th colspan=3 >Attributes</th></tr>
	<tr><td valign="top"><strong>Left Only<br></strong>
	<c:forEach items="${response.Attributes.Class1 }" var="entry" >
		<label><input type=checkbox name="scac1" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	<td valign="top"><strong>Same</strong><br>
	<c:forEach items="${response.Attributes.same }" var="entry" >
		<label><input type=checkbox name="scas" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td>
	<td valign="top"><strong>Right Only</strong><br>
	<c:forEach items="${response.Attributes.Class2 }" var="entry" >
		<label><input type=checkbox name="scac2" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td></tr>
	
	<!-- Similar Attribute names -->
	<tr>
	<td colspan=3><strong>Similar</strong><br>
	<c:forEach items="${response.Attributes.similar }" var="entry" >
		${entry }
		<br>
	</c:forEach>
	</td>
	</tr>
	
	<tr><th colspan=3 >Methods</th></tr>
	<tr><td valign="top"><strong>Left Only</strong><br>
	<c:forEach items="${response.Operations.Class1 }" var="entry" >
		<label><input type=checkbox name="scmc1" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td>
	<td valign="top"><strong>Same</strong><br>
	<c:forEach items="${response.Operations.same }" var="entry" >
		<label><input type=checkbox name="scms" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td>
	<td valign="top"><strong>Right Only</strong><br>
	<c:forEach items="${response.Operations.Class2 }" var="entry" >
		<label><input type=checkbox name="scmc2" value="${entry }" />${entry }</label>
		<br>
	</c:forEach>
	</td></tr>
	
	<!-- Similar Operation names -->
	<tr>
	<td colspan=3><strong>Similar</strong><br>
	<c:forEach items="${response.Operations.similar }" var="entry" >
		${entry }
		<br>
	</c:forEach>
	</td>
	</tr>
	
</table>
<table style="border: 0">
	<tr><td id=errorMsg class=hidden ></td></tr>
	<tr><td><button onclick=save() >Save</button></td></tr>
</table>
<form id=requestForm class=hidden action="ClassMergeComunicator" method=post >
	<input name=request type=text value="" />
</form>
</body>
</html>