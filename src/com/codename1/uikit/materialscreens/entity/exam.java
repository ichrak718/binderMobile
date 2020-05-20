/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

/**
 *
 * @author Assma
 */
public class exam {
    public int id;
    public String subject;
    public String file;
    public int duration;
    public String date;

    public exam() {
    }

    public exam(int id, String subject, String file, int duration, String date) {
        this.id = id;
        this.subject = subject;
        this.file = file;
        this.duration = duration;
        this.date = date;
    }

    public exam(String subject) {
        this.subject = subject;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "exam{" + "id=" + id + ", subject=" + subject + ", file=" + file + ", duration=" + duration + ", date=" + date + '}';
    }

        
    
}
