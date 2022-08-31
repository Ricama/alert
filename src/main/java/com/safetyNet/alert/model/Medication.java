package com.safetyNet.alert.model;

import javax.persistence.*;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "medications")
    private String medications;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecord medicalRecord;


    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getMedications() {
        return medications;
    }

    public Medication(String medications) {
        this.medications = medications;
    }


    public Medication() {
    }


    public Medication(String medications, MedicalRecord medicalRecord) {
        this.medications = medications;
        this.medicalRecord = medicalRecord;
    }


}
