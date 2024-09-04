package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
public class I_D {
    @EmbeddedId
    private I_DKey I_DKey;

    @ManyToOne
    @MapsId("identity_id")
    @JoinColumn(name = "identity_id")
    private Identity identity;

    @ManyToOne
    @MapsId("distinction_id")
    @JoinColumn(name = "distinction_id")
    private Distinction distinction;

    // Getters and setters

    public Distinction getDistinction() {
        return distinction;
    }

    public void setDistinction(Distinction distinction) {
        this.distinction = distinction;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public I_DKey getI_dKey() {
        return I_DKey;
    }

    public void setI_dKey(I_DKey I_DKey) {
        this.I_DKey = I_DKey;
    }

    @Embeddable
    public static class I_DKey implements java.io.Serializable {
        private Integer identity_id;
        private Integer distinction_id;

        // Getters, setters, equals, and hashCode methods

        public Integer getDistinction_id() {
            return distinction_id;
        }

        public void setDistinction_id(Integer distinction_id) {
            this.distinction_id = distinction_id;
        }

        public Integer getIdentity_id() {
            return identity_id;
        }

        public void setIdentity_id(Integer identity_id) {
            this.identity_id = identity_id;
        }
    }
}
