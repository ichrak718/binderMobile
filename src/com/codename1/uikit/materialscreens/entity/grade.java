package com.codename1.uikit.materialscreens.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Assma
 */
public class grade {
    public int id;
    public int examname;
    public float grade;
    public String teacher;
    public String pupil;
    public int id_pupil; 
    public String subject;
    public Integer  count;
    public Integer count2;

    public int getId_pupil() {
        return id_pupil;
    }

    public void setId_pupil(int id_pupil) {
        this.id_pupil = id_pupil;
    }

    public grade() {
    }

    public grade(int id, int examname, float grade, String teacher, String pupil, int id_pupil) {
        this.id = id;
        this.examname = examname;
        this.grade = grade;
        this.teacher = teacher;
        this.pupil = pupil;
        this.id_pupil = id_pupil;
    }
        public grade(int id, int examname, float grade, String teacher, String pupil, int id_pupil,String subject) {
        this.id = id;
        this.examname = examname;
        this.grade = grade;
        this.teacher = teacher;
        this.pupil = pupil;
        this.id_pupil = id_pupil;
        this.subject=subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamname() {
        return examname;
    }

    public void setExamname(int examname) {
        this.examname = examname;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPupil() {
        return pupil;
    }

    public void setPupil(String pupil) {
        this.pupil = pupil;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount2() {
        return count2;
    }

    public void setCount2(Integer count2) {
        this.count2 = count2;
    }
    

    @Override
    public String toString() {
        return "grade{" + "id=" + id + ", examname=" + examname + ", grade=" + grade + ", teacher=" + teacher + ", pupil=" + pupil + ", id_pupil=" + id_pupil + ", subject=" + subject + '}';
    }
    


  
    
    
}
