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
public class Crew_User implements Serializable {
    private Integer id_user;
    private Integer id_crew;
    private Boolean crew_user_enabled;

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_crew() {
        return id_crew;
    }

    public void setId_crew(Integer id_crew) {
        this.id_crew = id_crew;
    }

    public Boolean isCrew_user_enabled() {
        return crew_user_enabled;
    }

    public void setCrew_user_enabled(Boolean crew_user_enabled) {
        this.crew_user_enabled = crew_user_enabled;
    }
    
}
