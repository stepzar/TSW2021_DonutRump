<%@page import="java.beans.beancontext.BeanContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("catalog");
if (products == null) {
	response.sendRedirect("./Product");
	return;
}

GeneralProductBean product = (GeneralProductBean) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, com.donutrump.model.bean.*"%>


<head>
<link rel="stylesheet" href="styles/productview.css">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DonutRump</title>
<%@ include file="header.jsp" %>
</head>

<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	<h1>Catalogo</h1>

	<div class="container">

		<div class="catalogo">
			<!--
			<tr>
				<th><a href="Product?sort=id">Codice</a></th>
				<th><a href="Product?sort=nome">Nome</a></th>
				<th><a href="Product?sort=descrizione">Descrizione</a></th>
				<th><a href="Product?sort=prezzo">Prezzo</a></th>
				<th>Azione</th>
			</tr>
			-->
			<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					GeneralProductBean bean = (GeneralProductBean) it.next();
			%>
			<div class="prodotto">

				<a class="prodotto-immagine" href="Product?action=read&id=<%=bean.getId()%>"><img src="<%=bean.getImmagine()%>" alt="<%=bean.getImmagine()%>" style="width: 80%;"></a><br>

				<h2 class="prodotto-nome">
					<%=bean.getNome()%>
				</h2>

				<h3 class="prodotto-prezzo">
					€ <%=bean.getPrezzo()%>
				</h3>

				<%
				if (user != null && user.isAdmin()) {
				%>

				<a class="comando" href="Product?action=delete&id=<%=bean.getId()%>">Elimina dal catalogo</a><br> 				
				<%
				} else {
				%>

				<%
				if (bean.isDisponibilita()) {
				%>
				<a href="Product?action=addcart&id=<%=bean.getId()%>"
					class="comando">Aggiungi al Carrello</a>
				<%
				} else {
				%>
				<a href="#" class="btn btn-secondary btn-sm disabled">Non
					disponibile</a>
				<%
				}
				}
				%>
			</div>
			<%
			}
			} else {
			%>
			<p>Nessun prodotto disponibile nel catalogo</p>
			<%
			}
			%>
		</div>
	</div>

	<%if(user!=null && user.isAdmin()){%>
	<div class="admin_functions" style="text-align: center; width: 20%">
		<!-- Qua ci va il CSS per le liste -->
		<h2>[Funzionalità Amministratore]</h2>
		<ul>
			<li><a href="AdminInsert.jsp"> Inserisci nuovo prodotto </a></li>
			<li><a href="CategoriesManagement.jsp"> Gestione categorie </a></li>
			<li><a href="AdminOrders.jsp"> Visualizza ordini </a></li>
		</ul>
	</div>
	<%}%>
</body>

</html>