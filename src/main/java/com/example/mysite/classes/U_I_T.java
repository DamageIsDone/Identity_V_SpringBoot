package com.example.mysite.classes;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "U_I_T")
public class U_I_T implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "identity_id")
    private Identity identity;

    @ManyToOne
    @JoinColumn(name = "talent1_id")
    private Talent talent1;

    @ManyToOne
    @JoinColumn(name = "talent2_id")
    private Talent talent2;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Talent getTalent1() {
        return talent1;
    }

    public void setTalent1(Talent talent1) {
        this.talent1 = talent1;
    }

    public Talent getTalent2() {
        return talent2;
    }

    public void setTalent2(Talent talent2) {
        this.talent2 = talent2;
    }
}
