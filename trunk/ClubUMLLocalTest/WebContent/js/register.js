
// Submit button is enabled when user name, password and email fields are valid
var validUserName = false;
var validPassword = false;
var validEmail = false;

/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function validate() {
	if (document.forms.register.username.value == "") {
		alert("User Name can not be empty!");
		return false;
	} else if (document.forms.register.password.value == "") {
		alert("Password can not be empty!");
		return false;
	} else if (document.forms.register.password.value != document.forms.register.password2.value) {
		alert("Password does not match!");

		return false;
	} else if (document.forms.register.email.value == "") {
		alert("Email can not be empty!");
		return false;
	}
}

function checkUsername(value) {

	var block = document.getElementById("username");
	
	ajax = createAjax();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			
			var response = ajax.responseText;
			
			var patt1 = new RegExp("green");

			validUserName = patt1.test(response);
			
			block.innerHTML = ajax.responseText;
		}
	}
	ajax.open("get", "ValidateServlet?username=" + value + "", true);
	ajax.send(null);
}

function checkPassword() {
	var block2 = document.getElementById("password");
	var password1 = document.forms.registerForm.password.value;
	var password2 = document.forms.registerForm.password2.value;
	
	if (password1 == "" || password2 == "") {
		block2.innerHTML = "";
		validPassword = false;
	} else if (password1 != password2) {
		block2.innerHTML = "<font color = 'red'>Password does not match";
		validPassword = false;
	} else {
		block2.innerHTML = "<font color = 'green'>Password ok";
		validPassword = true;
	}
}

function checkEmail(email) {
	var block = document.getElementById("email");
	var patt1 = new RegExp(".+@.+\.[com|net|org|biz|gov]$");

	validEmail = patt1.test(email);
	
	if (email == "") {
		block.innerHTML = "";
	} else if (validEmail) {
		block.innerHTML = "<font color = 'green'>Email ok";
	} else {
		block.innerHTML = "<font color = 'red'> Invalid email";
	}
}

function createAjax() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}

/**
 * checks if user name, password and email fields are valid to enable submit button
 * else submit button is disabled
 */
function ProcessSubmitState() {
	if (validUserName && validPassword && validEmail) {
		document.getElementById('SubmitButton').disabled = "";
	} else {
		document.getElementById('SubmitButton').disabled = "true";
	}
		
}

function checkPromote() {
	if (document.forms.promote1.comment.value == ""
			|| document.forms.promote1.comment.value == null) {
		alert("Comment cannot be empty!");
		return false;
	}

}
