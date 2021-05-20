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

import com.donutrump.model.bean.GeneralProductBean;

public class GeneralProductDAO {
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

	private static final String TABLE_NAME = "prodottogenerico"; 
	
	public synchronized void doSave (GeneralProductBean product) throws SQLException {  

	Connection connection = null;
	PreparedStatement preparedStatement = null; 

	
	String insertSQL = "INSERT INTO " + TABLE_NAME
	+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";  

	try {
		connection = ds.getConnection(); 
		preparedStatement = connection.prepareStatement(insertSQL);
		preparedStatement.setInt(1, product.getId());
		preparedStatement.setString(2, product.getNome());
		preparedStatement.setInt(3, product.getQuantitaDisponibile());
		preparedStatement.setDouble(4, product.getPrezzo());
		preparedStatement.setDouble(5, product.getIva());
		preparedStatement.setBoolean(6, product.isDisponibilita());
		preparedStatement.setString(7, product.getDescrizione());
		
		//immagine
		preparedStatement.setNull(8, java.sql.Types.BLOB);
		
		// momentaneamente metto categoria uguale a 1
		preparedStatement.setInt(9, 1);
	
		preparedStatement.executeUpdate(); 
	
		//connection.commit();
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

	public synchronized GeneralProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		GeneralProductBean bean = new GeneralProductBean();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) { 				
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
				bean.setIva(rs.getDouble("iva"));
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



	public synchronized Collection<GeneralProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Collection<GeneralProductBean> products = new LinkedList<GeneralProductBean>();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
	
		if (order != null && !order.equals("")) { 
			selectSQL += " ORDER BY " + order; 
		}
	
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {                                     
				GeneralProductBean bean = new GeneralProductBean();
			
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
				bean.setIva(rs.getDouble("iva"));
				products.add(bean);
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
		return products;
	}

}

