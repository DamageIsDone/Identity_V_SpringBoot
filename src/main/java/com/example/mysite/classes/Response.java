package com.example.mysite.classes;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "response_date", nullable = false, updatable = false)
    private Timestamp responseDate;

    // Getters and Setters
}