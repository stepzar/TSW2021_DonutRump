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
		<h1>[CARRELLO]</h1>
		<h2><a href="Product?catalog">Torna al catalogo</a></h2>
		
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Iva</th>
				<th>Quantità</th>
				<th>Azioni</th>
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
						
						//qui stiamo settando la quantita iniziale che il prodotto ha nel carello (cioè quella aggiunta dal catalogo o in generale da fuori il carrello)
						if(!visited.contains(bean.getId())){
							visited.add(bean.getId());
			%>
			<tr>
				<td><%=bean.getNome()%></td>
				<td><%=bean.getPrezzo()%></td>
				<td><%=bean.getIva()%></td>
				<td>
					<form action="Product" method="get">
						<input type="hidden" name="action" value="cart"> <!-- mi serve perchè altrimenti il form mi rimanda sempre alla homepage, con questa resto nel carrello -->
						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="hidden" name="oldQuantity" value="<%=quantita%>"> <!-- mi serve mandare alla servlet la quantità prima della modifica  -->
						<input type="number" name= "quantity" min="1" max="<%=bean.getQuantitaDisponibile()%>" value="<%=quantita%>">
						<input type="submit" value="Modifica quantità">
					</form> 
				</td>
				<td>
					  <a href="Product?action=cart&deleteFromCart=true&id=<%=bean.getId()%>&oldQuantity=<%=quantita%>">Elimina dal carrello</a><br> <!-- qua sto creando questo parametro deleteFromCart che gestirò nella servlet -->
				</td>
				
				
				
			<% }}} else{ %> 
				<td colspan="6"> Il carrello è vuoto</td>
			<%} %>	
			
			
			<!-- questo codice lo esegue dopo il return della servlet -->	
			<%  if ( request.getParameter("deleteFromCart")!=null && request.getParameter("deleteFromCart").equals("true") ){
				response.sendRedirect("Product?action=cart"); //il sendRedirect è meglio usarlo nelle jsp rispetto che nelle Servlet
			} %>	

			</tr>

		</table>
	</body>
</html>