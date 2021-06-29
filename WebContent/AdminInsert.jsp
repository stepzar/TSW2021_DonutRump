<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*, com.donutrump.model.bean.GeneralProductBean, com.donutrump.model.bean.UserBean, 
    com.donutrump.model.bean.CategoryBean, com.donutrump.model.dao.CategoryDAO"%>

<%
CategoryDAO categoryModel = new CategoryDAO();
ArrayList<CategoryBean> categories = categoryModel.doRetrieveAll(null);

//**************controllo di sicurezza: Solo l'admin puï¿½ accedere a questa pagina di inserimento prodotto**************
UserBean user = (UserBean) request.getSession().getAttribute("current_user");
if (user == null || !(user.isAdmin())) {
	response.sendRedirect("Login.jsp");
	return;
}
%>

<!DOCTYPE html>
<html>

<head>
	<meta content="width=device-width, initial-scale=1" name="viewport"/>
	<meta charset="UTF-8">
	<title>Inserimento Prodotto</title>
	<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
</head>

<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">

	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<h2 class="active" style="cursor: default;">INSERIMENTO PRODOTTO</h2>
			
			<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/insert.png" id="icon" alt="User Icon" />
      		</div>

			<form action="Admin" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="insert">
				
				<p>Categoria:</p>
				<div class="insertCategories">

				<div class="boxRadio">
					<%
					if (categories != null && categories.size() != 0) {
						for (CategoryBean categoria : categories) {
					%>
					<input type="radio" name="category"
						value="<%=categoria.getNome()%>"> <label for="category">
						<%=categoria.getNome()%></label><br/>
					<%
					}
					%>
					</div>
					
				</div>
				<%
				}

				else { //la categoria (id=1 nome=generico) ï¿½ una "categoria di salvataggio" in caso di errori da parte dell'inserimento
				%>
				<label for="category">Categoria:</label><br> <input type="text"
					name="category" value="generico" maxlength="20" required readonly><br>

				<%
				}
				%>
				<br/> <br/> <label for="name">Nome:</label><br> 
				<input name="name" class="fadeIn second" type="text" maxlength="20" required
					placeholder="Mars Protein Bar"><br> <label
					for="description">Descrizione:</label><br>
				<textarea name="description" class="fadeIn second" maxlength="300"
					required
					placeholder="Barretta di cioccolato Mars Protein Bar da 50g, con cuore al caramello filante e croccante copertura di cioccolato al latte in una versione proteica..."></textarea>
				<br> <label for="price">Prezzo:</label><br> <input
					name="price" type="number" class="fadeIn second" step=".01" min="0"
					value="0" required><br> <label for="quantity">Quantità:</label><br>
				<input name="quantity" type="number" class="fadeIn second" min="1"
					value="1" required><br> <label for="iva">Iva:</label><br>
				<input name="iva" style="margin-bottom: 10%;" type="number"
					class="fadeIn second" min="1" value="22" required><br>
				<label for="image">Carica immagine prodotto:</label>
				<input type="file" name="image" class="fadeIn fourth"><br>
				<div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:30px;">
					<input type="submit" value="Aggiungi" class="fadeIn fourth">
					<input type="reset" value="Annulla" class="fadeIn fourth">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
