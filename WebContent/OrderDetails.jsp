<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.donutrump.model.bean.*, com.donutrump.model.dao.*, java.util.HashMap, java.util.Map" %>

<%
	int order_id = Integer.parseInt(request.getParameter("order_id"));
	OrderDAO model = new OrderDAO();
	
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link rel="stylesheet" href="styles/orderdetails.css">
		<title>Dettaglio Ordine <%=order_id%></title>
		<%@ include file="header.jsp" %>
	</head>
	
	<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
	
			<%
				OrderBean bean = model.doRetrieveByKey(order_id);
			%>

		<h1 class="data-ordine">
			Ordine effettuato il: <%=bean.getDataOrdine() %>
		</h1>

		
		<div class="table">

			<div class="container-info">
				<p class="totale-prodotto">
					Totale € <%=bean.getImportoTotale()%>
				</p>
	
				<p class="spedizione-prodotto">
					Spedizione € <%=bean.getSpeseSpedizione()%>
				</p>
	
				<p class="indirizzo-prodotto">
					Indirizzo: <%=bean.getIndirizzo().toString()%>
				</p>
	
				<p class="fattura-prodotto">
					<a href="#">Scarica Fattura</a>
				</p>
			</div>

			<div class="box-prodotti">
				<%if(bean.getDataConsegna() != null){ %>
				<p class="consegna">Consegnato il: <%=bean.getDataConsegna()%></p>
				<%}else{ %>
				<p class="consegna">Ancora non consegnato...</p>
				<%} %>
				
				<div class="prodotti">
						<%
						InstanceProductDAO instanceProductModel = new InstanceProductDAO();
						GeneralProductDAO generalProductModel = new GeneralProductDAO();
						
						// creo una lista di generalproduct (id) con relative quantità acquistate e le mostro nella jsp
						HashMap<Integer, Integer> prodottiAcquistati = new HashMap<Integer, Integer>();
						
						// itero tutti i prodotti acquistati
						for(InstanceProductBean instance :  instanceProductModel.doRetrieveAllByOrdine(order_id)){
							
							if(prodottiAcquistati.containsKey(instance.getId())){
								int quantita = (int) prodottiAcquistati.get(instance.getId());
								prodottiAcquistati.put(instance.getProdottoGenerico().getId(), quantita+1);
							}
							else{
								prodottiAcquistati.put(instance.getProdottoGenerico().getId(), 1);
							}
						}
						
						for(Map.Entry<Integer, Integer> entry: prodottiAcquistati.entrySet()){
							GeneralProductBean general = generalProductModel.doRetrieveByKey(entry.getKey());
						%>
						
						<div class="prodotto">
							<img class="immagine-prodotto" src="<%=general.getImmagine()%>" alt="<%=general.getNome()%>">
							<div class="informazioni-prodotto">
								<p class="nome-prodotto"><%=general.getNome()%></p>
								<p class="quantita-prodotto">Quantità: <%=entry.getValue()%></p>
								<p class="descrizione-prodotto"><%=general.getDescrizione()%></p>
							</div>
						</div>	
						
						<%} %>
						
				</div>
			</div>

		</div>
	
		
	</body>
</html>