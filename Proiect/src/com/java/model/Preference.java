package com.java.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "Preferences")
public class Preference implements Serializable {

    @EmbeddedId
    private PreferenceCompoundPk id;

    @Column(name = "NUMNBER_PREF")
    public int noPref;

    public PreferenceCompoundPk getId() {
        return id;
    }

    public void setId(PreferenceCompoundPk id) {
        this.id = id;
    }

    public int getNoPref() {
        return noPref;
    }

    public void setNoPref(int noPref) {
        this.noPref = noPref;
    }
}
