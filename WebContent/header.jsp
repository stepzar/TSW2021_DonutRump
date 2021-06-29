<%@ page contentType="text/html; charset=UTF-8"
	import="com.donutrump.model.bean.*, java.util.ArrayList"%>
	
<%
UserBean userHeader = (UserBean) request.getSession().getAttribute("current_user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="styles/header.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
  <div class="wrapper">
    <nav>
      <input type="checkbox" id="show-search">
      <input type="checkbox" id="show-menu">
      <label for="show-menu" class="menu-icon"><i class="fas fa-bars"></i></label>
      <div class="content">
      <div class="logo"><a href="ProductView.jsp"><img class="logo-img" src="images/logo.png" alt="Logo"></a></div>
        <ul class="links">
        <%
        if(userHeader == null){
        %>
          <li><a class="login" href="Login.jsp">Accedi/Registrati</a></li>
        <%}else{%>
          <li><a class="login" href="UserArea.jsp">Area Utente</a></li>
        <%} %>
          <li><a href="Product?action=filtercategory&category=dolce">Dolci</a></li>
          <li><a href="Product?action=filtercategory&category=salato">Salati</a></li>
          <li><a href="Product?action=filtercategory&category=bevande">Bevande</a></li>
          	<%
			if (userHeader == null || !userHeader.isAdmin()) {
			%>
          <li id="li-cart"><a href="Product?action=cart"><img class="cart" src="images/cart.png" alt="Carrello" srcset=""></a></li>
          	<%}%>
        </ul>
      </div>
      <label for="show-search" class="search-icon"><i class="fas fa-search"></i></label>
      <form action="Product" class="search-box" method="get">
      	<input type="hidden" name="action" value="searchproduct">
        <input id="searchbar" type="text" name="keyword" placeholder="Cerca..." required>
        <button type="submit" class="go-icon"><i class="fas fa-long-arrow-alt-right"></i></button>
      </form>
    </nav>
  </div>

</body>
</html>