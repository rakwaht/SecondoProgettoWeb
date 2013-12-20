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
public class Crew implements Serializable {

    private Integer id;
    private Integer id_admin;
    private String name;
    private String description;
    private Boolean crew_private;
    private String creation_date;
    private Boolean crew_enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_admin() {
        return id_admin;
    }

    public void setId_admin(Integer id_admin) {
        this.id_admin = id_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCrew_private() {
        return crew_private;
    }

    public void setCrew_private(Boolean crew_private) {
        this.crew_private = crew_private;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public Boolean isCrew_enabled() {
        return crew_enabled;
    }

    public void setCrew_enabled(Boolean crew_enabled) {
        this.crew_enabled = crew_enabled;
    }
    
    
}
