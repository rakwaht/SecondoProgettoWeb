/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.entitydto;

import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import com.deadormi.entity.User;
import java.util.ArrayList;

/**
 *
 * @author Davide
 */
public class ModeratorDto {
    
    private Crew crew;
    private ArrayList <Post> posts;
    private ArrayList <User> users;

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
}
