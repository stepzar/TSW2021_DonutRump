package com.donutrump.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.donutrump.model.dao.AddressDAO;
import com.donutrump.model.dao.PaymentMethodDAO;
import com.donutrump.model.dao.UserDAO;
import com.donutrump.model.bean.AddressBean;
import com.donutrump.model.bean.PaymentMethodBean;
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
		
		//area utente
		if(action.equalsIgnoreCase("nuovaCartaPagamento")) {
			PaymentMethodDAO paymentModel = new PaymentMethodDAO();
			
			UserBean user = (UserBean) request.getSession().getAttribute("current_user");
			
			String numeroCarta = request.getParameter("numeroCarta");
			String cvv = request.getParameter("cvv");
			String scadenza = request.getParameter("scadenza");
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsed;
			try {
				parsed = format.parse(scadenza);
		        java.sql.Date sql = new java.sql.Date(parsed.getTime());
				PaymentMethodBean bean = new PaymentMethodBean();
				bean.setCvv(cvv);
				bean.setNumeroCarta(numeroCarta);
				bean.setScadenza(sql);
				bean.setUtente(user);
				
				try {
					paymentModel.doSave(bean);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserArea.jsp");
			dispatcher.forward(request, response);

			
		}
		
		if(action.equalsIgnoreCase("nuovoinidirizzo")) {
			AddressDAO addressModel = new AddressDAO();
			UserBean user = (UserBean) request.getSession().getAttribute("current_user");
			
			String via = request.getParameter("via");
			String provincia = request.getParameter("provincia");
			int nCivico = Integer.parseInt(request.getParameter("nCivico"));
			String citta = request.getParameter("citta");
			String cap = request.getParameter("cap");
			
			AddressBean bean = new AddressBean();
			bean.setVia(via);
			bean.setCap(cap);
			bean.setCitta(citta);
			bean.setnCivico(nCivico);
			bean.setProvincia(provincia);
			bean.setUtente(user);
			
			try {
				addressModel.doSave(bean);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserArea.jsp");
			dispatcher.forward(request, response);
			
		}
		
		if(action.equalsIgnoreCase("login")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				UserBean bean = model.verifyUser(email, password); //verifyUser ci va qui
				System.out.println(bean);
				
				if(!bean.getEmail().equals(email) || (bean.getEmail().equals("") || bean.getPswd().equals("")) ) { /*se per qualunque caso nel DB sarà presente un utente con stringhe vuote, esso non sarà preso in considerazione*/
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
