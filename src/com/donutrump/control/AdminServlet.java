package com.donutrump.control;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.donutrump.model.bean.CategoryBean;
import com.donutrump.model.bean.GeneralProductBean;
import com.donutrump.model.bean.OrderBean;
import com.donutrump.model.bean.UserBean;
import com.donutrump.model.dao.CategoryDAO;
import com.donutrump.model.dao.GeneralProductDAO;
import com.donutrump.model.dao.OrderDAO;
import com.donutrump.model.dao.UserDAO;

@MultipartConfig
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
		String sort = request.getParameter("sort");
		OrderDAO order_model = new OrderDAO();
		UserDAO user_model = new UserDAO();
	   
		try {
			if (action != null) {
				
				if(action.equalsIgnoreCase("search_orders")) {
					String data_da_input = request.getParameter("data_da");
					String data_a_input = request.getParameter("data_a");
					String nome_cliente = request.getParameter("nome");
					String cognome_cliente = request.getParameter("cognome");

					String data_da = null;
					String data_a = null;
					
					try {
						if(!data_da_input.equalsIgnoreCase("")) {
							data_da = data_da_input;
						}
						else {
							//prendo la data del 1 gennaio 2000
							data_da = "2000-1-1";
						}
						
						if(!data_a_input.equalsIgnoreCase("")) {
							data_a = data_a_input;
						}
						else {
							//prendo la data di oggi e la converto in stringa
							data_a = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
						}
						
						if(nome_cliente != null && cognome_cliente != null) {
							UserBean cliente = user_model.searchUser(nome_cliente, cognome_cliente);
							request.setAttribute("cliente", cliente);
							
							try {
								ArrayList<OrderBean> orders = order_model.userDateOrders(cliente.getId(), data_da_input, data_a_input);
								request.setAttribute("ordini_cliente", orders);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
						if((nome_cliente == null || nome_cliente.equals("")) && (cognome_cliente == null || cognome_cliente.equals(""))) {
							try {
								ArrayList<OrderBean> orders = order_model.DateOrders(data_da, data_a);
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
				
				if (action.equalsIgnoreCase("insert")){
					
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					double iva = Double.parseDouble(request.getParameter("iva"));
					
					//immagine
					Part file = request.getPart("image");
					String uploadPath = "C:/Users/Step/eclipse-workspace/TSW2021_DonutRump/WebContent/images/products/" + file.getSubmittedFileName();
					
					CategoryBean category;
					try {
						category = categoryModel.doRetrieveByName(request.getParameter("category"));
						
						GeneralProductBean bean = new GeneralProductBean(category);
						
						bean.setNome(name);
						bean.setDescrizione(description);
						bean.setPrezzo(price);
						bean.setQuantitaDisponibile(quantity);
						bean.setIva(iva);
						bean.setImmagine(uploadPath);
							
						try {
							model.doSave(bean);
							
							// salvo immagine in images/products
							FileOutputStream fos = new FileOutputStream(uploadPath);
							InputStream is = file.getInputStream();
							byte[] data = new byte[is.available()];
							is.read(data);
							fos.write(data);
							fos.close();
							
						} catch (SQLException e) {
							System.out.println("Errore di save GeneralProductServlet insert product");
							e.printStackTrace();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
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
