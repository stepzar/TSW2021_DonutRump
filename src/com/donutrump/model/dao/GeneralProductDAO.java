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

import com.donutrump.model.bean.CategoryBean;
import com.donutrump.model.bean.GeneralProductBean;

public class GeneralProductDAO {
	
	private static DataSource ds; 
	private CategoryDAO categoryModel = new CategoryDAO(); 

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
		preparedStatement.setString(8, product.getImmagine());
		preparedStatement.setInt(9, product.getCategoria().getId());
	
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
				bean.setCategoria(categoryModel.doRetrieveByKey(rs.getInt("categoria")));
				bean.setImmagine(rs.getString("immagine"));
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
	
	public synchronized ArrayList<GeneralProductBean> doRetrieveAllByKeyword(String keyword) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE nome LIKE " + "'%" + keyword + "%'";
		
		System.out.println(selectSQL);
		
		ArrayList<GeneralProductBean> beans = new ArrayList<GeneralProductBean>();
		
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
				bean.setCategoria(categoryModel.doRetrieveByKey(rs.getInt("categoria")));
				bean.setImmagine(rs.getString("immagine"));
				beans.add(bean);
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
		return beans;
	}
	
	public synchronized ArrayList<GeneralProductBean> doRetrieveAllByCategory(String category) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		CategoryDAO categoryModel = new CategoryDAO();
		int idCategoria = categoryModel.doRetrieveByName(category).getId();
	
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE categoria = ?";
		
		ArrayList<GeneralProductBean> beans = new ArrayList<GeneralProductBean>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, idCategoria);
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next()) {
				GeneralProductBean bean = new GeneralProductBean();
				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getInt("prezzo"));
				bean.setQuantitaDisponibile(rs.getInt("quantita_disponibile"));
				bean.setIva(rs.getDouble("iva"));
				bean.setCategoria(categoryModel.doRetrieveByKey(rs.getInt("categoria")));
				bean.setImmagine(rs.getString("immagine"));
				beans.add(bean);
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
		return beans;
	}

	public synchronized void updateQuantity (int id, int newQuantity) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET quantita_disponibile = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, newQuantity);
			preparedStatement.setInt(2, id);

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
	

	public synchronized void updateName (int id, String newName) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET nome = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);

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
	
	public synchronized void updateDescription (int id, String newDescription) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET descrizione = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, newDescription);
			preparedStatement.setInt(2, id);

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
	
	
	public synchronized void updatePrice (int id, double newPrice) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET prezzo = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDouble(1, newPrice);
			preparedStatement.setInt(2, id);

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
	
	public synchronized void updateIva (int id, double newIva) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET iva = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDouble(1, newIva);
			preparedStatement.setInt(2, id);

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
	
	public synchronized void updateCategory (int id, CategoryBean newCategory) throws SQLException {  

		Connection connection = null;
		PreparedStatement preparedStatement = null; 

		String insertSQL = "UPDATE " + TABLE_NAME + " SET categoria = ? WHERE id = ?";  

		try {
			connection = ds.getConnection(); 
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, newCategory.getId());
			preparedStatement.setInt(2, id);

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
				bean.setImmagine(rs.getString("immagine"));
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

