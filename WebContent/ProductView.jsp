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
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
				<a href="User?action=logout">Esci</a>
			</div>
	<%
		}
	%>
	
	<div style="margin: 0 auto; text-align: center;">
		
		<h2><a href="Product?action=cart">Carrello</a></h2>
	
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
	
				<td><a href="Product?action=delete&id=<%=bean.getId()%>">Elimina dal catalogo</a><br>
					<a href="Product?action=read&id=<%=bean.getId()%>">Dettagli</a><br>
			<%
					if(bean.isDisponibilita()){
			%>
					<a href="Product?action=addcart&id=<%=bean.getId()%>" class="btn btn-primary btn-sm">Aggiungi al Carrello</a></td> 
			<%
					}else{
			%>
					<a href="#" class="btn btn-secondary btn-sm disabled">Aggiungi al Carrello</a>
			<%
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
		 
		<h2>Inserisci</h2>
		<form action="Product" method="get">
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
	</div>
</body>
</html>