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
public class Abscense {


    private Integer id;
    private String pupl;
    private String subject;
    private String nb;
    private String dateab;

    public Abscense(Integer id, String text, String text0, String text1, String text2, String text3, String text4) {
    }

    public Abscense(String text, String text0, String text1, String text2, String text3, String text4, String text5) {
    }

    public Abscense(String string, String string0) {
    }

    public Abscense(Integer id, String text, String text0) {
    }

    public String getDateab() {
        return dateab;
    }

    public void setDateab(String Dateab) {
        this.dateab = dateab;
    }

    public Abscense(Integer id, String pupl, String subject, String nb, String Dateab, String groupe, String hour,  String dateAb) {
        this.id = id;
        this.pupl = pupl;
        this.subject = subject;
        this.nb = nb;
        this.dateab = dateab;
        this.groupe = groupe;
        this.hour = hour;
      //  this.pupil_id = pupil_id;
        this.dateAb = dateAb;
    }
   // private Date date;
    private String groupe;
    private String hour;
      // public int pupil_id;

 

    public String getDateAb() {
        return dateAb;
    }

    public void setDateAb(String dateAb) {
        this.dateAb = dateAb;
    }
     public String dateAb;

    public Abscense() {
    }

    public Abscense(Integer id, String pupl, String subject, String nb, String groupe, String hour) {
        this.id = id;
        this.pupl = pupl;
        this.subject = subject;
        this.nb = nb;
        this.groupe = groupe;
        this.hour = hour;
//        this.pupil_id = pupil_id;
    }

   // public Abscense(Integer id, String text, String text0, String text1, String text2, String text3) {
  //  }

   // public int getPupil_id() {
//        return pupil_id;
 //   }

    public Abscense(String pupl, String subject, String groupe, String hour, String nb) {
        this.pupl = pupl;
        this.subject = subject;
        this.groupe = groupe;
        this.hour = hour;
        this.nb = nb;
    }

    public Abscense(int parseInt, String string, String string0, String string1, String string2, String string3, int parseInt0) {
    }

 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPupl() {
        return pupl;
    }

    public void setPupl(String pupl) {
        this.pupl = pupl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNb() {
        return nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    } 

    @Override
    public String toString() {
        return "Abscense{" + "id=" + id + ", pupl=" + pupl + ", subject=" + subject + ", nb=" + nb + ", groupe=" + groupe + ", hour=" + hour + '}';
    }

   // public void setPupil_id(int parseInt) {
  //  }
    
}

  