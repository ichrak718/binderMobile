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
public class activity {
    
    
     public int id;
     public String about;
      public String duration;
       public String location;
       public String clubAC;
        public String dateA;
        public int club_id;

    public activity() {
    }

    public activity(Integer id, String about, String duration, String location, String clubAC, String dateA, Integer club_id) {
        this.id = id;
        this.about = about;
        this.duration = duration;
        this.location = location;
        this.clubAC = clubAC;
        this.dateA = dateA;
        this.club_id = club_id;
    }

    public activity(String about, String duration, String location, String clubAC, String dateA, Integer club_id) {
        this.about = about;
        this.duration = duration;
        this.location = location;
        this.clubAC = clubAC;
        this.dateA = dateA;
        this.club_id = club_id;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClubAC() {
        return clubAC;
    }

    public void setClubAC(String clubAC) {
        this.clubAC = clubAC;
    }

    public String getDateA() {
        return dateA;
    }

    public void setDateA(String dateA) {
        this.dateA = dateA;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    @Override
    public String toString() {
        return "activity{" + "id=" + id + ", about=" + about + ", duration=" + duration + ", location=" + location + ", clubAC=" + clubAC + ", dateA=" + dateA + ", club_id=" + club_id + '}';
    }
        
        
        

    
}
