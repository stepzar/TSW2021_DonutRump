<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.donutrump.model.bean.*"%>
    
 <%
	UserBean user = (UserBean) request.getSession().getAttribute("current_user");
	if (user == null || !(user.isAdmin())) {
		response.sendRedirect("Login.jsp");	
		return;
	}
 
 	UserBean cliente = (UserBean) request.getAttribute("cliente");
 	if(cliente != null)
 		System.out.println(cliente.toString());
 	else
 		System.out.println("Cliente non presente");
 	ArrayList<OrderBean> ordini = (ArrayList<OrderBean>) request.getAttribute("ordini_cliente");
 %>
 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Visualizzazione Ordini</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>
	<br>
		<div style="width:350px; margin-left:10px; text-align: center;">
			<h1>Visualizza Ordini</h1>
			<form action="Admin" method="post">
			
				<input type="hidden" name="action" value="search_orders"> 
				
				<div class="form-group">
					<input type="date" class="form-control" name="data_da">
				</div>
				
				<div class="form-group">
					<input type="date" class="form-control" name="data_a">
				</div>
				
				<div class="form-group">
					<input type="text" class="form-control" name="nome" placeholder="Nome cliente">
				</div>
				
				<div class="form-group">
					<input type="text" class="form-control" name="cognome" placeholder="Cognome cliente">
				</div>
				
				<input type="submit" class="btn btn-primary btn-lg" value="Cerca">
			</form>
		</div>
		
		<div>
			<%if(cliente != null){%>
			<br>
			<div style="margin-left:10px;">
				<h1>Ordini di <%=cliente.getNome()%> <%=cliente.getCognome()%></h1>
				
				<table>
					<tr>
						<th>Id</th>
						<th>Data Ordine</th>
						<th>Importo Totale</th>
						<th>Indirizzo</th>
						<th>Stato</th>
						<th>Spesa Spedizione</th>
						<th>Quantita Acquisto</th>
						<th>Data Consegna</th>
						<th>Metodo Pagamento</th>
					</tr>
					
					<%
					for(OrderBean order: ordini){
					%>
					<tr>
						<td><%=order.getId()%></td>
						<td><%=order.getDataOrdine() %></td>
						<td><%=order.getImportoTotale() %></td>
						<td><%=order.getIndirizzo().toString()%></td>
						<td><%=order.getStato()%></td>
						<td><%=order.getSpeseSpedizione()%></td>
						<td><%=order.getQuantitaAcquisto()%></td>
						<td><%=order.getDataConsegna()%></td>
						<td><%=order.getMetodoPagamento().getNumeroCarta()%></td>
					</tr>
					<%
					}	
					%>			
				</table>
			</div>
			<%} %>
		</div>
	</body>
</html>