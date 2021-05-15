package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.bean.*;
import com.donutrump.model.dao.*;

public class GeneralProductServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();

	public GeneralProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		String cancellaDalCarrello = request.getParameter("deleteFromCart");
	   
		String quantitaS = request.getParameter("quantity");
	    int quantita = 0; 
	    if (quantitaS!=null) {
	    	quantita = Integer.parseInt(quantitaS);
	    	}
	   
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		
		try {
			
			
			
			
			if (action != null) {
				
				//CARRELLO
				//modifica quantità dei prodotti
				if (quantita != 0 && action.equalsIgnoreCase("cart")) {
						
					int id = Integer.parseInt(request.getParameter("id"));
					int quantitaVecchia = Integer.parseInt(request.getParameter("oldQuantity"));
					if (quantita >= quantitaVecchia) {
						for (int i=quantitaVecchia; i<quantita; i++) cart.addProduct(model.doRetrieveByKey(id));
					}
					else {
						for (int i=quantita; i<quantitaVecchia; i++) cart.deleteProduct(model.doRetrieveByKey(id));
					}
				}
				
				
				if (action.equalsIgnoreCase("cart")){
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
					
					if (cancellaDalCarrello.equalsIgnoreCase("true")) { //cancella il prodotto dal carrello, indipendentemente dalle quantità
						//cancella dal carrello leggendo l'id
						int id = Integer.parseInt(request.getParameter("id"));
						int quantitaDaCancellare = Integer.parseInt(request.getParameter("oldQuantity"));
						for (int i=0; i<quantitaDaCancellare; i++) cart.deleteProduct(model.doRetrieveByKey(id));
						
						return; //Il return ci riporta immmediatamente al punto della jsp deov'eravamo prima, e quest'ultima andrà ad eseguire il sendRedirect 
					}
					
						
				} 
				
				else if (action.equalsIgnoreCase("addcart")){
					int id = Integer.parseInt(request.getParameter("id"));
					
					// aggiungi al carrello
					GeneralProductBean gp = model.doRetrieveByKey(id);
					cart.addProduct(gp);
				}
				
				
				//CATALOGO
				if (action.equalsIgnoreCase("read")){
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductDetails.jsp");
					dispatcher.forward(request, response);
				} 
				
				else if (action.equalsIgnoreCase("delete")){  //Prima di eliminarlo dal DB, noi dobbiamo eliminarlo dal carrello (se ovviamente c'è)
					
					int id = Integer.parseInt(request.getParameter("id"));
					
					// vedo se c'è nel carrello
					boolean presente = cart.isPresent(model.doRetrieveByKey(id));
					System.out.println(presente); //debug
					
					//elimino dal DB
					model.doDelete(id);
					
					// elimino dal carrello carrello
					if (presente==true) {
						cart.deleteAllProduct(model.doRetrieveByKey(id));
						request.getSession().removeAttribute("cart");
						request.getSession().setAttribute("cart", cart);
						
					}
				} 
				
				else if (action.equalsIgnoreCase("insert")){
					
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					int price = Integer.parseInt(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					double iva = Double.parseDouble(request.getParameter("iva"));

					GeneralProductBean bean = new GeneralProductBean();
					
					bean.setNome(name);
					bean.setDescrizione(description);
					bean.setPrezzo(price);
					bean.setQuantitaDisponibile(quantity);
					bean.setIva(iva);
						
					model.doSave(bean);
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String sort = request.getParameter("sort");
		
		
		try {
			request.removeAttribute("catalog");
			request.setAttribute("catalog", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	
}

