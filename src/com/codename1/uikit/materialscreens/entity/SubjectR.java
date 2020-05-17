/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

/**
 *
 * @author Rahma
 */
public class SubjectR {
     private Integer id;
    private String noms;
     private String nombre;

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    @Override
    public String toString() {
        return "SubjectR{" + "id=" + id + ", noms=" + noms + ", nombre=" + nombre + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SubjectR(Integer id, String noms, String nombre) {
        this.id = id;
        this.noms = noms;
        this.nombre = nombre;
    }
   
  
      public SubjectR( String noms, String nombre) {
      
        this.noms = noms;
        this.nombre = nombre;
    }
    
      public SubjectR( ) {
      
       
    }
}
