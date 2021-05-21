<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.donutrump.model.dao.*, com.donutrump.model.bean.*"%>
    
    <% 
    UserBean currentUser = (UserBean) request.getSession().getAttribute("current_user"); 
    
    //INDIRIZZI
    AddressDAO model = new AddressDAO ();  
	ArrayList<AddressBean> indirizzi = model.userAddresses(currentUser.getId());
	System.out.println(indirizzi);
	
	//CARTE PAGAMENTO
    PaymentMethodDAO payModel = new PaymentMethodDAO ();  
	ArrayList<PaymentMethodBean> carte = payModel.userPayments(currentUser.getId()); 
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Pagamento</title>
	</head>
	<body>
		<h1>[Procedi all'ordine]</h1><br>
	
		<h3>Scelta indirizzo:</h3>
		<div>
	        <form action="Order" method="post">
		       <!-- INDIRIZZO -->
		        <div>
				<% for(int i=0; i<indirizzi.size(); i++){%>
				  <input type="radio" name ="id_indirizzo" value="<%=indirizzi.get(i).getId()%>"> <%=indirizzi.get(i).toString()%>
		       <%}%> 
		       	  
				</div>
				
				<h3>Scelta metodo di pagamento:</h3>
				<!-- CARTA CREDITO -->
				<div>
				<% for(int i=0; i<carte.size(); i++ ){%>
				  <input type="radio" name ="numero_carta" value="<%=carte.get(i).getNumeroCarta()%>"> <%=carte.get(i).getNumeroCarta()%>
		       <%}%> 
		       	  <input type="hidden" name="action" value="confirm_buy">
				  <input type="hidden" name="justSelected" value="true"><br>
				  <input type="submit" value="Conferma">
				</div>
			</form>
		</div>
	</body>
</html>