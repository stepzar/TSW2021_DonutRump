package com.donutrump.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.donutrump.model.bean.UserBean;

public class UserDAO {
	private static DataSource ds; 

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/TSW2021_DonutRump");
		} 
		catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "utente"; 
	
	public synchronized void doSave (UserBean user) throws SQLException {  

	Connection connection = null;
	PreparedStatement preparedStatement = null; 

	
	String insertSQL = "INSERT INTO " + TABLE_NAME
	+ " VALUES (?, ?, ?, ?, ?, ?, ?)";  

	try {
		connection = ds.getConnection(); 
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getNome());
	    preparedStatement.setString(3, user.getCognome());
	    preparedStatement.setString(4, user.getEmail());
	    preparedStatement.setBoolean(5, user.isAdmin());
	    preparedStatement.setString(6, user.getTelefono());
	    preparedStatement.setString(7, user.getPswd());
	    
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

	public synchronized UserBean doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		UserBean bean = new UserBean();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) { 				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setPswd(rs.getString("pswd"));
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
		return bean;
	}
	
	
	public synchronized UserBean verifyUser (String email, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		UserBean bean = new UserBean();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? and pswd = ?";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) { 				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setPswd(rs.getString("pswd"));
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
		return bean;
	}
	
	public synchronized UserBean searchUser (String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		UserBean bean = new UserBean();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome = ? and cognome = ? LIMIT 1";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);
			
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) { 				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setPswd(rs.getString("pswd"));
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
		return bean;
	}
	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		int result = 0;
	
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
		
			result = preparedStatement.executeUpdate();
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
		return (result != 0);
	}

	public synchronized Collection<UserBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Collection<UserBean> users = new LinkedList<UserBean>();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
	
		if (order != null && !order.equals("")) { 
			selectSQL += " ORDER BY " + order; 
		}
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {                                     
				UserBean bean = new UserBean();
			
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
                bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
                bean.setTelefono(rs.getString("telefono"));
                bean.setPswd(rs.getString("pswd"));
				users.add(bean);
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
		return users;
	}
}
