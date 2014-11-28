/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.entity;

import java.util.Objects;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Cristian
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Absence {

    String id_absence;
    String nameclass;
    String nameTeacher;
    String nameReplace;

    public Absence() {
    }

    public Absence(String id_absence, String nameclass, String nameTeacher, String nameReplace) {
        this.id_absence = id_absence;
        this.nameclass = nameclass;
        this.nameTeacher = nameTeacher;
        this.nameReplace = nameReplace;
    }

    public String getId_absence() {
        return id_absence;
    }
@JsonProperty("id_absence")
    public void setId_absence(String id_absence) {
        this.id_absence = id_absence;
    }

    public String getNameclass() {
        return nameclass;
    }
@JsonProperty("nameClass")
    public void setNameclass(String nameclass) {
        this.nameclass = nameclass;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }
@JsonProperty("nameTeacher")
    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getNameReplace() {
        return nameReplace;
    }
@JsonProperty("nameReplace")
    public void setNameReplace(String nameReplace) {
        this.nameReplace = nameReplace;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id_absence);
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
        final Absence other = (Absence) obj;
        if (!Objects.equals(this.id_absence, other.id_absence)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Absence{" + "id_absence=" + id_absence + ", nameclass=" + nameclass + ", nameTeacher=" + nameTeacher + ", nameReplace=" + nameReplace + '}';
    }
    

}
