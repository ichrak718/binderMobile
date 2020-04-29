/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.entity;

import java.io.Serializable;
import java.util.List;



/**
 *
 * @author Asus
 */
public class FOSUser implements Serializable{
    
    Integer id;
    String userName;
    String userNameCanonical;
    String email;
    String emailCanonical;
    byte enabled;
    String salt;
    String password;
   // Date  lastLogin;
    String confirmationToken;
   // Date passwordReq;
    String role;
    String valid;
        List<String> roles;

    public FOSUser(Integer id, List<String> roles) {
        this.id = id;
        this.roles = roles;
    }

    


    public FOSUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public FOSUser(String valid) {
        this.valid = valid;
    }

    public FOSUser(Integer id) {
        this.id = id;
    }
    

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNameCanonical() {
        return userNameCanonical;
    }

    public void setUserNameCanonical(String userNameCanonical) {
        this.userNameCanonical = userNameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "FOSUser{" + "id=" + id + ", userName=" + userName + ", userNameCanonical=" + userNameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", confirmationToken=" + confirmationToken + ", role=" + role + '}';
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public FOSUser() {
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    
    
    
    
    
}
