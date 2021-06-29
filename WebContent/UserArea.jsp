<%@page import="java.beans.beancontext.BeanContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
UserBean user = (UserBean) request.getSession().getAttribute("current_user"); 
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.*"%>

<head>
	<meta charset="ISO-8859-1">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<title>Area Utente</title>
	<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
</head>

<body>
<% 	if (user == null) {%>
	<h1>AREA UTENTE</h1>
	<h2>Non sei loggato? <a href="Login.jsp">Accedi</a></h2>
		
<%}else if (!user.isAdmin()){ %>	
	
	<div class=wrappwer style="text-align:center;">
	<h1>AREA UTENTE: <%=user.getNome()%> <%=user.getCognome()%></h1>
	<h2 style="color:black;">ID UTENTE: <%=user.getId()%></h2>
	
		<ul style="list-style: none; padding: 0;">
			<li><a href="MyOrders.jsp">I miei ordini</a></li>
			<li><a href="User?action=logout">Esci</a></li>
		</ul>
		
		</div>
		
		<div class="wrapper fadeInDown" style="margin-bottom:10px;">
		  <div id="formContent">
			 <!-- Tabs Titles -->
			<h2 class="active" style="cursor:default;">Inserisci nuovo pagamento (facoltativo):</h2>
	
			<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/subfolder.png" id="icon" alt="User Icon" />
      		</div>
	
	<p> </p>
	
	<form action="User" method="post"> 
		<input type="hidden" name="action" value="nuovaCartaPagamento"/>
		
		<input type="text" class="fadeIn second" name="numeroCarta" placeholder="Numero Carta"/>
		<input type="text" maxlenght="3" class="fadeIn second" name="cvv" placeholder="CVV"/>
		<input type="date"  class="fadeIn second" name="scadenza" placeholder="Scadenza"/>
		
			<div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:10%;">
				<input type="submit" class="fadeIn fourth" value="Aggiungi">  
				<input type="reset" class="fadeIn fourth" value="Annulla">
			</div>
		</form>	 
	  </div>
	 </div>

	
		<div class="wrapper fadeInDown">
		  <div id="formContent">
		  <!-- Tabs Titles -->
			<h2 class="active" style="cursor:default;"> Inserisci nuovo indirizzo (facoltativo):</h2>
		  
		  <!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/vediOrdini.png" id="icon" alt="User Icon" />
      		</div>
	
	<form action="User" method="post">
		<input type="hidden" name="action" value="nuovoinidirizzo"/>
		
		<input type="text" class="fadeIn second" name="via" placeholder="Via"/>
		<input type="text" class="fadeIn second" name="provincia" placeholder="Provincia"/>
		<input type="number" min=1 class="fadeIn second" name="nCivico" placeholder="numero civico"/>
		<input type="text" class="fadeIn second" name="citta" placeholder="Città"/>
		<input type="text" maxlenght="5" class="fadeIn second" name="cap" placeholder="CAP"/>
		
		<div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:10%;">
				<input type="submit" class="fadeIn fourth" value="Aggiungi">  
				<input type="reset" class="fadeIn fourth" value="Annulla">
			</div>
 		</form>
 	</div>
 </div>
	
	<%} else if(user!=null && user.isAdmin()){%> <!-- se invece è l'admin -->
	<div class="admin_functions" style="text-align: center; width: 20%">
		<!-- Qua ci va il CSS per le liste -->
		<h2>[Funzionalità Amministratore]</h2>
		<ul>
			<li><a href="AdminInsert.jsp"> Inserisci nuovo prodotto </a></li>
			<li><a href="CategoriesManagement.jsp"> Gestione categorie </a></li>
			<li><a href="AdminOrders.jsp"> Visualizza ordini </a></li>
			<li><a href="User?action=logout">Esci</a></li>
		</ul>
	</div>
	<%}%>
</body>
</html>