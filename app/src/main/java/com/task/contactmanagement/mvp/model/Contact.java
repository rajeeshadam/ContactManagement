package com.task.contactmanagement.mvp.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String profile_pic;
    private String last_name;
    private int id;
    private String first_name;
    private String url;
    private boolean favorite=false;

    public String getStart_alphabet() {
        return start_alphabet;
    }

    public void setStart_alphabet(String start_alphabet) {
        this.start_alphabet = start_alphabet;
    }

    private String start_alphabet="";


    public String getProfile_pic() {
        return this.profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name.trim();
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name.trim();
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public boolean getFavourite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}