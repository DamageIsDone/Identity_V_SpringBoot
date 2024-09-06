package com.example.mysite.classes;

import jakarta.persistence.*;

@Entity
@Table(name = "I_H")
public class I_H {
    @EmbeddedId
    private I_HKey I_HKey;

    @ManyToOne
    @MapsId("identity_id")
    @JoinColumn(name = "identity_id")
    private Identity identity;

    @ManyToOne
    @MapsId("handheld_id")
    @JoinColumn(name = "handheld_id")
    private Handheld handheld;

    public Handheld getHandheld() {
        return handheld;
    }

    public void setHandheld(Handheld handheld) {
        this.handheld = handheld;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public I_HKey getI_HKey() {
        return I_HKey;
    }

    public void setI_HKey(I_HKey i_HKey) {
        I_HKey = i_HKey;
    }

    @Embeddable
    public static class I_HKey implements java.io.Serializable {
        private Integer identity_id;
        private Integer handheld_id;

        public Integer getHandheld_id() {
            return handheld_id;
        }

        public void setHandheld_id(Integer handheld_id) {
            this.handheld_id = handheld_id;
        }

        public Integer getIdentity_id() {
            return identity_id;
        }

        public void setIdentity_id(Integer identity_id) {
            this.identity_id = identity_id;
        }
    }
}
