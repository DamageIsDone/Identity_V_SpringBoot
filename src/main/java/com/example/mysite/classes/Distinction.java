package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "Distinction")
public class Distinction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer distinction_id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String description;

    // Getters and Setters

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

    public Integer getDistinction_id() {
        return distinction_id;
    }

    public void setDistinction_id(Integer distinction_id) {
        this.distinction_id = distinction_id;
    }
}
