<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="merge.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Select Class</title>
<script>
function sendRequest(mo) {
	var scd1co = document.getElementsByName("scd1co");
	var scd2co = document.getElementsByName("scd2co");
	var samec = document.getElementsByName("samec");
	var request = document.getElementsByName("request")[0];
	var requestForm = document.getElementById("requestForm");
	var errorMsg = document.getElementById("errorMsg");
	
	var func = mo.value; // used to identify each request
	var sc1Count = 0; 
	var sc2Count = 0;
	var sameCount = 0;
	
	var req = {};
	for(var i=0; i<scd1co.length; i++) {
		if(scd1co[i].checked) {
			req.Class1 = scd1co[i].value;
			sc1Count ++;
		}
	}
	for(var i=0; i<scd2co.length; i++) {
		if(scd2co[i].checked) {
			req.Class2 = scd2co[i].value;
			sc2Count ++;
		}
	}
	for(var i=0; i<samec.length; i++) {
		if(samec[i].checked) {
			req.Same = samec[i].value;
			sameCount ++;
		}
	}
	if(func == "break")
		if(sameCount == 1 && sc1Count == 0 && sc2Count == 0) {
			req.Request = "Break";
			request.value = JSON.stringify(req);
			requestForm.submit();
		} else {
			errorMsg.innerHTML = "Only One Same Class Can Be Broken One Time!";
		}
	if(func == "merge")
		if(sc1Count == 1 && sc2Count == 1) {
			req.Request = "Compare";
			request.value = JSON.stringify(req);
			requestForm.submit();
		} else {
			errorMsg.innerHTML = "Merge Function Failed!"
		}
	if(func == "add") 
		if(sameCount + sc1Count + sc2Count == 1 && sameCount == 0) {
			req.Request = "Add";
			request.value = JSON.stringify(req);
			requestForm.submit();
		} else {
			errorMsg.innerHTML = "Add Function Failed!"
		}
	if(func == "next") 
		if(sameCount + sc1Count + sc2Count == 0) {
			req.Request = "Next";
			request.value = JSON.stringify(req);
			requestForm.submit();
		} else {
			errorMsg.innerHTML = "No Class Can Be Selected When Choose Next!";
		}
	
}
	
</script>
</head>
<body>
	<!-- The data source name is "response" -->
	<form>

	<table>
		<tr><th>${response.Diagram1 }</th><th>${response.Diagram2}</th></tr>
		<tr style="text-align: center; " >
			<td><img src="${DiagramPath1 }" alt="response1Pic" /></td>
			<td><img src="${DiagramPath2 }" alt="response2Pic" /></td>
		</tr>
	</table>

	
		<!-- 
		<tr>
			<th>${response.Diagram1 } ONLY</th>
			<th>COMMON</th>
			<th>${response.Diagram2 } ONLY</th>
		</tr>
		 -->
	<table>
		<tr>
		<td>Classes unique to left diagram:</td>
		<td>Classes in common:</td>
		<td>Classes unique to right diagram:</td>
		</tr>
		<tr>
			<td>
				<c:forEach items="${response.Diagram1 }" var="entry" >
					<label><input type=checkbox name="scd1co" value="${entry }" />${entry }</label>
				 	<br>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${response.Same }" var="entry" >
					<label><input type=checkbox name="samec" value="${entry }" />${entry }</label>
				 	<br>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${response.Diagram2 }" var="entry" >
					<label><input type=checkbox name="scd2co" value="${entry }" />${entry }</label>
				 	<br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan=3>Similarly named classes:<br>
				<c:forEach items="${response.Similar }" var="entry" >
				 	${entry }<br>
				</c:forEach>
			</td>
		</tr>
	</table>
	<table>
		<tr><td id=errorMsg ></td></tr>
		</table>
	</form>
	<form id=requestForm style="display: none;" action="ClassMergeComunicator" method=post>
		<input name=request type=text value="" readonly onclick=sendRequest() />
	</form>
	<table>
		<tr><td>
			<button id=breakButton value="break" onclick="sendRequest(this)">BREAK</button>
			<button id=megerButton value="merge" onclick="sendRequest(this)">MERGE</button>
			<button id=addButton value="add" onclick="sendRequest(this)">ADD</button>
			<button id=nextButton value="next" onclick="sendRequest(this)">NEXT</button>
		</td></tr>
	</table>

</body>
</html>