<%@page import="java.beans.beancontext.BeanContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("catalog");
    if(products == null) {
		response.sendRedirect("./Product");	
		return;
	}
    
	GeneralProductBean product = (GeneralProductBean) request.getAttribute("product");
	UserBean user = (UserBean) request.getSession().getAttribute("current_user");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.*" %>


<head>
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>DonutRump</title>
</head>

<body>
	<h1 style="margin: 0 auto; text-align: center;">Catalogo</h1>
	<%
		if(user == null){
	%>
			<div style="text-align: right; padding: 10px;">
				<a href="Login.jsp">Accedi</a>
				<a href="Signup.jsp">Registrati</a>
			</div>
	<%			
		}
		else{%>
			<div style="text-align: right; padding: 10px;">
				<a href="#">Area Utente</a>
				<a href="User?action=logout">Esci</a>
			</div>
	<%
		}
	%>
	
	<div style="margin: 0 auto; text-align: center;">
		
		<%  if (user==null) { %>
		
			<h2><a href="Product?action=cart">Carrello</a></h2>
		
		<% } 
			else {	
				if (!user.isAdmin()) { %>
					<h2><a href="Product?action=cart">Carrello</a></h2>
			
			<% } else { %>
					<!-- NADA: non visualizza il carrello, perchè sarà l'admin -->
			<% } %>
		
		<% } %>
		
		<a href="Product">List</a>
		<table border="1" style="width:500px; margin: 0 auto; text-align: center;">
			<tr>
				<th><a href="Product?sort=id">Codice</a></th>
				<th><a href="Product?sort=nome">Nome</a></th>
				<th><a href="Product?sort=descrizione">Descrizione</a></th>
				<th><a href="Product?sort=prezzo">Prezzo</a></th>
				<th>Azione</th>
			</tr>
			<%
				if (products != null && products.size() != 0) {
					Iterator<?> it = products.iterator();
					while (it.hasNext()) {
						GeneralProductBean bean = (GeneralProductBean) it.next();
			%>
			<tr>
				<td><%=bean.getId()%></td>
				<td><%=bean.getNome()%></td>
				<td><%=bean.getDescrizione()%></td>
				<td><%=bean.getPrezzo()%></td>
	
				<td>
	
				<% 
				if (user!=null && user.isAdmin()) {
				%>
					<a href="Product?action=delete&id=<%=bean.getId()%>">Elimina dal catalogo</a><br>
					<a href="Product?action=read&id=<%=bean.getId()%>">Dettagli/Modifica</a><br>
				<% 
				}
				else { 
				%>	
					<a href="Product?action=read&id=<%=bean.getId()%>">Dettagli</a><br>
					
				
			<%
					
					if(bean.isDisponibilita()){
			%>
					<a href="Product?action=addcart&id=<%=bean.getId()%>" class="btn btn-primary btn-sm">Aggiungi al Carrello</a></td> 
			<%
					}else{
			%>
					<a href="#" class="btn btn-secondary btn-sm disabled">Non disponibile</a>
			<%
					}
				}	
			%>
	 		</tr>
	 		
			<%
					}
				} else {
			%>
			<tr>
				<td colspan="6">Nessun prodotto disponibile nel catalogo</td>
			</tr>
			<%
				}
			%>
		</table>
		</div>
		
		<% 
		// CONTROLLO DI SICUREZZA
		if (user!=null && user.isAdmin()) {
		%>
		
		<div class="admin_functions" style="text-align: center; width:20%"> <!-- Qua ci va il CSS per le liste -->
			<h2>[Funzionalità Amministratore]</h2>
			<ul>
				<li> <a href="AdminInsert.jsp"> Inserisci nuovo prodotto </a> </li>
				<li> <a href="CategoriesManagement.jsp"> Gestione categorie </a></li>
				<li> <a href="AdminOrders.jsp"> Visualizza ordini </a></li>
			</ul>
		</div>
		<% 
		} else {/* does not show it */}
		%> 

</body>
</html>