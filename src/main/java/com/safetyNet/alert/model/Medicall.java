package com.safetyNet.alert.model;


import java.util.List;


public class Medicall {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    private String birthdate;
    private List<String> medications;
    private List<String> allergies;


}
