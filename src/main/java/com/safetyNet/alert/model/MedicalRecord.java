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

    @Column(name = "medications")
    private List<String> medications;

    @Column(name = "allergies")
    private List<String> allergies;

    public MedicalRecord() {

    }

    public MedicalRecord(String firstName, String lastName, String birthdate, List<String> mediactions, List<String> allergies) {
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

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
