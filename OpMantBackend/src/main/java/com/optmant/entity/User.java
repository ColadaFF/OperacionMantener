/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.entity;

import java.util.Objects;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Cristian
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    String id_user;
    String name;
    String charge;
    String id_faculty;

    public User() {
    }

    public User(String id_user, String name, String charge, String id_faculty) {
        this.id_user = id_user;
        this.name = name;
        this.charge = charge;
        this.id_faculty = id_faculty;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(String id_faculty) {
        this.id_faculty = id_faculty;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id_user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.id_user, other.id_user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", name=" + name + ", charge=" + charge + ", id_faculty=" + id_faculty + '}';
    }

}
