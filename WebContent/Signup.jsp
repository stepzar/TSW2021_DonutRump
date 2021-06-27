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
	
	<body>
	
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
				<input type="text" class="fadeIn second" name="nome" placeholder="Nome" required/>
	 			<input type="text" class="fadeIn second" name="cognome" placeholder="Cognome" required/>
	 			<input type="tel" class="fadeIn second" name="telefono" placeholder="Telefono" pattern="[0-9]{10}" required/>
				<input type="email" class="fadeIn second" name="email" placeholder="E-mail" required/>
				<input type="password" class="fadeIn second" name="password" placeholder="Password" required/>
				<input type="submit" id="register" class="fadeIn fourth"  value="Registrati">
				<input type="reset" class="fadeIn fourth" value="Annulla">
				<div id="formFooter">
        			<a class="underlineHover" href="Login.jsp">Già utente? Accedi</a>
      			</div>	
			</form>
		</div>
	   </div>
	</body>
</html>

<!-- ********************************************************************************************** -->

	<!--  <body>
		<div class="wrapper fadeInDown">
		  <div id="formContent">
			 <!-- Tabs Titles -->
	<!-- 		<h2 class="active" style="cursor:default;"> ACCEDI </h2>
      		
      		<!-- Icon -->
      <!--		<div class="fadeIn first">
        		<img src="images/userIconBlue.gif" id="icon" alt="User Icon" />
      		</div>
		
			<form action="User" method="post">
			
				<input type="hidden" name="action" value="login"/>
				<input type="email" id="login" class="fadeIn second" name="email" placeholder="email">
        		<input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
      
				<%
					//if(utente_non_trovato != null && utente_non_trovato.equals("true")){
				%>
				
				<div class="alert-danger" role="alert">
				  *Password o Email non corretti
				</div><br>
				<%
					//}
				%>	
				<input type="submit" class="fadeIn fourth" value="Log In">
				<input type="reset" class="fadeIn fourth" value="Annulla">
				<!-- Remind Passowrd -->
      <!--			<div id="formFooter">
        			<a class="underlineHover" href="Signup.jsp">Registrati</a>
      			</div>	
			</form>
		</div>
	   </div>
	</body>
	-->
