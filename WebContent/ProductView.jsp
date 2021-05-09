<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./Product");	
		return;
	}
	GeneralProductBean product = (GeneralProductBean) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.GeneralProductBean"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>DonutRump</title>
</head>

<body>
	<h2>Prodotti</h2>
	<a href="Product">List</a>
	<table border="1">
		<tr>
			<th><a href="Product?sort=name">Nome</a></th>
			<th><a href="Product?sort=description">Descrizione</a></th>
			<th><a href="Product?sort=price">Prezzo</a></th>
			<th>Azione</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					GeneralProductBean bean = (GeneralProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getNome()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><%=bean.getPrezzo()%></td>

			<td><a href="Product?action=delete&id=<%=bean.getId()%>">Elimina</a><br>
				<a href="Product?action=read&id=<%=bean.getId()%>">Dettagli</a></td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<h2>Dettagli</h2>
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
				else{%>no<%}%></td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Inserisci</h2>
	<form action="Product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
		
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="price">Prezzo:</label><br> 
		<input name="price" type="number" min="0" value="0" required><br>

		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
		
		<label for="iva">Iva:</label><br> 
		<input name="iva" type="number" min="1" value="22" required><br>

		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">

	</form>
</body>
</html>