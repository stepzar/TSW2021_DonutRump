package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.dao.UserDAO;
import com.donutrump.model.bean.UserBean;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static UserDAO model = new UserDAO();
	
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				UserBean bean = model.doRetrieveByKey(email, password);
				System.out.println(bean);
				
				if(!bean.getEmail().equals(email)) {
					// non esiste
					System.out.println("Utente Non Trovato");
					request.setAttribute("utente_non_trovato", "true");
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
					dispatcher.forward(request, response);
				}
				else {
					// login avvenuto con successo
					request.getSession().setAttribute("current_user", bean);
					request.setAttribute("utente_non_trovato", "false");
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				System.out.println("Utente Non Trovato, RIPROVARE!");
			}
		}
		else if(action.equalsIgnoreCase("signup")) {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserBean bean = new UserBean();
			bean.setId(0);
			bean.setAdmin(false);
			bean.setNome(nome);
			bean.setCognome(cognome);
			bean.setTelefono(telefono);
			bean.setEmail(email);
			bean.setPswd(password);
			

			try {
				model.doSave(bean);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
		}
		
		else if (action.equalsIgnoreCase("logout")) {
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("current_user");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
