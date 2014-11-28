/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.resteasy;

import com.optmant.dao.ClassDAO;
import com.optmant.dao.Connection;
import com.optmant.entity.Class;
import com.optmant.services.rest.ClassServices;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class ClassServicesImplement implements ClassServices {

    @Override
    public ArrayList<Class> getLists(String id) {
        ClassDAO ld = new ClassDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        ArrayList<Class> rs = new ArrayList();
        try {
            rs = ld.getLists(id);
        } catch (Exception ex) {
            Logger.getLogger(ClassServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(rs);
        return rs;
    }

    @Override
    public void setClass(Class classes) {
        ClassDAO ev = new ClassDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        try {
            ev.setClass(classes);
        } catch (Exception ex) {
            Logger.getLogger(ClassServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
