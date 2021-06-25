<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%
	String utente_non_trovato = (String) request.getAttribute("utente_non_trovato");
%>

<!DOCTYPE html> 
<html>
	<head>
		<link rel="stylesheet" href="styles/formStyle.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<title>Accedi</title>
	</head>

	<body>
		<div class="wrapper fadeInDown">
		  <div id="formContent">
			 <!-- Tabs Titles -->
			<h2 class="active" style="cursor:default;"> ACCEDI </h2>
      		
      		<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/userIconBlue.gif" id="icon" alt="User Icon" />
      		</div>
		
			<form action="User" method="post">
			
				<input type="hidden" name="action" value="login"/>
				<input type="email" id="login" class="fadeIn second" name="email" placeholder="email">
        		<input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      
				<%
					if(utente_non_trovato != null && utente_non_trovato.equals("true")){
				%>
				
				<div class="alert-danger" role="alert">
				  *Password o Email non corretti
				</div><br>
				<%
					}
				%>	
				<input type="submit" class="fadeIn fourth" value="Log In">
				<input type="reset" class="fadeIn fourth" value="Annulla">
				<!-- Remind Passowrd -->
      			<div id="formFooter">
        			<a class="underlineHover" href="Signup.jsp">Registrati</a>
      			</div>	
			</form>
		</div>
	   </div>
	</body>
</html>
