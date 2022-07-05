package com.safetyNet.alert.model;

import java.util.List;

public class HomePerson {
    String lastName;
    String firstName;
    String phone;
    String birthdate;
    List<String> medications;
    List<String> allergies;

    public HomePerson( String lastName, String firstName, String phone, String birthdate, List<String> medications, List<String> allergies) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
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

    @Override
    public String toString() {
        return "HomePerson{" +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
