<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.donutrump.model.bean.*, com.donutrump.model.dao.OrderDAO" %>
   
<%
	UserBean user = (UserBean) request.getSession().getAttribute("current_user");
	if(user == null){
		response.sendRedirect("Login.jsp");	
		return;
	}
	OrderDAO model = new OrderDAO();
%>    
    
<!DOCTYPE html>
<html>

	<head>
	<meta charset="UTF-8">
	<title>I miei Ordini</title>
	</head>
	
	<body>
		<h1>Gli ordini di: ${user.getNome()}</h1>
		<table border="1" style="width:500px; margin: 0 auto; text-align: center;">
			<tr>
				<th>Data Ordine</th>
				<th>Importo Totale</th>
				<th>Dettaglio</th>
			</tr>
			
			<%

			for(OrderBean order: model.userOrders(user.getId())){
			%>
			<tr>
				<td><%=order.getDataOrdine() %></td>
				<td><%=order.getImportoTotale() %></td>
				<td><a href="OrderDetails.jsp?order_id=<%=order.getId()%>">Dettaglio</a></td>
			</tr>
			<%
			}	
			%>
		</table>	
	</body>
</html>