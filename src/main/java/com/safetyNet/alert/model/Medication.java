package com.safetyNet.alert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medication {


    @Id
    @Column(name = "name")
    String name;

    public Medication(String medications) {
        this.name = medications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
