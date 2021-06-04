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
	   
		try {
			if (action != null) {				
				
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
