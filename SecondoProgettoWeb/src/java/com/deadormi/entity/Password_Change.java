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

    private Integer id;
    private Integer id_user;
    private String password_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getPassword_date() {
        return password_date;
    }

    public void setPassword_date(String password_date) {
        this.password_date = password_date;
    }
    
    
}
