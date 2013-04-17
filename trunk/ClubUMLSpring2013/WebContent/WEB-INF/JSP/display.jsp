<%--
    Document   : display
    Created on : Oct 20, 2012, 2:25:33 PM
    Author     : pratham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/display.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Project Diagrams</title>
<link href="css/display.css" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var type = "";
	$(document).ready(function() {
		$("#downloadButton").click(function() {
			type = this.id.toString();
		});
		$("#compareButton").click(function() {
			type = this.id.toString();
		});
		$("#displayButton").click(function() {
			type = this.id.toString();
		});
		// Merge function
		$("#mergeButton").click(function() {
			type = this.id.toString();
		});
		// end
	});
	function checkFields() {
		if (type == "downloadButton") {
			if ($(".myCheckBox:checked").length == 1) {
				return true;
			}
			alert("Please select 1 diagram for download");
			return false;
		}
		if (type == "compareButton") {
			if ($(".myCheckBox:checked").length == 2) {
				return true;
			}
			alert("Please select 2 diagrams to compare");
			return false;
		}
		if (type == "displayButton") {
			if ($(".myCheckBox:checked").length == 1) {
				return true;
			}
			alert("Please select 1 diagram to display");
			return false;
		}
		// merge function
		if (type == "mergeButton") {
			var req = document.getElementById("req");
			var form = document.getElementById("requestForm");
			var check = document.getElementsByName("check");
			var checked = [];
			for(var i=0; i<check.length; i++) {
				if(check[i].checked) {
					checked.push(check[i]);
				}
			}
			if (checked.length == 2) {
				var reqO = {};
				reqO.Request = "Refresh";
				reqO.Diagram1 = checked[0].value;
				reqO.Diagram2 = checked[1].value;
				req.value = JSON.stringify(reqO);
				
				// DEBUG - show diagram IDs
				//alert(checked[0].value + " " + checked[1].value);
				
				form.submit();
				return false;
			}
			alert("Please select 2 diagram to merge");
			return false;
		}
		// end
	}
	
	function displayClassDiagramFields(element) {
		var option = element.options[element.selectedIndex].text;
		
		var selectLabel = document.getElementById("DiagramSelectLabel");
		var fileinput2 = document.getElementById("file2");
		var fileinput3 = document.getElementById("file3");
		if (option == "ECORE") {
			fileinput2.style.display = "none";
			fileinput3.style.display = "none";
			selectLabel.innerHTML = "Class Diagram Format: (.ecore)";
		} else if (option == "XMI") {
			fileinput2.style.display = "block";
			fileinput3.style.display = "block";
			selectLabel.innerHTML = "Class Diagram Format: (.di, .notation, .uml)";
		}
	}
	
	function toggleChecked(status) {
		$(".myCheckBox").each( function() {
			$(this).attr("checked",status);
		})
	}

</script>
</head>
<body style="background-color: #F3F3F3">
	<div id="myHeader">
		<h1 id="banner">Class Diagrams</h1>
	</div>
	
	<div id="mainContainer">
	
	<div id="diagramBox">
		<c:if test="${requestScope.diagramId1 != null}">
			<img src="${requestScope.firstPath}" width="100%" height="450px" />
		</c:if>
		<b>Comments : </b>
		<c:if test="${requestScope.comments != null}">
			<div id="commentBox">
				<table id="myTable">
					<c:forEach items="${requestScope.comments}" var="comment">
						<tr>
							<td><b>${comment.getUserName()}</b></td>
							<td>${comment.getContent()}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
    
    <div id="rightContainer">
	
		<div id="box">
			<span id="DiagramSelectLabel">Class Diagram Format: (.ecore)</span> 
			<select onchange="displayClassDiagramFields(this)">
					<option value="ecore">ECORE</option>
					<option value="xmi">XMI</option>
			</select> 
			<form action="UploadServlet" method="post" enctype="multipart/form-data">		
					<input id="file1" type="file" name="file" size="50" />
					<input id="file2" type="file" name="file2" size="50" style="display:none"/>
					<input id="file3" type="file" name="file3" size="50" style="display:none"/>
					<input class="submit" type="submit" value="upload">
			</form>
		</div>
	
		<div id="box2">
			<form action="DisplayDiagram" method="post">
				<input type="submit" id="downloadProjectButton" value="DownloadProject"
					name="submit" />
			</form>
		</div>
		
		<div id="list">
			<form action="DisplayDiagram" method="post"
				onsubmit="return checkFields()">
				<input type="submit" id="displayButton" value="Display" name="submit" />
				<input type="submit" id="downloadButton" value="Download" name="submit" />
				<input type="submit" id="compareButton" value="Go to compare" name="submit" <c:if test="${type=='sequence'}">disabled</c:if> />
				<!-- Merge -->
				<input type="submit" id="mergeButton" value="Go To Merge" name="submit" <c:if test="${type=='sequence'}">disabled</c:if> />
				<!-- End Merge -->
				<table>
					<tr>
						<td>
						<input type="checkbox" name="checkall"
								value="10" onclick="toggleChecked(this.checked)">
						</td>
						<td><b>Image</b></td>
						<td><b>Edited</b></td>
						<td><b>Type</b></td>
					</tr>
					<c:forEach items="${requestScope.diagrams}" var="diagram">
						<tr>
							<td><input class="myCheckBox" type="checkbox" name="check"
								value="${diagram.diagramId}" /></td>
							<td>${diagram.diagramName}</td>
							<td>${diagram.createdTime}</td>
							<td>${diagram.diagramType}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</div>
	</div>
	
	</div>
	
	<!-- Merge Form -->
	<form id=requestForm action="ClassMergeComunicator" method=POST style="display: none;" onsubmit="return checkFields()">
		<input name=request id=req value="" />
	</form>
	<!-- End Merges -->
</body>
</html>
