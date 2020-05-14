/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

/**
 *
 * @author wael
 */
public class Subject {
    private int id;
    private String name;
    private String teachers;
    private String classes;

    
    public Subject() {
    }

    public Subject(int id, String name, String teachers, String classes) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
    
    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "name=" + name + ", teachers=" + teachers + ", classes=" + classes + "\n";
    }
    
    
 
    

}
