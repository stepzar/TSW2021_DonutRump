<%@ page contentType="text/html; charset=UTF-8"
	import="com.donutrump.model.bean.*"%>
	
<%
UserBean user = (UserBean) request.getSession().getAttribute("current_user");
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
      <div class="logo"><a href="#"><img class="logo-img" src="images/logo.png" alt="Logo"></a></div>
        <ul class="links">
          <li><a class="login" href="#">Accedi/Registrati</a></li>
          <li><a href="#">Dolci</a></li>
          <li><a href="#">Salati</a></li>
          <li><a href="#">Bevande</a></li>
          	<%
			if (user == null || !user.isAdmin()) {
			%>
          <li id="li-cart"><a href="Product?action=cart"><img class="cart" src="images/cart.png" alt="Carrello" srcset=""></a></li>
          	<%}%>
        </ul>
      </div>
      <label for="show-search" class="search-icon"><i class="fas fa-search"></i></label>
      <form action="#" class="search-box">
        <input type="text" placeholder="Cerca..." required>
        <button type="submit" class="go-icon"><i class="fas fa-long-arrow-alt-right"></i></button>
      </form>
    </nav>
  </div>

</body>
</html>