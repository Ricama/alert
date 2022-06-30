package com.safetyNet.alert.model;

import java.util.List;

public class DataPerson {

    List<Person> persons;
    List<FireStation> firestations;
    List<Medicall> medicalrecords;

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public List<Medicall> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicall> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
