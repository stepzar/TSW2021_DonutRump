<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Cart cart = (Cart)request.getSession().getAttribute("cart");
	GeneralProductDAO model = new GeneralProductDAO();
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.HashMap, java.util.Map, com.donutrump.model.bean.*, com.donutrump.model.dao.GeneralProductDAO"%>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<meta charset=UTF-8>
		<title>Carrello</title>
	</head>
	
	<body>
		<div style="margin: 0 auto; text-align: center;">
			<h1>Carrello</h1>
			<h2><a href="Product?catalog">Torna al catalogo</a></h2>
			
			<table border="1" style="width:500px; margin: 0 auto; text-align: center;">
				<tr>
					<th>Nome</th>
					<th>Prezzo</th>
					<th>Iva</th>
					<th>Quantità</th>
					<th>Azioni</th>
				</tr>
				<%	if (cart != null && cart.getProducts().size() != 0) {
						HashMap<Integer, Integer> carrello = cart.getProducts();
						
						for(Map.Entry<Integer, Integer> entry: carrello.entrySet()){
							GeneralProductBean bean = model.doRetrieveByKey(entry.getKey());
							int quantita = entry.getValue();		
				%>
				<tr>
					<td><%=bean.getNome()%></td>
					<td><%=bean.getPrezzo()%></td>
					<td><%=bean.getIva()%></td>
					<td>
						<form action="Product" method="get">
							<input type="hidden" name="action" value="cart"> <!-- mi serve perchè altrimenti il form mi rimanda sempre alla homepage, con questa resto nel carrello -->
							<input type="hidden" name="id" value="<%=bean.getId()%>">
							<input type="number" name= "quantity" min="1" max="<%=bean.getQuantitaDisponibile()%>" value="<%=quantita%>">
							<input type="submit" value="Modifica quantità">
						</form>
					</td>
					<td>
						  <a href="Product?action=deletefromcart&id=<%=bean.getId()%>">Rimuovi</a><br> <!-- qua sto creando questo parametro deleteFromCart che gestirò nella servlet -->
					</td>
					
				<% }} else{ %> 
					<td colspan="6"> Il carrello è vuoto</td>
				<%} %>	
	
				</tr>
	
			</table>
		</div>
		
		<a href="Order?action=buy">Acquista</a>
		
	</body>
</html>