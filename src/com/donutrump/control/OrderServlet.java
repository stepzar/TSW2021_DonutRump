package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
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

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();
	static AddressDAO addressModel = new AddressDAO(); 
	static InstanceProductDAO InstanceModel = new InstanceProductDAO();
	static OrderDAO orderModel = new OrderDAO();
	static PaymentMethodDAO payModel = new PaymentMethodDAO(); 
       
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
				
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Payment.jsp");
					dispatcher.forward(request, response);

			}
			
			else if(action.equalsIgnoreCase("confirm_buy")) {
				
				OrderBean order = new OrderBean();
				order.setDataConsegna(null);
				order.setDataOrdine(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				int idAddress = Integer.parseInt(request.getParameter("id_indirizzo"));
				System.out.println("Servlet Order Address ID: " + request.getParameter("id_indirizzo"));
				try {
					order.setIndirizzo(addressModel.doRetrieveByKey(idAddress)); // TO-DO bisogna passarlo e farlo scegliere all'utente
				} 
				catch (SQLException e3) {
					e3.printStackTrace();
				}
				
				String numeroCarta = request.getParameter("numero_carta");
				System.out.println(numeroCarta);
				try {
					order.setMetodoPagamento(payModel.doRetrieveByKey(numeroCarta)); // TO-DO bisogna passarlo e farlo scegliere all'utente
				} 
				catch (SQLException e2) {
					e2.printStackTrace();
				}		
				order.setSpeseSpedizione(7);
				order.setStato("ricevuto");
				order.setUtente(currentUser);
				
				double importoTotale = 0;
				int quantitaAcquisto = 0;
				
				if (cart != null && cart.getProducts().size() != 0) {
					HashMap<Integer, Integer> carrello = cart.getProducts();
					
					for(Map.Entry<Integer, Integer> entry: carrello.entrySet()){
						int quantita = entry.getValue();
						GeneralProductBean bean;
						
						try {
							bean = model.doRetrieveByKey(entry.getKey());
							
							importoTotale += bean.getPrezzo() * quantita;
							quantitaAcquisto += quantita;
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				order.setQuantitaAcquisto(quantitaAcquisto);
				order.setImportoTotale(importoTotale);
				
				try {
					orderModel.doSave(order);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				OrderBean ordine;
				try {
					ordine = orderModel.lastUserOrder(currentUser.getId());
					
					// creiamo istanze prodotto
					if (cart != null && cart.getProducts().size() != 0) {
						HashMap<Integer, Integer> carrello = cart.getProducts();
						
						for(Map.Entry<Integer, Integer> entry: carrello.entrySet()){
							int quantita = entry.getValue();
							GeneralProductBean bean;
							
							try {
								bean = model.doRetrieveByKey(entry.getKey());
								
								for(int i=0; i<quantita; i++) {
									InstanceProductBean istance = new InstanceProductBean(bean, ordine);
									System.out.println(istance);
									try {
	
										InstanceModel.doSave(istance);
										
										// decrementiamo quantita disponibile
										model.updateQuantity(bean.getId(), bean.getQuantitaDisponibile() - quantita);
										
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
						}
		
					request.getSession().removeAttribute("cart");
					request.getSession().setAttribute("cart", null);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
					}
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
