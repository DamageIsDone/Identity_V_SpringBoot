package com.example.mysite.classes;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer game_id;

    @ManyToOne
    @JoinColumn(name = "hunter_id")
    private U_I_T hunter;

    @ManyToOne
    @JoinColumn(name = "survivor1_id")
    private U_I_T survivor1;

    @ManyToOne
    @JoinColumn(name = "survivor2_id")
    private U_I_T survivor2;

    @ManyToOne
    @JoinColumn(name = "survivor3_id")
    private U_I_T survivor3;

    @ManyToOne
    @JoinColumn(name = "survivor4_id")
    private U_I_T survivor4;

    @Column(name = "result1")
    private Boolean result1;

    @Column(name = "result2")
    private Boolean result2;

    @Column(name = "result3")
    private Boolean result3;

    @Column(name = "result4")
    private Boolean result4;

    // Getters and Setters

    public Boolean getResult4() {
        return result4;
    }

    public void setResult4(Boolean result4) {
        this.result4 = result4;
    }

    public Boolean getResult3() {
        return result3;
    }

    public void setResult3(Boolean result3) {
        this.result3 = result3;
    }

    public Boolean getResult2() {
        return result2;
    }

    public void setResult2(Boolean result2) {
        this.result2 = result2;
    }

    public Boolean getResult1() {
        return result1;
    }

    public void setResult1(Boolean result1) {
        this.result1 = result1;
    }

    public U_I_T getSurvivor4() {
        return survivor4;
    }

    public void setSurvivor4(U_I_T survivor4) {
        this.survivor4 = survivor4;
    }

    public U_I_T getSurvivor3() {
        return survivor3;
    }

    public void setSurvivor3(U_I_T survivor3) {
        this.survivor3 = survivor3;
    }

    public U_I_T getSurvivor2() {
        return survivor2;
    }

    public void setSurvivor2(U_I_T survivor2) {
        this.survivor2 = survivor2;
    }

    public U_I_T getSurvivor1() {
        return survivor1;
    }

    public void setSurvivor1(U_I_T survivor1) {
        this.survivor1 = survivor1;
    }

    public U_I_T getHunter() {
        return hunter;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public void setHunter(U_I_T hunter) {
        this.hunter = hunter;
    }

}