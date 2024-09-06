package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "I_S")
public class I_S {
    @EmbeddedId
    private I_SKey I_SKey;

    @ManyToOne
    @MapsId("identity_id")
    @JoinColumn(name = "identity_id")
    private Identity identity;

    @ManyToOne
    @MapsId("skill_id")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    // Getters and setters

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public I_SKey getI_sKey() {
        return I_SKey;
    }

    public void setI_sKey(I_SKey I_SKey) {
        this.I_SKey = I_SKey;
    }

    @Embeddable
    public static class I_SKey implements java.io.Serializable {
        private Integer identity_id;

        private Integer skill_id;

        // Getters and setters

        public Integer getSkill_id() {
            return skill_id;
        }

        public void setSkill_id(Integer skill_id) {
            this.skill_id = skill_id;
        }

        public Integer getIdentity_id() {
            return identity_id;
        }

        public void setIdentity_id(Integer identity_id) {
            this.identity_id = identity_id;
        }
    }
}
