                          <!-- Questa pagina gestisce le categorie dei prodotti (lato admin) -->

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
	<title>Categorie</title>
</head>

<body>
	<h1>[Categorie]</h1>
	<h2><a href="Product?catalog">Torna al catalogo</a></h2>
	
	<h3>Categorie attualmente presenti:</h3>
	
	<ul>
	<%
	if (categories.size()!=0 && categories!=null) {
		for (CategoryBean categoria : categories){	
	%>
	<li><%=categoria.getNome()%> <a href="Admin?action=delete_category&id=<%=categoria.getId()%>&nome=<%=categoria.getNome()%>">elimina</a></li>
	<% 
		}
	}
	else {
	%>
	<li><i>Non sono presenti categorie</i></li>
	<%
	}
	%>
	</ul>
	
	<br/><br/><br/>
	
	<div class="insert">
	<h3>Inserisci una nuova categoria:</h3>
	
	<form action="Admin" method="post">
		<input type="hidden" name="action" value="new_category">
		
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="inserisci nome"><br> 
		
		<input type="submit" value="Modifica">  
		<input type="reset" value="Annulla">
		
	</form>
	</div>


</body>
</html>