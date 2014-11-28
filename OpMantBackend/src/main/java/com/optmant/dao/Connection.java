/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.dao;

/**
 *
 * @author Cristian
 */
import java.sql.*;

public class Connection {

    private String user;
    private String pass;
    private String url;

    public Connection(String user, String pass, String url) {
        this.user = user;
        this.pass = pass;
        this.url = url;
    }

    public java.sql.Connection conectar() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            throw new Exception("No se pudo establecer la conexion", e);
        }
    }

    public void cerrar(java.sql.Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.print("No es posible cerrar la Conexion");
        }

    }
}
