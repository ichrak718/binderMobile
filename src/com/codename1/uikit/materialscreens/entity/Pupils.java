/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class Pupils {
   private Integer id ;
    private String fullname;
  private String email;
  private String birthday;
  private int classes;
  private int user_id;
    public static FOSUser current_user = new FOSUser(1);
  

    public Pupils() {
    }

    public Pupils(String email) {
        this.email = email;
    }
    
    
    

    public Pupils(int id, String fullname, String email, String birthday, int classes) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.classes = classes;
    }

    public Pupils(String name, String date, String email, int classes) {
        this.fullname=name;
        this.birthday=date;
        this.email=email;
        this.classes=classes;
        
    }

    public Pupils(int id, String fullname,  String birthday,String email, int classes, int user_id) {
        this.id = id;
        this.fullname = fullname;
        this.birthday = birthday;
        this.email = email;
        
        this.classes = classes;
        this.user_id = user_id;
    }

    public Pupils(Integer id, String fullname, String email, String birthday) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
    }

    public Pupils(String fullname, String email, String birthday) {
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
    }
    
    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Pupils{" + "id=" + id + ", fullname=" + fullname + ", email=" + email + ", birthday=" + birthday + ", classes=" + classes + ", user_id=" + user_id + '}';
    }

    

    
    
    
}
