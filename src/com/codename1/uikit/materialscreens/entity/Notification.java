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
public class Notification {
     private Integer id;
    private String category;

   
    private String sender;
    private String receiver;
    private String content;

    public Notification(Integer id, String category, String sender, String receiver, String content) {
        this.id = id;
        this.category = category;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;    }

    public Notification(String category, String sender, String receiver, String content) {
        this.category = category;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
       
    }

    public Notification() {
    }

    public Notification(String text, String text0, String text1) {
    }

  



  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
 @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", category=" + category + ", sender=" + sender + ", receiver=" + receiver + ", content=" + content + '}';
    }
  
}
