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
public class FacultyDAO extends BaseDAO {

    public FacultyDAO(Connection connectionManager) {
        super(connectionManager);
    }

    public void setFaculty(String name) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.conectar();
            ps = connection.prepareStatement("INSERT INTO faculty (name) VALUES (?)");
            ps.setString(1, name);

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

    public ArrayList<Faculty> getLists(String id) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Faculty> listValues = new ArrayList<>();
        String st1 = "SELECT * from faculty where idfaculty = ?";
        String st2 = "SELECT * from faculty";
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
                listValues.add(new Faculty(rs.getString(1), rs.getString(2)));
            }
            return listValues;
        } catch (Exception ex) {
            Logger.getLogger(FacultyDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FacultyDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }
}
