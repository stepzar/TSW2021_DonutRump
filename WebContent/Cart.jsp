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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="script.js"></script>
<%@ include file="header.jsp" %>
</head>

<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	<div style="min-height: 70vh; justify-content: space-between;">
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
						<img src="<%=bean.getImmagine()%>">
						<div>
							<p><%=bean.getNome()%></p>
							<small>Prezzo: <%=bean.getPrezzo()%>
							</small> <br> <a
								href="Product?action=deletefromcart&id=<%=bean.getId()%>">Rimuovi</a>
						</div>
					</div>
				</td>

				<td>
					<input class="priceProduct" type="hidden" name="price" value="<%=bean.getPrezzo()%>">
					<input class="idProduct" type="hidden" name="id" value="<%=bean.getId()%>">
					<input class="quantity" type="number" name="quantity" min="1" max="<%=bean.getQuantitaDisponibile()%>" value="<%=quantita%>">

				</td>
				<td class="prezzo">EUR <%=tot%></td>
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
				<a <%if (cart == null || cart.getProducts().size()!=0) {%>
					href="Order?action=buy" id="buy-button" <%} else {%> id="buy-button-disable" <%}%>>Acquista</a>
				</div>
			</div>
		
			</div>
	</div>
	</div>

</body>

<%@ include file="Footer.html"%>
</html>