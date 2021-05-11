<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Cart cart = (Cart)request.getSession().getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*, com.donutrump.model.bean.*, java.util.LinkedHashMap;"%>
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
					ArrayList<GeneralProductBean> carrello = cart.getProducts();
					Map<GeneralProductBean, Integer> filterCarrello = new LinkedHashMap<GeneralProductBean, Integer>();

					for(GeneralProductBean bean : carrello){
						int quantita = 0;
						filterCarrello.put(bean, quantita); 
						
						for(GeneralProductBean bean2: carrello){
                             if(bean.getId() == bean2.getId()) {
                            	 quantita ++;
                            	 filterCarrello.put(bean2, quantita); 
                             }
						}
					
					}		
					
					for (Map.Entry<GeneralProductBean, Integer> entry : filterCarrello.entrySet()){
						GeneralProductBean bean = entry.getKey(); 
						Integer quantita = entry.getValue(); 
					
			%>
			<tr>
				<td><%=bean.getNome() %></td>
				<td><%=bean.getPrezzo()%></td>
				<td><%=quantita%></td>
				<td><%=bean.getIva()%></td>
			
			<%}} else{%> 
				<td colspan="6"> Il carrello è vuoto </td>
			<%} %>
			</tr>
		</table>
	</body>
</html>