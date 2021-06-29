                          <!-- Questa pagina gestisce le categorie dei prodotti (lato admin) -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.donutrump.model.bean.GeneralProductBean, com.donutrump.model.bean.UserBean, 
    com.donutrump.model.bean.CategoryBean, com.donutrump.model.dao.CategoryDAO" %>
 
 <%
 	CategoryDAO categoryModel = new CategoryDAO(); 
 	ArrayList<CategoryBean> categories = categoryModel.doRetrieveAll(null); 
 	
 	//**************controllo di sicurezza: Solo l'admin può accedere a questa pagina di inserimento prodotto**************
 	UserBean user = (UserBean) request.getSession().getAttribute("current_user");
 	if ( user == null || !(user.isAdmin())) {
 		response.sendRedirect("Login.jsp");	
 		return;
 	}
%>
<!DOCTYPE html>
<html>

<head>
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta charset="UTF-8">
	<title>Categorie</title>
	<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
</head>

<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	
	<div class="wrapper fadeInDown">
	  <div id="formContent">
		 <!-- Tabs Titles -->
		<h2 class="active" style="cursor:default;"> CATEGORIE </h2>
		
		<!-- Icon -->
   		<div class="fadeIn first">
       		<img src="images/subfolder.png" id="icon" alt="User Icon" />
    	</div>
		
	
		<h3>Categorie attualmente presenti:</h3>
	
		<ul>
		<%
		if (categories.size()!=0 && categories!=null) {
			for (CategoryBean categoria : categories){	
		%>
		<li><%=categoria.getNome()%> <a href="Admin?action=delete_category&id=<%=categoria.getId()%>&nome=<%=categoria.getNome()%>" id="deleteCategories">elimina</a></li>
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
			<input name="name" type="text" maxlength="20" class="fadeIn second" required placeholder="Nuova Categoria"><br> 
			<div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:10%;">
				<input type="submit" class="fadeIn fourth" value="Aggiungi">  
				<input type="reset" class="fadeIn fourth" value="Annulla">
			</div>	
		</form>
		</div>
	  </div>
	</div>
  </body>
</html>