<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.donutrump.model.bean.OrderBean, com.donutrump.model.dao.*" %>

<%
	int order_id = Integer.parseInt(request.getParameter("order_id"));
	OrderDAO model = new OrderDAO();
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Dettaglio Ordine <%=order_id%></title>
	</head>
	
	<body>
		<table border="1" style="width:500px; margin: 0 auto; text-align: center;">
			<tr>
				<th>Id</th>
				<th>Indirizzo</th>
				<th>Stato</th>
				<th>Data Ordine</th>
				<th>Importo Totale</th>
				<th>Spesa Spedizione</th>
				<th>Quantita Acquisto</th>
				<th>Data Consegna</th>
				<th>Metodo Pagamento</th>
			</tr>
			
			<%
				OrderBean bean = model.doRetrieveByKey(order_id);
			%>
			
			<tr>
				<td><%=order_id%></td>
				<td><%=bean.getIndirizzo().toString()%></td>
				<td><%=bean.getStato()%></td>
				<td><%=bean.getDataOrdine()%></td>
				<td><%=bean.getImportoTotale()%></td>
				<td><%=bean.getSpeseSpedizione()%></td>
				<td><%=bean.getQuantitaAcquisto()%></td>
				<td><%=bean.getDataConsegna()%></td>
				<td><%=bean.getMetodoPagamento().getNumeroCarta()%></td>
			</tr>
		</table>
	</body>
</html>