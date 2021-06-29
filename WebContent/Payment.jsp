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
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta charset="UTF-8">
		<title>Pagamento</title>
		<link rel="stylesheet" href="styles/formStyle.css" type="text/css">
	</head>
	<body style="background-image: url('images/wallpaper.jpg'); background-size: cover;">
		
		<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			<h2 class="active" style="cursor: default;">PROCEDI CON IL PAGAMENTO</h2>
			
			<!-- Icon -->
      		<div class="fadeIn first">
        		<img src="images/payment-method.png" id="icon" alt="User Icon" />
      		</div>
      		
	
		<h3>Indirizzo di spedizione:</h3>
		
	        <form action="Order" method="post">
		       <!-- INDIRIZZI -->
		       <div id="indirizziSpedizione"> 
		        
				<% for(int i=0; i<indirizzi.size(); i++){%>
				  <input type="radio" name ="id_indirizzo" value="<%=indirizzi.get(i).getId()%>" required> <%=indirizzi.get(i).toString()%> <br/>
		       <%}%> 
		       	</div> 
		
		<h3>Carta di credito:</h3>
				<!-- CARTA CREDITO -->
				<div id="carteDiCredito">
				<% for(int i=0; i<carte.size(); i++ ){%>
				  <input type="radio" name ="numero_carta" value="<%=carte.get(i).getNumeroCarta()%>" required> <%=carte.get(i).getNumeroCartaCifreFinali()%> <br/>
		       <%}%> 
		       	  <input type="hidden" name="action" value="confirm_buy">
				  <input type="hidden" name="justSelected" value="true">
			   </div>
			  
			   <div id="formFooter" style="padding: 3px 3px 3px 3px; margin-top:10%;">
					<input type="submit" class="fadeIn fourth" value="Procedi" >  
					<input type="reset" class="fadeIn fourth" value="Annulla"> 
			   </div>
			</form>
		
	  </div>
	</div>
	
	<%@ include file="Footer.html"%>
	
  </body>
</html>