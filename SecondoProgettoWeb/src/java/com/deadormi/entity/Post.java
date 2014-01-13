/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francesco
 */
public class Post implements Serializable {

    private Integer id;
    private User writer;
    private Crew crew;
    private String creation_date;
    private String text;
    private ArrayList<Post_File> files;

    public ArrayList<Post_File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<Post_File> files) {
        this.files = files;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
    
    
}
