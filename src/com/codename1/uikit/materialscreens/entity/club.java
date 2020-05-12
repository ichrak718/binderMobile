/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

/**
 *
 * @author Sam
 */
public class club {
    
     public int id;
    public String name;
     public String specialty;
      public String responsible;

    public club(String name, String specialty, String responsible) {
        
        this.name = name;
        this.specialty = specialty;
        this.responsible = responsible;    
        
    }

    public club(String string, String string0) {
       
    }

    public club(String httplocalhostmobilegetClubsphp) {
       
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public club() {
    }

    public club(int id, String name, String specialty, String responsible) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.responsible = responsible;
    }

    @Override
    public String toString() {
        return "club{" + "id=" + id + ", name=" + name + ", specialty=" + specialty + ", responsible=" + responsible + '}';
    }
    
    
    
}
