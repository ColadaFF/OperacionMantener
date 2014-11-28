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
public class Class {

    String id_class;
    String hour;
    String date;
    String title;
    String classroom;
    String nameTeacher;

    public Class() {
    }

    public Class(String id_class, String hour, String date, String title, String classroom, String id_teacher) {
        this.id_class = id_class;
        this.hour = hour;
        this.date = date;
        this.title = title;
        this.classroom = classroom;
        this.nameTeacher = id_teacher;
    }

    public String getId_class() {
        return id_class;
    }
    @JsonProperty("id_class")
    public void setId_class(String id_class) {
        this.id_class = id_class;
    }

    public String getHour() {
        return hour;
    }
@JsonProperty("hour")
    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }
@JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
@JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassroom() {
        return classroom;
    }
@JsonProperty("classroom")
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getnameTeacher() {
        return nameTeacher;
    }
@JsonProperty("teacher")
    public void setnameTeacher(String id_teacher) {
        this.nameTeacher = id_teacher;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id_class);
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
        final Class other = (Class) obj;
        if (!Objects.equals(this.id_class, other.id_class)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Class{" + "id_class=" + id_class + ", hour=" + hour + ", date=" + date + ", title=" + title + ", classroom=" + classroom + ", nameTeacher=" + nameTeacher + '}';
    }

}
