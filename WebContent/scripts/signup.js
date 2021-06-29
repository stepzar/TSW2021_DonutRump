/**
 *   VALIDATION FORM JAVASCRIPT PER IL LOGIN
 */


$('document').ready(function required (){
						document.getElementById("login").required=true;
						document.getElementById("password").required=true;
					}
);

//********************************************************E-MAIL******************************************************************************* */

//senza espr. regolari
function ValidationEmail (email) {
	var x = email.value; // passaggio per riferimento, qundi prendo il valore
	var pos_chiocciola = x.indexOf("@");
	var pos_punto = x.lastIndexOf(".");
	
	if (pos_chiocciola<1 || pos_punto<1 || pos_punto<pos_chiocciiola+2 || pos_punto+2>=x.lengt || pos_chiocciola==undefined ||pos_chiocciola==null ||
	    pos_punto==undefined ||pos_punto==null) {
		
		document.getElementById("alertMail").innerHTML="*Formato e-mail errato (es: mario@peluso.it)"; 
		// JQUERY SAME CODE -->   $(#alertMail).html("*Formato e-mail errato (es: mario@peluso.it)");
		email.focus(); 
		return false; 
	}	
	else {
		document.getElementById("alertMail").innerHTML="*Formato corretto!";
		email.focus();
		return true
	} 
}


// controllo e-mail con espr. regolari
function validateEmail(uemail){
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(uemail.value.match(mailformat)){
		return true;
	}
	else {
		document.getElementById("alertMail").style.color="rgb(255, 81, 0)";
		document.getElementById("alertMail").innerHTML="*Formato e-mail errato (es: marioRossi@gmail.com)";
		uemail.focus();
		return false;
	}
}


// controllo del valore ritornato da ValidationEmail (email), altrimenti lo script viene eseguito una sola volta
function controlReturnEmail (returnEmail) {
	
	if (returnEmail){
		document.getElementById("alertMail").style.color="green";
		document.getElementById("alertMail").innerHTML="✔";
	}
	
}


//********************************************************PASSWORD******************************************************************************* */

//la password deve solo non essere nulla 
function controlReturnPassword (returnPassword) {
	
	if (returnPassword){
		document.getElementById("alertPassword").style.color="green";
		document.getElementById("alertPassword").innerHTML="✔";
	}
	else {
		document.getElementById("alertPassword").style.color="rgb(255, 81, 0)";
	}
	
}

//********************************************************REQUIRED_LOGIN******************************************************************************* */

function requiredEmail (text) {
	
	var email = text.value; 
	
	if (email==null || email==undefined || email==""){
		submit.disabled;
		document.getElementById("alertMail").innerHTML="*Campo obbligatorio!";
		email.focus();
		return false;
	}
	
}

function requiredPassword (text) {
	
	var pass = text.value; 
		
		if (pass==null || pass==undefined || pass==""){
			submit.disabled;
			document.getElementById("alertPassword").style.color="rgb(255, 81, 0)";
			document.getElementById("alertPassword").innerHTML="*Campo obbligatorio!";
			pass.focus();
			return false;
		}
		else return true; 
}




//*************************************************************NAME**************************************************************************** */


function validationName(text) {
	var name = /^[A-Za-z]+$/; 
	if(text.value.match(name)){
		document.getElementById("alertName").style.color="green";
		document.getElementById("alertName").innerHTML="✔";
		return true;
	}
	else {
		document.getElementById("alertName").style.color="rgb(255, 81, 0)";
		document.getElementById("alertName").innerHTML="*Nome errato";
		uemail.focus();
		return false;
	}
	
}

function validationSurname (text) {
	var name = /^[A-Za-z]+$/; 
	if(text.value.match(name)){
		document.getElementById("alertSurname").style.color="green";
		document.getElementById("alertSurname").innerHTML="✔";
		return true;
	}
	else {
		document.getElementById("alertSurname").style.color="rgb(255, 81, 0)";
		document.getElementById("alertSurname").innerHTML="*Cognome errato";
		uemail.focus();
		return false;
	}
	
}

function validationNumber (text) {
	var name = /^([0-9]{3}-[0-9]{7})$/; 
	if(text.value.match(name)){
		document.getElementById("alertNumber").style.color="green";
		document.getElementById("alertNumber").innerHTML="✔";
		return true;
	}
	else {
		document.getElementById("alertNumber").style.color="green";
		document.getElementById("alertNumber").innerHTML="✔";
		
		//document.getElementById("alertNumber").style.color="rgb(255, 81, 0)";
		//document.getElementById("alertNumber").innerHTML="*Numero errato";
		text.focus();
		return false;
	}
	
}












