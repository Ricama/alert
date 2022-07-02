package com.safetyNet.alert.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class MedicalRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "birthdate")
    private String birthdate;

    @OneToMany(mappedBy = "medications",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Medication> medications;

    @OneToMany(mappedBy = "allergies",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Allergy> allergies;

    public MedicalRecord() {

    }

    public MedicalRecord(String firstName, String lastName, String birthdate, List<Medication> mediactions, List<Allergy> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.medications = mediactions;
        this.allergies = allergies;
    }



    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Medicalrecords{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", mediactions=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
