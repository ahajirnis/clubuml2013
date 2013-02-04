/**
 * Validates all required fields.
 * 
 * @returns true if all fields are valid, else false
 */
function validateForm() {
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
	
	return true;
}

/**
 * Check if user name already exist in user table.
 * 
 * @param value is the user name to check
 */
function checkUsername(value) {

	var block = document.getElementById("username");

	ajax = createAjax();
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4 && ajax.status == 200) {
			block.innerHTML = ajax.responseText;
		}
	};
	ajax.open("get", "ValidateServlet?username=" + value + "", true);
	ajax.send(null);
}

/**
 * Validate passwords match.
 * 
 * Displays message for valid and invalid password.
 */
function checkPassword() {
	var block2 = document.getElementById("password");
	var password1 = document.forms.registerForm.password.value;
	var password2 = document.forms.registerForm.password2.value;

	if (password1 == "" || password2 == "") {
		block2.innerHTML = "";
	} else if (password1 != password2) {
		block2.innerHTML = "<font color = 'red'>Password does not match";
	} else {
		block2.innerHTML = "<font color = 'green'>Password ok";
	}
}

/**
 * Validate email format.
 * 
 * Displays message for valid and invalid email.
 * 
 * @param email to verify
 */
function checkEmail(email) {
	var block = document.getElementById("email");
	var patt1 = new RegExp(".+@.+\.[com|net|org|biz|gov|edu]$");

	if (email == "") {
		block.innerHTML = "";
	} else if (patt1.test(email)) {
		block.innerHTML = "<font color = 'green'>Email ok";
	} else {
		block.innerHTML = "<font color = 'red'> Invalid email";
	}
}

/**
 * Creates Ajax object
 * 
 * @returns Ajax object
 */
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
 * (Have not seen used. Candidate for removal)
 * @returns
 */
function checkPromote() {
	if (document.forms.promote1.comment.value == ""
			|| document.forms.promote1.comment.value == null) {
		alert("Comment cannot be empty!");
		return false;
	}
}
