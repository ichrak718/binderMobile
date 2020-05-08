/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

/**
 *
 * @author Asus
 */
public class TimeTable {
    private Integer id;
    private Integer id_class;
    private String pdf;

    public TimeTable() {
    }

    public TimeTable(Integer id, Integer id_class, String pdf) {
        this.id = id;
        this.id_class = id_class;
        this.pdf = pdf;
    }

    public TimeTable(Integer id_class, String pdf) {
        this.id_class = id_class;
        this.pdf = pdf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_class() {
        return id_class;
    }

    public void setId_class(Integer id_class) {
        this.id_class = id_class;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "TimeTable{" + "id=" + id + ", id_class=" + id_class + ", pdf=" + pdf + '}';
    }
    
}
