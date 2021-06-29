package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.donutrump.model.bean.*;
import com.donutrump.model.dao.*;

@MultipartConfig
public class GeneralProductServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();
	static CategoryDAO categoryModel = new CategoryDAO(); 

	public GeneralProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("Servlet");
		
		String sort = request.getParameter("sort");
		
		String action = request.getParameter("action");
	   
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
				//search e filtri
				if(action.equalsIgnoreCase("searchproduct")) {
					String keyword = request.getParameter("keyword");
					
					ArrayList<GeneralProductBean> beans = model.doRetrieveAllByKeyword(keyword);
					request.setAttribute("resultsearch", beans);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
					dispatcher.forward(request, response);

				}
				if(action.equalsIgnoreCase("filtercategory")) {
					String category = request.getParameter("category");
					
					ArrayList<GeneralProductBean> beans = model.doRetrieveAllByCategory(category);
					request.setAttribute("resultsearch", beans);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
					dispatcher.forward(request, response);
				}
				
				
				//CARRELLO
				//modifica quantit� dei prodotti
				if (quantita > 0 && action.equalsIgnoreCase("cart")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.setQuantity(id, quantita);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
				}
				
				if (quantita == 0 && action.equalsIgnoreCase("cart")){					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
				} 
				
				else if (action.equalsIgnoreCase("addcart")){
					int id = Integer.parseInt(request.getParameter("id"));
					// aggiungi al carrello
					if(model.doRetrieveByKey(id).isDisponibilita())	
						cart.addProduct(id);
				}
				
				else if (action.equalsIgnoreCase("deletefromcart")) {
					int id = Integer.parseInt(request.getParameter("id"));
					// eliminiamo dal carrello
					cart.deleteAllProduct(id);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
				}
				
				
				//CATALOGO
				if (action.equalsIgnoreCase("read")){
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductDetails.jsp");
					dispatcher.forward(request, response);
				} 
				
				else if (action.equalsIgnoreCase("delete")){  //Prima di eliminarlo dal DB, noi dobbiamo eliminarlo dal carrello (se ovviamente c'�)
					
					int id = Integer.parseInt(request.getParameter("id"));
					
					// vedo se c'� nel carrello
					boolean presente = cart.isPresent(id);
					System.out.println(presente); //debug
					
					//elimino dal DB
					model.doDelete(id);
					
					// elimino dal carrello 
					if (presente==true) {
						cart.deleteAllProduct(id);
						request.getSession().removeAttribute("cart");
						request.getSession().setAttribute("cart", cart);
						
					}
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		try {
			request.removeAttribute("catalog");
			request.setAttribute("catalog", model.doRetrieveAll(sort));
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		doGet(request, response);
	}
	
	
}

