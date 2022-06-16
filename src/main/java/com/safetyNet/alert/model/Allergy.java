package com.safetyNet.alert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Allergy {

    @Id
    @Column(name = "name")
    String name;

    public Allergy(String allergies) {
        this.name = allergies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
