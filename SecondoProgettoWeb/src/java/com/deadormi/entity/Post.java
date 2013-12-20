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
public class Post implements Serializable {

    private Integer id;
    private Integer id_writer;
    private Integer id_crew;
    private String creation_date;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_writer() {
        return id_writer;
    }

    public void setId_writer(Integer id_writer) {
        this.id_writer = id_writer;
    }

    public Integer getId_crew() {
        return id_crew;
    }

    public void setId_crew(Integer id_crew) {
        this.id_crew = id_crew;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
}
