package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.bean.*;
import com.donutrump.model.dao.*;

@WebServlet("/Order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();
	static InstanceProductDAO InstanceModel = new InstanceProductDAO();
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		UserBean currentUser = (UserBean) request.getSession().getAttribute("current_user");
		
		if(action != null) {
			
			if(action.equalsIgnoreCase("buy")) {
				// se non e loggato lo portiamo al login
				if(currentUser == null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
					dispatcher.forward(request, response);
				}
				
				
				// jsp pagamento -> l'indirizzo e metodo pagamento
				
				
				// TO - DO creaimo ordine
				OrderBean order = new OrderBean();
				
				// creiamo istanze prodotto
				if (cart != null && cart.getProducts().size() != 0) {
					HashMap<Integer, Integer> carrello = cart.getProducts();
					
					for(Map.Entry<Integer, Integer> entry: carrello.entrySet()){
						int quantita = entry.getValue();
						GeneralProductBean bean;
						try {
							bean = model.doRetrieveByKey(entry.getKey());
							
							for(int i=0; i<quantita; i++) {
								InstanceProductBean istance = new InstanceProductBean(bean, order);
								
								try {
									// TO-DO aggiungere id ordine
									
									if(InstanceModel.doSave(istance)) {
										// decrementiamo quantita disponibile
										model.updateQuantity(bean.getId(), bean.getQuantitaDisponibile() - quantita);
									}
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				}
				
				request.getSession().removeAttribute("cart");
				request.getSession().setAttribute("cart", null);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
				dispatcher.forward(request, response);
				
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
