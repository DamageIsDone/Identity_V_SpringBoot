package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "Talent")
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer talent_id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 255)
    private String effect;

    @Column(nullable = false, length = 50)
    private String camp;

    @Column(nullable = false, length = 255)
    private String picture;

    // Getters and Setters

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
    public Integer getTalent_id() {
        return talent_id;
    }

    public void setTalent_id(Integer talent_id) {
        this.talent_id = talent_id;
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

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }
}
