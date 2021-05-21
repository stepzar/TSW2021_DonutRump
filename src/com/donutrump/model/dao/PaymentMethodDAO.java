package com.donutrump.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.donutrump.model.bean.AddressBean;
import com.donutrump.model.bean.PaymentMethodBean;
import com.donutrump.model.bean.UserBean;

public class PaymentMethodDAO {
	private static DataSource ds; 

		static {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				ds = (DataSource) envCtx.lookup("jdbc/TSW2021_DonutRump");
			} catch (NamingException e) {
				System.out.println("Error:" + e.getMessage());
				}
			}
		
		private static final String TABLE_NAME = "metodopagamento"; 
		
		public synchronized void doSave (PaymentMethodBean pay) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		
		String insertSQL = "INSERT INTO " + TABLE_NAME
		+ " VALUES (?, ?, ?, ?)";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, pay.getNumeroCarta());
			UserBean user = pay.getUtente(); 
			preparedStatement.setInt(2, user.getId());
			preparedStatement.setDate(3, pay.getScadenza());
			preparedStatement.setString(4, pay.getCvv());
			
			preparedStatement.executeUpdate(); 

		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			
			finally {
				if (connection != null)
					connection.close();
			}
		}



		}

		public synchronized PaymentMethodBean doRetrieveByKey(String id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			PaymentMethodBean bean = new PaymentMethodBean();

			String selectSQL = "SELECT * FROM " + TABLE_NAME + " where numeroCarta = ?"; 
						
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, id);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				while (rs.next()) {
					bean.setNumeroCarta(rs.getString("numeroCarta"));
					
	                UserDAO userModel = new UserDAO();
	                UserBean user = userModel.doRetrieveByKey(rs.getInt("idUtente"));
	                bean.setUtente(user);
	                bean.setScadenza(rs.getDate("scadenza"));
					bean.setCvv(rs.getString("cvv"));
				}
		}finally {
			try {
				if (preparedStatement != null) {
						preparedStatement.close();
				}
				}finally {
					if (connection != null) {
						connection.close();
					}
				}
			}
			return bean;
			}

		
		public synchronized boolean doDelete(String id) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	
			int result = 0;
	
			String deleteSQL = "DELETE * FROM " + TABLE_NAME + "where numeroCarta = ?"; 
	
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, id);
		
				result = preparedStatement.executeUpdate();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return (result != 0);
		}



		public synchronized Collection<PaymentMethodBean> doRetrieveAll(String order) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
		
			Collection<PaymentMethodBean> payments = new LinkedList<PaymentMethodBean>();
		
			String selectSQL = "SELECT * FROM " + TABLE_NAME;
		
			if (order != null && !order.equals("")) { 
				selectSQL += " ORDER BY " + order; 
			}
		
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
			
				ResultSet rs = preparedStatement.executeQuery();
			
				while (rs.next()) {   
					
					PaymentMethodBean bean = new PaymentMethodBean();
					
					bean.setNumeroCarta(rs.getString("numeroCarta"));
					
					UserBean user = new UserBean();
		            user.setId(rs.getInt("idUtente"));
		            UserDAO userModel = new UserDAO();
		            user = userModel.doRetrieveByKey(user.getId());
		            bean.setUtente(user);
		            
		            bean.setScadenza(rs.getDate("scadenza"));
		            bean.setCvv(rs.getString("cvv"));
		             
		            payments.add(bean);
				}
			} 
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					if (connection != null)
						connection.close();
				}
			}
			return payments;
		}
		
		public synchronized ArrayList<PaymentMethodBean> userPayments(int idUtente){
			Connection connection = null;
			PreparedStatement preparedStatement = null;
		
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idUtente = ?";
			

			ArrayList<PaymentMethodBean> pays = new ArrayList<PaymentMethodBean>();
			
			try {
				connection = ds.getConnection();
				
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, idUtente);
				
				ResultSet rs = preparedStatement.executeQuery();
			
				while (rs.next()) {                                     
					PaymentMethodBean bean = new PaymentMethodBean();
					
					bean.setNumeroCarta(rs.getString("numeroCarta"));
					
					UserBean user = new UserBean();
		            user.setId(rs.getInt("idUtente"));
		            UserDAO userModel = new UserDAO();
		            user = userModel.doRetrieveByKey(user.getId());
		            bean.setUtente(user);
		            
		            bean.setScadenza(rs.getDate("scadenza"));
		            bean.setCvv(rs.getString("cvv"));
		             
		            pays.add(bean);
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			if (preparedStatement != null)
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			return pays;
		}
			
			
}
		
	

