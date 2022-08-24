package com.safetyNet.alert.model;

import java.util.List;

public class HomePerson {
    String lastName;
    String firstName;
    String phone;
    String birthdate;
    List<Medication> medications;
    List<Allergy> allergies;

    public HomePerson(String lastName, String firstName, String phone, String birthdate, List<Medication> medications, List<Allergy> allergies) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }

    public HomePerson() {
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


}
