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
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta charset="UTF-8">
		<title>Visualizzazione Ordini</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
	</head>
	
	<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	
		<div class="wrapper fadeInDown">
	  <div id="formContent">
		 <!-- Tabs Titles -->
		<h2 class="active" style="cursor:default;">VISUALIZZA ORDINI</h2>
		
		<!-- Icon -->
   		<div class="fadeIn first">
       		<img src="images/vediOrdini.png" id="icon" alt="User Icon" />
    	</div>
		
			<form action="Admin" method="post">
				
				<input type="hidden" name="action" value="search_orders"> 
				<p>Dalla data:</p>
				<input type="date" class="form-control" name="data_da">
				<p>Alla data:</p>
				<input type="date" class="form-control" name="data_a">
				<p>Per Cliente:</p>
				<input type="text" class="form-control" name="nome" placeholder="Nome cliente">
				<input type="text" class="form-control" name="cognome" placeholder="Cognome cliente">
				<div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:10%;">
					<input type="submit" class="fadeIn fourth" value="Cerca">  
					<input type="reset" class="fadeIn fourth" value="Annulla">
				</div>
				
			</form>
		</div>
		
		<br/> <br/>
		
		<div>
		
			<%if(ordini!=null){%>
			<br>
			
			<table class="table">
			  <thead>
			    <tr>
					<th scope="col">Id</th>
					<th scope="col">Data Ordine</th>
					<th scope="col">Importo Totale</th>
					<th scope="col">Indirizzo</th>
					<th scope="col">Stato</th>
					<th scope="col">Spesa Spedizione</th>
					<th scope="col">Quantita Acquisto</th>
					<th scope="col">Data Consegna</th>
					<th scope="col">Metodo Pagamento</th>     
			    </tr>
			  </thead>
			  <tbody>
			  
				<%	for(OrderBean order: ordini){ %>
					
			    <tr>
					<td><%=order.getId()%></td>
					<td><%=order.getDataOrdine()%></td>
					<td><%=order.getImportoTotale()%></td>
					<td><%=order.getIndirizzo().toString()%></td>
					<td><%=order.getStato()%></td>
					<td><%=order.getSpeseSpedizione()%></td>
					<td><%=order.getQuantitaAcquisto()%></td>
					<td><%=order.getDataConsegna()%></td>
					<td><%=order.getMetodoPagamento().getNumeroCartaCifreFinali()%></td>			    
				</tr>
				<%
					}	
					%>
			  </tbody>
			</table>
			</div>
			<%} %>
	</body>
</html>
			