package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.bean.Cart;
import com.donutrump.model.bean.GeneralProductBean;
import com.donutrump.model.dao.GeneralProductDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();

	public UserServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String action = request.getParameter("action");
		String quantita = request.getParameter("quantita");
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		try {
			
			if (action != null) {
				
				//CARRELLO
				if (action.equalsIgnoreCase("cart")){
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Cart.jsp");
					dispatcher.forward(request, response);
					
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
				
				else if (action.equalsIgnoreCase("delete")){
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

}

