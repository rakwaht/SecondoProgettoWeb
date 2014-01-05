/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.entity;

import java.io.Serializable;

/**
 *
 * @author Timbu
 */
public class Post_File implements Serializable {

    
    private Integer id;
    private Integer id_post;
    private String name;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
