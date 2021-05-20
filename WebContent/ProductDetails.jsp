                          <!-- Questa pagina gestisce i dettagli di ProdottoGenerico -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
	GeneralProductBean product = (GeneralProductBean) request.getAttribute("product");
%>
 
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.GeneralProductBean"%>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<meta charset=UTF-8>
		<title>Dettagli</title>
	</head>
	
	<body>
		<h1>[DETTAGLI]</h1>
		<h2><a href="Product?catalog">Torna al catalogo</a></h2>
		<%
			if (product != null) {
		%>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th>Quantità</th>
				<th>Iva</th>
				<th>Disponibilità</th>
				<th>+ Carrello</th>
			</tr>
			<tr>
				<td><%=product.getId()%></td>
				<td><%=product.getNome()%></td>
				<td><%=product.getDescrizione()%></td>
				<td><%=product.getPrezzo()%></td>
				<td><%=product.getQuantitaDisponibile()%></td>
				<td><%=product.getIva()%></td>
				<td><% if(product.isDisponibilita() == true)
				{%>Si<%}
					else{%>No<%}%></td>
				<td><a href="Product?action=addcart&id=<%=product.getId()%>">Aggiungi al Carrello</a>
			</tr>
		</table>
		<%
			} 
			else { %>
			<h3>ERRORE: Dettagli non disponibili</h3>
	<% }%>
	</body>
</html>