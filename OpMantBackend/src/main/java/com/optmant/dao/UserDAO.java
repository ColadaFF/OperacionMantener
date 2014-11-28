/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.dao;

import com.optmant.entity.Faculty;
import com.optmant.entity.User;
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
public class UserDAO extends BaseDAO {

    public UserDAO(Connection connectionManager) {
        super(connectionManager);
    }

    public void setUser(User user) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.conectar();
            ps = connection.prepareStatement("INSERT INTO user (name, charge, id_faculty) VALUES (?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getCharge());
            ps.setString(3, user.getId_faculty());

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
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }
    public ArrayList<User> getLists(String id) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> listValues = new ArrayList<>();
        String st1 = "SELECT * from user where iduser = ?";
        String st2 = "SELECT * from user where charge = 'docente'";
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
                listValues.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return listValues;
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking out for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }

}
