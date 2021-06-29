<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
		<title>Registrati</title>
	</head>
	
	<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	
		<div class="wrapper fadeInDown">
		  <div id="formContent">
			 <!-- Tabs Titles -->
			<h2 class="active" style="cursor:default;"> REGISTRATI </h2>
	
			<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/userIconBlue.gif" id="icon" alt="User Icon" />
      		</div>

			<form action="User" method="post">
				<input type="hidden" name="action" value="signup" required/>
				<input type="text" class="fadeIn second" name="nome" placeholder="Nome" required onChange="validationName(this)"/>
				<div class="alert-danger" role="alert" id="alertName"> </div>
	 			<input type="text" class="fadeIn second" name="cognome" placeholder="Cognome" required onChange="validationSurname(this)"/>
	 			<div class="alert-danger" role="alert" id="alertSurname"> </div>
	 			<input type="tel" class="fadeIn second" name="telefono" placeholder="Telefono" pattern="[0-9]{10}" required onChange="validationNumber(this)"/>
				<div class="alert-danger" role="alert" id="alertNumber"> </div>
				<input type="text" id="login" class="fadeIn second" name="email" placeholder="email" onChange="validateEmail(this), controlReturnEmail(validateEmail(this)), requiredEmail(this), controlReturnEmail(requiredEmail(this))">
				<div class="alert-danger" role="alert" id="alertMail"> </div>
				
				<input type="password" id="password" class="fadeIn third" name="password" placeholder="password" onChange="requiredPassword(this), controlReturnPassword2(requiredPassword(this))">
      			<div class="alert-danger" role="alert" id="alertPassword"> </div><br>
				
				<input type="submit" id="register" class="fadeIn fourth"  value="Registrati">
				<input type="reset" class="fadeIn fourth" value="Annulla">
				<div id="formFooter">
        			<a class="underlineHover" href="Login.jsp">Già utente? Accedi</a>
      			</div>	
			</form>
		</div>
	   </div>
	   
	   	   <!-- ******************************************SCRIPTS********************************************************* -->
	 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	 	<script src="scripts/signup.js"> window.onload=required; </script> 
	 	
	 	  
	   <!-- ************************************************************************************************** -->
	</body>
</html>

