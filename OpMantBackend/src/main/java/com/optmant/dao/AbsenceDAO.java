/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.dao;

import com.optmant.entity.Absence;
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
public class AbsenceDAO extends BaseDAO {

    public AbsenceDAO(Connection connectionManager) {
        super(connectionManager);
    }

    public void setAbsence(Absence absence) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = connectionManager.conectar();
            ps = connection.prepareStatement("INSERT INTO absence (nameTeacher, nameReplace, className) VALUES (?, ?, ?)");
            ps.setString(1, absence.getNameTeacher());
            ps.setString(2, absence.getNameReplace());
            ps.setString(3, absence.getNameclass());
            ps.execute();

        } catch (Exception ex) {
            Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }

    public ArrayList<Absence> getLists(String id, String witch) throws Exception {
        java.sql.Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Absence> listValues = new ArrayList<>();
        String st1 = "SELECT * from absence";
        String st2 = "SELECT * from absence where replacement = ?";
        try {
            String selectedQuery = "";
            if (witch.equals("2")) {
                selectedQuery = st2;
            } else {
                selectedQuery = st1;
            }
            connection = connectionManager.conectar();
            ps = connection.prepareStatement(selectedQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                listValues.add(new Absence(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return listValues;
        } catch (Exception ex) {
            Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error while looking out for the list", ex);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AbsenceDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            connectionManager.cerrar(connection);
        }
    }
}
