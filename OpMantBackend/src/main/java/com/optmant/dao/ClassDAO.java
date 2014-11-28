/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.dao;

import com.optmant.entity.Class;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class ClassDAO extends BaseDAO {

    public ClassDAO(Connection connectionManager) {
        super(connectionManager);
    }

    public void setClass(Class classes) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.conectar();
            ps = connection.prepareStatement("INSERT INTO class (hour, date, title, classroom, nameTeacher) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, classes.getHour());
            ps.setString(2, classes.getDate());
            ps.setString(3, classes.getTitle());
            ps.setString(4, classes.getClassroom());
            ps.setString(5, classes.getnameTeacher());

            ps.execute();

        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }

    public ArrayList<Class> getLists(String id) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Class> listValues = new ArrayList<>();
        String st1 = "SELECT * from class where idteacher = ?";
        String st2 = "SELECT * from class";
        try {
            String selectedQuery = "";
            if (id == null) {
                selectedQuery = st2;
            } else {
                selectedQuery = st1;
            }
            connection = connectionManager.conectar();
            ps = connection.prepareStatement(selectedQuery);
            if (selectedQuery.equals(st1)) {
                ps.setString(1, id);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                listValues.add(new Class(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return listValues;
        } catch (Exception ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking out for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }

}
