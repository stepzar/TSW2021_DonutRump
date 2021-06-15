package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.bean.CategoryBean;
import com.donutrump.model.dao.CategoryDAO;
import com.donutrump.model.dao.GeneralProductDAO;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static GeneralProductDAO model = new GeneralProductDAO();
	static CategoryDAO categoryModel = new CategoryDAO(); 
    
    public AdminServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		OrderDAO order_model = new OrderDAO();
		UserDAO user_model = new UserDAO();
	   
		try {
			if (action != null) {
				
				if(action.equalsIgnoreCase("search_orders")) {
					String data_da_input = request.getParameter("data_da");
					String data_a_input = request.getParameter("data_a");
					String nome_cliente = request.getParameter("nome");
					String cognome_cliente = request.getParameter("cognome");

					Date data_da = null;
					Date data_a = null;
					
					try {
						if(!data_da_input.equalsIgnoreCase("")) {
							data_da = Date.valueOf(data_da_input);
						}
						else {
							//prendo la data del 1 gennaio 2000
							data_da = Date.valueOf("2000-1-1");
						}
						
						if(!data_a_input.equalsIgnoreCase("")) {
							data_a = Date.valueOf(data_a_input);
						}
						else {
							//prendo la data di oggi
							data_a = new Date(Calendar.getInstance().getTimeInMillis());
						}
						
						System.out.println("Le date sono:");
						System.out.println(data_da);
						System.out.println(data_a);
						
						if(nome_cliente != null && cognome_cliente != null) {
							UserBean cliente = user_model.searchUser(nome_cliente, cognome_cliente);
							request.setAttribute("cliente", cliente);
							
							try {
								ArrayList<OrderBean> orders = order_model.userDateOrders(cliente.getId(), data_da, data_a);
								request.setAttribute("ordini_cliente", orders);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminOrders.jsp");
					dispatcher.forward(request, response);
				}

				if (action.equalsIgnoreCase("mod_product")){
					int id = Integer.parseInt(request.getParameter("id"));
					
					model.updateName(id, request.getParameter("name")); 
					model.updateDescription(id, request.getParameter("description")); 
					model.updateIva(id, Double.parseDouble(request.getParameter("iva"))); 
					model.updatePrice(id, Double.parseDouble(request.getParameter("price"))); 
					model.updateQuantity(id, Integer.parseInt(request.getParameter("quantity")));
					model.updateCategory(id, categoryModel.doRetrieveByName(request.getParameter("category")));
					
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductDetails.jsp");
					dispatcher.forward(request, response);
				} 
				
				
				if (action.equalsIgnoreCase("new_category")){
					String name = request.getParameter("name");
					CategoryBean category = new CategoryBean(); 
					
					category.setNome(name);
					categoryModel.doSave(category);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CategoriesManagement.jsp");
					dispatcher.forward(request, response);
				}
				
				if (action.equalsIgnoreCase("delete_category")){
					int id = Integer.parseInt(request.getParameter("id"));

					categoryModel.doDelete(id);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CategoriesManagement.jsp");
					dispatcher.forward(request, response);
				}
				
				
			} 
	
		} 
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
