/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.resteasy;

import com.optmant.dao.Connection;
import com.optmant.dao.FacultyDAO;
import com.optmant.dao.UserDAO;
import com.optmant.entity.Faculty;
import com.optmant.entity.User;
import com.optmant.services.rest.UserServices;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class UserServicesImplement implements UserServices {

    @Override
    public ArrayList<User> getLists(String id) {
        UserDAO ld = new UserDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        ArrayList<User> rs = new ArrayList();
        try {
            rs = ld.getLists(id);
        } catch (Exception ex) {
            Logger.getLogger(UserServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(rs);
        return rs;
    }

    @Override
    public void setEvaluation(User user) {
        UserDAO ev = new UserDAO(new Connection("root", "root", "jdbc:mysql://localhost/opmant"));
        try {
            ev.setUser(user);
        } catch (Exception ex) {
            Logger.getLogger(UserServicesImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
