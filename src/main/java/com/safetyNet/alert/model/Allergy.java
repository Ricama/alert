package com.safetyNet.alert.model;

import javax.persistence.*;

@Entity
public class Allergy {



    @Id
    @Column(name = "allergies")
   private String allergies;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecord medicalRecord;

    public Allergy(String allergies, MedicalRecord medicalRecord) {
        this.allergies = allergies;
        this.medicalRecord = medicalRecord;
    }

    public Allergy() {
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
