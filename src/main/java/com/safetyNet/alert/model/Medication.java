package com.safetyNet.alert.model;

import javax.persistence.*;

@Entity
public class Medication {


    @Id
    @Column(name = "medications")
   private String medications;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecord medicalRecord;

    public Medication() {
    }


    public void setMedications(String medications) {
        this.medications = medications;
    }

    public Medication(String medications, MedicalRecord medicalRecord) {
        this.medications = medications;
        this.medicalRecord = medicalRecord;
    }



}
