<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Cart cart = (Cart)request.getSession().getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.*"%>
	<head>
		<meta charset=UTF-8>
		<title>Carrello</title>
	</head>
	
	<body>
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Quantità</th>
				<th>Iva</th>
			</tr>
			<%
				if (cart != null && cart.getProducts().size() != 0) {
					ArrayList<InstanceProductBean> carrello = cart.getProducts();
					for(InstanceProductBean bean : carrello){
						int quantita = 0;
						for(InstanceProductBean bean2: carrello){
							if(bean.getGeneralProduct().getId() == bean2.getGeneralProduct().getId()){
								quantita ++;
							}
						}
			%>
			<tr>
				<td><%=bean.getGeneralProduct().getNome()%></td>
				<td><%=bean.getGeneralProduct().getPrezzo()%></td>
				<td><%=quantita%></td>
				<td><%=bean.getIva()%></td>
			
			<%}} else{%> 
				Il carrello è vuoto
			<%} %>
			</tr>
		</table>
	</body>
</html>