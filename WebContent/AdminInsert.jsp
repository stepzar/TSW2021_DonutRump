<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.donutrump.model.bean.GeneralProductBean, com.donutrump.model.bean.UserBean, 
    com.donutrump.model.bean.CategoryBean, com.donutrump.model.dao.CategoryDAO" %>

<%
CategoryDAO categoryModel = new CategoryDAO(); 
ArrayList<CategoryBean> categories = categoryModel.doRetrieveAll(null);  

//**************controllo di sicurezza: Solo l'admin può accedere a questa pagina di inserimento prodotto**************
UserBean user = (UserBean) request.getSession().getAttribute("current_user"); 
if ( user == null || !(user.getEmail().equals("admin@donut.rump.com") && user.getPswd().equals("root")) ) {
	response.sendRedirect("./Product");	
	return;
}
%>

 <!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Inserimento Prodotto</title>
</head>

<body>
	<h1>[Inserisci nuovo prodotto al catalogo]</h1>
	<h2><a href="Product?catalog">Torna al catalogo</a></h2>
	
	<form action="Product" method="get">
		<input type="hidden" name="action" value="insert"> 
		
		<p>Categoria:</p>
		<% 
		if (categories!=null && categories.size()!=0){
			for (CategoryBean categoria : categories){	
		%>
		<input type="radio" name="category" value="<%=categoria.getNome()%>" >
		<label for="category"> <%=categoria.getNome()%></label><br> 
		<% 
			}
		}
		
		else {   //la categoria (id=1 nome=generico) è una "categoria di salvataggio" in caso di errori da parte dell'inserimento
		%>
		<label for="category">Categoria:</label><br>
		<input type="text" name="category" value="generico" maxlength="20" required readonly><br>
		
		<% 
		}
		%>
		<br/>
			
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
			
		<label for="description">Descrizione:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
			
		<label for="price">Prezzo:</label><br> 
		<input name="price" type="number" step=".01" min="0" value="0" required><br>
	
		<label for="quantity">Quantità:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>
			
		<label for="iva">Iva:</label><br> 
		<input name="iva" type="number" min="1" value="22" required><br>
	
		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">
	</form>

</body>
</html>  