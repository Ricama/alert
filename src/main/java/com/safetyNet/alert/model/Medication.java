package com.safetyNet.alert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medication {


    @Id
    @Column(name = "medications")
   private String medications;

    public Medication(String medications) {
        this.medications = medications;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }


}
