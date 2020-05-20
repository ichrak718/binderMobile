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
public class Comment {
    
    private int id;
    private String content;
    private int id_Activity;

    public Comment(String content) {
        this.content = content;
    }

    public Comment() {
    }

    public Comment(int id, String content, int id_Activity) {
        this.id = id;
        this.content = content;
        this.id_Activity = id_Activity;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId_Activity() {
        return id_Activity;
    }

    public void setId_Activity(int id_Activity) {
        this.id_Activity = id_Activity;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + ", id_Activity=" + id_Activity + '}';
    }

    public Comment(String content, int id_Activity) {
        this.content = content;
        this.id_Activity = id_Activity;
    }

   

    
}
