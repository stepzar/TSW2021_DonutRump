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
				<th>Iva</th>
				<th>Quantità</th>
			</tr>
			<%	if (cart != null && cart.getProducts().size() != 0) {
					ArrayList<GeneralProductBean> carrello = cart.getProducts();
					ArrayList<Integer> visited = new ArrayList<Integer>();
					
					for(GeneralProductBean bean: carrello){
						int quantita = 0;
						for(GeneralProductBean bean2: carrello){
							if(bean.getId() == bean2.getId()){
								quantita++;
							}
						}
						if(!visited.contains(bean.getId())){
							visited.add(bean.getId());
			%>
			<tr>
				<td><%=bean.getNome()%></td>
				<td><%=bean.getPrezzo()%></td>
				<td><%=bean.getIva()%></td>
				<form action="Product" method="get">
					<input type="hidden" name="quantita" value="aggiorna"> 
					<td><input type="number" min="1" max="<%=bean.getQuantitaDisponibile()%>" value="<%=quantita%>"></td>
				</form>
			
			<%}}} else{%> 
				<td colspan="6"> Il carrello è vuoto</td>
			<%} %>			</tr>

		</table>
	</body>
</html>