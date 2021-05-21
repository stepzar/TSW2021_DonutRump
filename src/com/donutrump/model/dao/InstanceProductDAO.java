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
import com.donutrump.model.bean.InstanceProductBean;



public class InstanceProductDAO {

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

    private static final String TABLE_NAME = "istanzaprodotto";

    public synchronized boolean doSave(InstanceProductBean product) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String insertSQL = "INSERT INTO " + TABLE_NAME +
            " VALUES (?, ?, ?, ?, ?)";
        
        int flag = 0;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setDouble(1, product.getId());
            preparedStatement.setDouble(2, product.getIvaAcquisto());
            preparedStatement.setDouble(3, product.getPrezzoAcquisto());
            preparedStatement.setInt(4, product.getProdottoGenerico().getId());
            preparedStatement.setInt(5, product.getOrdine().getId());

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

    public synchronized InstanceProductBean doRetrieveByKey(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        InstanceProductBean bean = new InstanceProductBean();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + "where id = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setId(rs.getInt("id"));
                bean.setIvaAcquisto(rs.getDouble("ivaAcquisto"));
                bean.setPrezzoAcquisto(rs.getDouble("prezzoAcquisto"));

                GeneralProductBean gp = new GeneralProductBean();
                gp.setId(rs.getInt("prodottoGenerico"));
                GeneralProductDAO gpModel = new GeneralProductDAO();
                gp = gpModel.doRetrieveByKey(gp.getId());
                bean.setProdottoGenerico(gp);

                OrderDAO ordModel = new OrderDAO ();                
                bean.setOrdine(ordModel.doRetrieveByKey(rs.getInt("idOrdine")));
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



    public synchronized Collection < InstanceProductBean > doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection < InstanceProductBean > products = new LinkedList < InstanceProductBean > ();

        String selectSQL = "SELECT * FROM " + TABLE_NAME;

        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                InstanceProductBean bean = new InstanceProductBean();

                bean.setId(rs.getInt("id"));
                bean.setIvaAcquisto(rs.getDouble("ivaAcquisto"));
                bean.setPrezzoAcquisto(rs.getDouble("prezzoAcquisto"));

                GeneralProductBean gp = new GeneralProductBean();
                gp.setId(rs.getInt("prodottoGenerico"));
                GeneralProductDAO gpModel = new GeneralProductDAO(); 
                gp = gpModel.doRetrieveByKey(gp.getId());
                bean.setProdottoGenerico(gp);
               
                OrderDAO ordModel = new OrderDAO ();                
                bean.setOrdine(ordModel.doRetrieveByKey(rs.getInt("idOrdine")));

                products.add(bean);
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
        return products;
    }

}