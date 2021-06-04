package com.donutrump.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.donutrump.model.bean.CategoryBean;

import java.util.ArrayList;


public class CategoryDAO {
		
		private static DataSource ds; 

		static {
			try {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				ds = (DataSource) envCtx.lookup("jdbc/TSW2021_DonutRump");
			}catch (NamingException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}

		private static final String TABLE_NAME = "categoria"; 
		
		public synchronized void doSave (CategoryBean category) throws SQLException {  

			Connection connection = null;
			PreparedStatement preparedStatement = null; 
	
			String insertSQL = "INSERT INTO " + TABLE_NAME
			+ " VALUES (?, ?)";  
	
			try {
				connection = ds.getConnection(); 
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, category.getId());
				preparedStatement.setString(2, category.getNome());
				
				preparedStatement.executeUpdate(); 
			}
	
			finally {
					try {
						if (preparedStatement != null) {
							preparedStatement.close();
							}
						} 
					
					finally {
						if (connection != null) {
							connection.close();
							}
						}
				}
		}

		public synchronized CategoryBean doRetrieveByKey(int id) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			CategoryBean bean = new CategoryBean();
	
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " where id = ?"; 
							
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				while (rs.next()) {
					bean.setId(rs.getInt("id"));	
					bean.setNome(rs.getString("nome"));
				}
	
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				}finally {
					if (connection != null)
						connection.close();
				}
			}
			return bean;
		}
		
		public synchronized CategoryBean doRetrieveByName(String name) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			CategoryBean bean = new CategoryBean();
	
			String selectSQL = "SELECT * FROM " + TABLE_NAME + " where nome = ?"; 
							
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, name);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				while (rs.next()) {
					bean.setId(rs.getInt("id"));	
					bean.setNome(rs.getString("nome"));
				}
	
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				}finally {
					if (connection != null)
						connection.close();
				}
			}
			return bean;
		}

		
		public synchronized boolean doDelete(int id) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	
			int result = 0;
	
			String deleteSQL = "DELETE FROM " + TABLE_NAME + " where id = ?"; 
	
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, id);
		
				result = preparedStatement.executeUpdate();
	
			}finally {
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



		public synchronized ArrayList<CategoryBean> doRetrieveAll(String order) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
		
			ArrayList<CategoryBean> categories = new ArrayList<CategoryBean>();
		
			String selectSQL = "SELECT * FROM " + TABLE_NAME;
		
			if (order != null && !order.equals("")) { 
				selectSQL += " ORDER BY " + order; 
			}
		
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
			
				ResultSet rs = preparedStatement.executeQuery();
			
				while (rs.next()) {                                     
					CategoryBean bean = new CategoryBean();
					
					bean.setId(rs.getInt("id"));
					bean.setNome(rs.getString("nome"));
					
					categories.add(bean);
				}
			} 
			finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				}
				finally {
					if (connection != null) {
					connection.close();
					}
				}
			}
			return categories;
		}
}


