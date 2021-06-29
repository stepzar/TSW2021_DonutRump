package com.donutrump.model.dao;

import java.sql.Connection;
import java.sql.Date;
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

import com.donutrump.model.bean.OrderBean;

public class OrderDAO {
    
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

    private static final String TABLE_NAME = "ordine";

    public synchronized void doSave(OrderBean order) throws SQLException {
    	// restitutisce l'id
    	
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String insertSQL = "INSERT INTO " + TABLE_NAME +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setDouble(1, order.getId());
            preparedStatement.setInt(2, order.getUtente().getId());
            preparedStatement.setInt(3, order.getIndirizzo().getId());
            preparedStatement.setString(4, order.getStato());
            preparedStatement.setDate(5,order.getDataOrdine()); 
            preparedStatement.setDouble(6, order.getImportoTotale());
            preparedStatement.setDouble(7, order.getSpeseSpedizione());
            preparedStatement.setInt(8, order.getQuantitaAcquisto());
            preparedStatement.setDate(9, order.getDataConsegna());
            preparedStatement.setString(10, order.getMetodoPagamento().getNumeroCarta());
            
            preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    public synchronized OrderBean doRetrieveByKey(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        OrderBean bean = new OrderBean();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " where id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return bean;
    }
    
    public synchronized OrderBean lastUserOrder(int idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        OrderBean bean = new OrderBean();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " where idUtente = ? ORDER BY id DESC LIMIT 1";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return bean;
    }
    
    public synchronized ArrayList<OrderBean> userOrders(int idUtente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " where idUtente = ? ORDER BY id DESC";
        
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        
        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	OrderBean bean = new OrderBean();
            	
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
                
                orders.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return orders;
    }
    
    public synchronized ArrayList<OrderBean> userDateOrders(int idUtente, String data_da, String data_a) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " where idUtente = ? and dataOrdine >= ? and dataOrdine <= ? ORDER BY id DESC";
        
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        
        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idUtente);
            preparedStatement.setString(2, data_da);
            preparedStatement.setString(3, data_a);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("fatto query");

            while (rs.next()) {
            	OrderBean bean = new OrderBean();
            	
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
                
                orders.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        
        for(OrderBean order : orders){
        	System.out.println("Ordine:");
        	System.out.println(order.getId());
        }
        
        return orders;
    }
    
    public synchronized ArrayList<OrderBean> DateOrders(String data_da, String data_a) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " where dataOrdine >= ? and dataOrdine <= ? ORDER BY id DESC";
        
        ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
        
        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, data_da);
            preparedStatement.setString(2, data_a);
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	OrderBean bean = new OrderBean();
            	
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
                
                orders.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        
        for(OrderBean order : orders){
        	System.out.println("Ordine:");
        	System.out.println(order.getId());
        }
        
        return orders;
    }



    public synchronized boolean doDelete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE * FROM " + TABLE_NAME + "where id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);

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



    public synchronized Collection < OrderBean > doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection < OrderBean > orders = new LinkedList < OrderBean > ();
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();
        PaymentMethodDAO paymentModel = new PaymentMethodDAO();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OrderBean bean = new OrderBean();
                
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("idIndirizzo")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
                bean.setMetodoPagamento(paymentModel.doRetrieveByKey(rs.getString("metodoPagamento")));
               

                orders.add(bean);
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return orders;
    }
}

