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
public class Invite implements Serializable {

    private Integer id;
    private Integer id_receiver;
    private Integer id_sender;
    private Integer id_crew;
    private Boolean invite_enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(Integer id_receiver) {
        this.id_receiver = id_receiver;
    }

    public Integer getId_sender() {
        return id_sender;
    }

    public void setId_sender(Integer id_sender) {
        this.id_sender = id_sender;
    }

    public Integer getId_crew() {
        return id_crew;
    }

    public void setId_crew(Integer id_crew) {
        this.id_crew = id_crew;
    }

    public Boolean isInvite_enabled() {
        return invite_enabled;
    }

    public void setInvite_enabled(Boolean invite_enabled) {
        this.invite_enabled = invite_enabled;
    }
    
    
}
