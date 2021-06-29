<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%
	String utente_non_trovato = (String) request.getAttribute("utente_non_trovato");
%>

<!DOCTYPE html> 
<html>
	<head>
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link rel="stylesheet" href="styles/formStyle.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<title>Accedi</title>
	</head>

	<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
		<div class="wrapper fadeInDown">
		  <div id="formContent">
			 <!-- Tabs Titles -->
			<h2 class="active" style="cursor:default;"> ACCEDI </h2>
      		
      		<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/userIconBlue.gif" id="icon" alt="User Icon" />
      		</div>
		
			<form action="User" method="post">
			
				<input type="hidden" name="action" value="login" required/>
				
				<input type="text" id="login" class="fadeIn second" name="email" placeholder="email" onChange="validateEmail(this), controlReturnEmail(validateEmail(this)), requiredEmail(this), controlReturnEmail(requiredEmail(this))">
				<div class="alert-danger" role="alert" id="alertMail"> </div><br>
        		
        		<input type="password" id="password" class="fadeIn third" name="password" placeholder="password" onChange="requiredPassword(this), controlReturnPassword(requiredPassword(this))">
      			<div class="alert-danger" role="alert" id="alertPassword"> </div><br>
				<%
					if(utente_non_trovato != null && utente_non_trovato.equals("true")){
				%>
				
				<div class="alert-danger" role="alert">
				  *Utente inesistente
				</div><br>
				<%
					}
				%>	
				<input type="submit" id="submit" class="fadeIn fourth" value="Log In">
				<input type="reset" class="fadeIn fourth" value="Annulla">
				<!-- Remind Passowrd -->
      			<div id="formFooter">
        			<a class="underlineHover" href="Signup.jsp">Registrati</a>
      			</div>	
			</form>
		</div>
	   </div>
	   <!-- ******************************************SCRIPTS********************************************************* -->
	 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	 	<script src="scripts/validationForm.js"> window.onload=required; </script> 
	 	
	 	  
	   <!-- ************************************************************************************************** -->
	</body>
</html>
