<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%
	String utente_non_trovato = (String) request.getAttribute("utente_non_trovato");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"%>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Accedi</title>
	</head>

	<body>
		<br>
		<div style="width:350px; margin: 0 auto; text-align: center;">
			<h1 style="font-weight: 200;">Accedi</h1><br>
		
			<form action="User" method="post">
			
				<input type="hidden" name="action" value="login"/>
				
				<div class="form-group">
					<input type="email" class="form-control" placeholder="E-mail" name="email" autocomplete="on">
				</div>	
				<div class="form-group">
					<input type="password" class="form-control" placeholder="Password" name="password">
				</div>
				
				<%
					if(utente_non_trovato != null && utente_non_trovato.equals("true")){
				%>
				
				<div class="alert alert-danger" role="alert">
				  Password o Email non corrette
				</div><br>
				<%
					}
				%>	
				
				<input type="submit" class="btn btn-primary btn-lg" value="Accedi">
				<br>
				<br>
				<a href="#">Password Dimenticata?</a>
				<br>
				<a href="Signup.jsp">Registrati</a>		
			
			</form>
		</div>
	</body>
</html>
