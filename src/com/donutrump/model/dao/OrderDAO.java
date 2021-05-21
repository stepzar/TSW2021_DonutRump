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

    public synchronized boolean doSave(OrderBean order) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String insertSQL = "INSERT INTO " + TABLE_NAME +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        int flag = 0;

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
            
            flag = preparedStatement.executeUpdate();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        
        if(flag == 0)
        	return false;
        else
        	return true;
    }

    public synchronized OrderBean doRetrieveByKey(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        UserDAO userModel = new UserDAO(); 
        AddressDAO addressModel = new AddressDAO();

        OrderBean bean = new OrderBean();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + "where id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id"));
                bean.setUtente(userModel.doRetrieveByKey(rs.getInt("idUtente")) );
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("id")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));

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
                bean.setIndirizzo(addressModel.doRetrieveByKey(rs.getInt("id")) );
                bean.setStato(rs.getString("stato"));
                bean.setDataOrdine(rs.getDate("dataOrdine"));
                bean.setImportoTotale(rs.getDouble("importoTotale"));
                bean.setSpeseSpedizione(rs.getDouble("speseSpedizione"));
                bean.setQuantitaAcquisto(rs.getInt("quantitaAcquisto"));
                bean.setDataConsegna(rs.getDate("dataConsegna"));
               

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

