/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.entity;

import java.io.Serializable;

/**
 *
 * @author francesco
 */
public class Password_Change implements Serializable {

    private String id;
    private User user;
    private String password_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword_date() {
        return password_date;
    }

    public void setPassword_date(String password_date) {
        this.password_date = password_date;
    }
    
    
}
