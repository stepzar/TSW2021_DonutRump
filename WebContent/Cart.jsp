<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Cart cart = (Cart) request.getSession().getAttribute("cart");
GeneralProductDAO model = new GeneralProductDAO();
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.HashMap, java.util.Map, com.donutrump.model.bean.*, com.donutrump.model.dao.GeneralProductDAO"%>
<head>
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<link rel="stylesheet" href="styles/Cart.css">
<title>Carrello</title>
</head>

<body>

	<h1>Carrello</h1>

	<div class="small-container cart-container">

		<table>
			<tr>
				<th>Prodotto</th>
				<th>Quantità</th>
				<th>Totale</th>
			</tr>

			<%
			double totaleProdotto = 0;

			if (cart != null && cart.getProducts().size() != 0) {
				HashMap<Integer, Integer> carrello = cart.getProducts();

				for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
					GeneralProductBean bean = model.doRetrieveByKey(entry.getKey());
					int quantita = entry.getValue();
					double tot = quantita * bean.getPrezzo();
					totaleProdotto += tot;
			%>

			<tr>
				<td>
					<div class="cart-info">
						<img src="Mars.png">
						<div>
							<p><%=bean.getNome()%></p>
							<small>Prezzo: <%=bean.getPrezzo()%>
							</small> <br> <a
								href="Product?action=deletefromcart&id=<%=bean.getId()%>">Rimuovi</a>
						</div>
					</div>
				</td>

				<td>
					<form action="Product" method="get">
						<input type="hidden" name="action" value="cart">
						<!-- mi serve perchè altrimenti il form mi rimanda sempre alla homepage, con questa resto nel carrello -->
						<input type="hidden" name="id" value="<%=bean.getId()%>">
						<input type="number" name="quantity" min="1"
							max="<%=bean.getQuantitaDisponibile()%>" value="<%=quantita%>">
						<input type="submit" value="Modifica quantità">
					</form>

				</td>
				<td>EUR <%=tot%></td>
			</tr>

			<%
			}
			} else {
			%>
			<td colspan="6">Il carrello è vuoto</td>
			<%
			}
			%>
		</table>


		<div class="total-price">
			<table>
				<tr>
					<td>Totale</td>
					<td><%=totaleProdotto%></td>
				</tr>
			</table>
		
			
			<div class="buy">
				<div class="wrapperLink">	
				<a <%if (cart.getProducts().size()!=0) {%>
					href="Order?action=buy" id="buy-button" <%} else {%> id="buy-button-disable" <%}%>>Acquista</a>
				</div>
			</div>
		</div>
	</div>


</body>
</html>