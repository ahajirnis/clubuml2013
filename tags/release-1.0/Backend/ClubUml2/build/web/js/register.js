/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function validate(){
    if(document.forms.register.username.value=="")
	{
            alert("User Name can not be empty!");
            return false;  
	}
	else if(document.forms.register.password.value=="")
	{	
            alert("Password can not be empty!");
            return false;
	}
        else if(document.forms.register.password.value!=document.forms.register.password2.value)
        {
           alert("Password does not match!");
           
	   return false;     
        }
	else if(document.forms.register.email.value=="")
	{
            alert("Email can not be empty!");
            return false;
        }	
    
}

function checkUsername(value){
    ajax = createAjax();
    ajax.onreadystatechange=function()
    {
	if(ajax.readyState==4&&ajax.status==200)
	{
            var block = document.getElementById("username");
            block.innerHTML = ajax.responseText;
        }
    }
        ajax.open("get", "ValidateServlet?username=" + value +"", true);
        ajax.send(null);
}

function checkPassword()
{
     var block2 = document.getElementById("password");
     if(document.forms.register.password.value!=document.forms.register.password2.value)
     {
         
         block2.innerHTML = "<font color = 'red'>Password does not match";
     }
     else{
         block2.innerHTML = "<font color = 'green'>Password ok";
     }
         
   
}

function checkEmail()
{
    var x=document.forms["register"]["email"].value;
    var atpos=x.indexOf("@");
    var dotpos=x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
    {
        alert("Not a valid e-mail address");
        return false;
    }
}

function createAjax()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	return xmlhttp;
}

function checkPromote(){
        if(document.forms.promote1.comment.value==""
    ||document.forms.promote1.comment.value==null)
	{
            alert("Comment cannot be empty!");
            return false;  
	}
    
}


