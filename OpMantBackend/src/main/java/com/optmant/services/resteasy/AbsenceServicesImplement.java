/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.resteasy;

import com.optmant.dao.Connection;
import com.optmant.dao.AbsenceDAO;
import com.optmant.entity.Absence;
import com.optmant.services.rest.AbsenceServices;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class AbsenceServicesImplement implements AbsenceServices {

    @Override
    public ArrayList<Absence> getLists(String id, String witch) {
        AbsenceDAO ld = new AbsenceDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        ArrayList<Absence> rs = new ArrayList();
        try {
            rs = ld.getLists(id, witch);
        } catch (Exception ex) {
            Logger.getLogger(AbsenceServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(rs);
        return rs;
    }

    @Override
    public void setAbsence(Absence absence) {
        AbsenceDAO ev = new AbsenceDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        try {
            ev.setAbsence(absence);
        } catch (Exception ex) {
            Logger.getLogger(AbsenceServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
