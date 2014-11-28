/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.resteasy;

import com.optmant.dao.ClassDAO;
import com.optmant.dao.Connection;
import com.optmant.dao.FacultyDAO;
import com.optmant.entity.Faculty;
import com.optmant.services.rest.FacultyServices;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class FacultyServicesImplement implements FacultyServices {

    @Override
    public ArrayList<Faculty> getLists(String id) {
        FacultyDAO ld = new FacultyDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        ArrayList<Faculty> rs = new ArrayList();
        try {
            rs = ld.getLists(id);
        } catch (Exception ex) {
            Logger.getLogger(FacultyServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(rs);
        return rs;
    }

    @Override
    public void setEvaluation(String name) {
        FacultyDAO ev = new FacultyDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        try {
            ev.setFaculty(name);
        } catch (Exception ex) {
            Logger.getLogger(FacultyServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
