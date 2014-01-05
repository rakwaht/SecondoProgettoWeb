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
    private User receiver;
    private User sender;
    private Crew crew;
    private Boolean invite_enabled;
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public Boolean isInvite_enabled() {
        return invite_enabled;
    }

    public void setInvite_enabled(Boolean invite_enabled) {
        this.invite_enabled = invite_enabled;
    }
    
    
}
