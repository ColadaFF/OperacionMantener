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
public class Faculty {

    String id_faculty;
    String name_faculty;

    public Faculty() {
    }

    public Faculty(String id_faculty, String name_faculty) {
        this.id_faculty = id_faculty;
        this.name_faculty = name_faculty;
    }

    public String getId_faculty() {
        return id_faculty;
    }

    public void setId_faculty(String id_faculty) {
        this.id_faculty = id_faculty;
    }

    public String getName_faculty() {
        return name_faculty;
    }

    public void setName_faculty(String name_faculty) {
        this.name_faculty = name_faculty;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id_faculty);
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
        final Faculty other = (Faculty) obj;
        if (!Objects.equals(this.id_faculty, other.id_faculty)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Faculty{" + "id_faculty=" + id_faculty + ", name_faculty=" + name_faculty + '}';
    }

}
