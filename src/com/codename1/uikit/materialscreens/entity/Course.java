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
public class Course {
    private int id;
    private String subject;
    private String content;
    private String teacher;

    
    public Course() {
    }

    public Course(int id, String subject, String content, String teacher) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.teacher = teacher;
    }

    public Course(String subject, String content, String teacher) {
        this.subject = subject;
        this.content = content;
        this.teacher = teacher;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "subject=" + subject + ", content=" + content + ", teacher=" + teacher + "\n";
    }
    
    
 
    
}

