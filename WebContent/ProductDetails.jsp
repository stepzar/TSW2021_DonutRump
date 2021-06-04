                          <!-- Questa pagina gestisce i dettagli di ProdottoGenerico -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.donutrump.model.bean.GeneralProductBean, com.donutrump.model.bean.UserBean, 
    com.donutrump.model.bean.CategoryBean, com.donutrump.model.dao.CategoryDAO" %>
 
 <%
	GeneralProductBean product = (GeneralProductBean) request.getAttribute("product");
 	UserBean user = (UserBean) request.getSession().getAttribute("current_user");
 	
 	CategoryDAO categoryModel = new CategoryDAO(); 
 	ArrayList<CategoryBean> categories = categoryModel.doRetrieveAll(null); 
 	
 	CategoryBean category = product.getCategoria(); 
 	if (category == null ){ 
 	   category.setNome("generico"); 
 	} 
%>
 
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<meta charset=UTF-8>
		<title>Dettagli</title>
	</head>
	
	<body>
		<%
			if (product != null && user!=null && user.getEmail().equals("admin@donut.rump.com") && user.getPswd().equals("root")) {
		%>
		
		                       <!-- vista e modifica di un prodotto generico lato amministratore -->
		<h1>[MODIFICA PRODOTTO]</h1>
		<h2><a href="Product?catalog">Torna al catalogo</a></h2>
		
		
		<form action="Admin" method="get">
		<input type="hidden" name="action" value="mod_product"> 
		<input type="hidden" name="id" value="<%=product.getId()%>"> <!-- ho bisogno di questo parametro perchè quanto c'è il disabled non viene inviata correttamente la coppia nome-valore al server, usiamo il disabled perchè neanche l'admin può modificare l'id di un prodotto -->
		
		<label for="id_locked">Id:</label><br> 
		<input type="number" name="id_locked" min="0" value="<%=product.getId()%>" disabled required> <br>
			
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="20" value="<%=product.getNome()%>" required><br> 
			
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="100" rows="3" required><%=product.getDescrizione()%></textarea><br>
			
		<label for="price">Prezzo:</label><br> 
		<input type="number" name="price" step="0.1" min="0" value="<%=product.getPrezzo()%>" required><br>
	
		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="<%=product.getQuantitaDisponibile()%>" required ><br>
			
		<label for="iva">Iva:</label><br> 
		<input name="iva" type="number" min="1" value="<%=product.getIva()%>" required><br><br>
		
		<label for="category">Categoria:</label>
		<select name= "category">
		<% 
		if (categories!=null && categories.size()!=0){
		%>
		<option value="<%=category.getNome()%>" selected><%=category.getNome()%></option>
		<%	for (CategoryBean categoria : categories){	
				if (categoria.equals(category)==false) {
		%>
		<option value="<%=categoria.getNome()%>"><%=categoria.getNome()%></option>
		<% 
				}
			}
		}
		else { //altrimenti mostra selezionabile solo la categoria a cui già appartiene
		%>
		 <option value="<%=category.getNome()%>" selected><%=category.getNome()%></option>
		<% 
		}
		%>
		</select>
		<br/>
		<br/>
		<label for="image">Immagine:</label><br> 
		<input type="file" name="image"><br><br><br>
	
		<input type="submit" value="Modifica">  
		<input type="reset" value="Annulla">
		
	</form>
		
		
		
		<%}
		  else if (product != null) {
			  if (user!=null && !user.getEmail().equals("admin@donut.rump.com") && !user.getPswd().equals("root")){
		%>
		<h1>[DETTAGLI]</h1>
		<h2><a href="Product?catalog">Torna al catalogo</a></h2>
		
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th>Iva</th>
				<th>Disponibilità</th>
				<th>+ Carrello</th>
			</tr>
			<tr>
				<td><%=product.getNome()%></td>
				<td><%=product.getDescrizione()%></td>
				<td><%=product.getPrezzo()%></td>
				<td><%=product.getIva()%></td>
				<td><% if(product.isDisponibilita() == true)
				{%>Si<%}
					else{%>No<%}%></td>
				<td><a href="Product?action=addcart&id=<%=product.getId()%>">Aggiungi al Carrello</a>
			</tr>
		</table>
		
		<% 
			}
			else if (user==null) {
		%>
		<h1>[DETTAGLI]</h1>
		<h2><a href="Product?catalog">Torna al catalogo</a></h2>
		
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Descrizione</th>
				<th>Prezzo</th>
				<th>Iva</th>
				<th>Disponibilità</th>
				<th>+ Carrello</th>
			</tr>
			<tr>
				<td><%=product.getNome()%></td>
				<td><%=product.getDescrizione()%></td>
				<td><%=product.getPrezzo()%></td>
				<td><%=product.getIva()%></td>
				<td><% if(product.isDisponibilita() == true)
				{%>Si<%}
					else{%>No<%}%></td>
				<td><a href="Product?action=addcart&id=<%=product.getId()%>">Aggiungi al Carrello</a>
			</tr>
		</table>		
		<%
			}
		
		} 
			else { 
		%>
			<h3>ERRORE: Pagina non disponibile</h3>
	<% }%>
	</body>
</html>