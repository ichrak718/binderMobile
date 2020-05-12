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
public class participation {
    
    public int id;
    public String name;
    public String classe;
    public String club;

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public participation(int id, String name, String classe, String club) {
        this.id = id;
        this.name = name;
        this.classe = classe;
        this.club = club;
    }

    public participation(String name, String classe, String club) {
        this.name = name;
        this.classe = classe;
        this.club = club;
    }

    
    public participation() {
    }

    @Override
    public String toString() {
        return "participation{" + "id=" + id + ", name=" + name + ", classe=" + classe + ", club=" + club + '}';
    }

     public String message() {
        return "the participant " + "named " + name + ", from classe " + classe + ",would like to participate to club " + club;
    }
    
    
}
