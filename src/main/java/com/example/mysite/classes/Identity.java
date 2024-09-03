package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identity_id;

    private String career;
    private String name;
    private String camp;
    private String gender;
    private String birthday;

    private String picture;

    // Getters and setters

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getIdentity_id() {
        return identity_id;
    }

    public void setIdentity_id(Integer identity_id) {
        this.identity_id = identity_id;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}