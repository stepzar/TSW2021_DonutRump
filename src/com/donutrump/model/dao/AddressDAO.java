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

import com.donutrump.model.bean.*;

public class AddressDAO {
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

	private static final String TABLE_NAME = "indirizzo"; 
	
	public synchronized void doSave (AddressBean address) throws SQLException {  

        Connection connection = null;
        PreparedStatement preparedStatement = null; 

        
        String insertSQL = "INSERT INTO " + TABLE_NAME
        + " VALUES (?, ?, ?, ?, ?, ?, ?)";  

        try {
            connection = ds.getConnection(); 
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setInt(2, address.getUtente().getId());
            preparedStatement.setString(3, address.getCap());
            preparedStatement.setInt(4, address.getnCivico());
            preparedStatement.setString(5, address.getVia());
            preparedStatement.setString(6, address.getProvincia());
            preparedStatement.setString(7, address.getCitta());
            
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
	
	public synchronized ArrayList<AddressBean> userAddresses(int idUtente){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idUtente = ?";
		

		ArrayList<AddressBean> addresses = new ArrayList<AddressBean>();
		
		try {
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idUtente);
			
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {                                     
				AddressBean bean = new AddressBean();
			
				bean.setId(rs.getInt("id"));

	            UserDAO userModel = new UserDAO();
	            UserBean user = userModel.doRetrieveByKey(idUtente);
	            bean.setUtente(user);

	            bean.setCap(rs.getString("cap"));
	            bean.setnCivico(rs.getInt("nCivico"));
	            bean.setVia(rs.getString("via"));
	            bean.setProvincia(rs.getString("provincia"));
	            bean.setCitta(rs.getString("citta"));
	            
				addresses.add(bean);
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

		return addresses;
	}
		
	

	public synchronized AddressBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		AddressBean bean = new AddressBean();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) { 				
				bean.setId(rs.getInt("id"));

                UserBean user = new UserBean();
                user.setId(rs.getInt("idUtente"));
                UserDAO userModel = new UserDAO();
                user = userModel.doRetrieveByKey(user.getId());
                bean.setUtente(user);

                bean.setCap(rs.getString("cap"));
                bean.setnCivico(rs.getInt("nCivico"));
                bean.setVia(rs.getString("via"));
                bean.setProvincia(rs.getString("provincia"));
                bean.setCitta(rs.getString("citta"));
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

	public synchronized Collection<AddressBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Collection<AddressBean> addresses = new LinkedList<AddressBean>();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
	
		if (order != null && !order.equals("")) { 
			selectSQL += " ORDER BY " + order; 
		}
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {                                     
				AddressBean bean = new AddressBean();
			
				bean.setId(rs.getInt("id"));

                UserBean user = new UserBean();
                user.setId(rs.getInt("idUtente"));
                UserDAO userModel = new UserDAO();
                user = userModel.doRetrieveByKey(user.getId());
                bean.setUtente(user);

                bean.setCap(rs.getString("cap"));
                bean.setnCivico(rs.getInt("nCivico"));
                bean.setVia(rs.getString("via"));
                bean.setProvincia(rs.getString("provincia"));
                bean.setCitta(rs.getString("citta"));
                
				addresses.add(bean);
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
		return addresses;
	}
}
