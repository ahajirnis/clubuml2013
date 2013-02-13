// valid user flag. Used by Ajax call.
var validUser = false;

/**
 * Validates all required fields.
 * 
 * @returns true if all fields are valid, else false
 */
function validateForm() {

	var username = document.forms.registerForm.username.value;
	var password = document.forms.registerForm.password.value;
	var password2 = document.forms.registerForm.password2.value;
	var email = document.forms.registerForm.email.value;

	if (!validUser) {
		alert("Require valid user!");
		return false;
	} else if (password == "") {
		alert("Password can not be empty!");
		return false;
	} else if (document.forms.registerForm.password.value != document.forms.registerForm.password2.value) {
		alert("Password does not match!");
		return false;
	} else if (!validateEmail(email)) {
		alert("Require valid email!");
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
			var patt1 = new RegExp("ok");
			var response = ajax.responseText;

			block.innerHTML = response;

			// Set flag for validUser
			if (patt1.test(response)) {
				validUser = true;
			} else {
				validUser = false;
			}
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
 * Displays message for valid and invalid email.
 * 
 * @param email to verify
 */
function checkEmail(email) {
	var block = document.getElementById("email");
	var emailFlag = validateEmail(email);

	if (email == "") {
		block.innerHTML = "";
	} else if (emailFlag) {
		block.innerHTML = "<font color = 'green'>Email ok";
	} else {
		block.innerHTML = "<font color = 'red'> Invalid email";
	}
}

/**
 * Validates if email is in correct format
 * 
 * @param email to verify
 * @return true if valid email, else false
 */
function validateEmail(email) {
	var patt1 = new RegExp(".+@.+\.[com|net|org|biz|gov|edu]$");

	if (email == "") {
		return false;
	} else if (patt1.test(email)) {
		return true;
	} else {
		return false;
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
 * 
 * @returns
 */
function checkPromote() {
	if (document.forms.promote1.comment.value == ""
			|| document.forms.promote1.comment.value == null) {
		alert("Comment cannot be empty!");
		return false;
	}
}
