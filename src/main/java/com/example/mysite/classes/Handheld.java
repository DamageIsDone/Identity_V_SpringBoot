package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "Handheld")
public class Handheld {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer handheld_id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 255)
    private String picture;

    // Getters and Setters

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHandheld_id() {
        return handheld_id;
    }

    public void setHandheld_id(Integer handheld_id) {
        this.handheld_id = handheld_id;
    }
}
