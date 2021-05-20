<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Registrati</title>
	</head>
	
	<body>
		<br>
		<div style="width:350px; margin: 0 auto; text-align: center;">
			<h1 style="font-weight: 200;">Registrati</h1><br>
			<form action="User" method="post">
				<input type="hidden" name="action" value="signup"/>
	 			
	 			<div class="form-group">
	 				<input type="text" class="form-control" name="nome" placeholder="Nome"/>
	 			</div>
	 			
	 			<div class="form-group">
	 				<input type="text" class="form-control" name="cognome" placeholder="Cognome"/>
	 			</div>
	 			
	 			<div class="form-group">
	 				<input type="tel" class="form-control" name="telefono" placeholder="Telefono" pattern="[0-9]{10}"/>
				</div>
				
				<div class="form-group">
					<input type="email" class="form-control" name="email" placeholder="E-mail"/>
				</div>
				
				<div class="form-group">		
					<input type="password" class="form-control" name="password" placeholder="Password"/>
				</div>
				
				<input type="submit" class="btn btn-primary btn-lg" value="Registrati">
				<br><br>
				<a href="login.html">Già utente? Accedi</a>		

			</form>
		</div>
	</body>
</html>