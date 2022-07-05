package com.safetyNet.alert.model;

import java.util.List;

public class PersonInfo {

    String lastName;
    String birthdate;
    String email;
    List<Medication> medications;
    List<Allergy> allergies;


    public PersonInfo(String lastName, String birthdate, String email, List<Medication> medications, List<Allergy> allergies) {
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "PersonInfo{" +
                "lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }

}
