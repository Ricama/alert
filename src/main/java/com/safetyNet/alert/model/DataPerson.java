package com.safetyNet.alert.model;

import java.util.List;

public class DataPerson {

    List<Person> persons;
    List<FireStation> firestations;
    List<Medicall> medicalrecords;

    public List<Person> getPersons() {
        return persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public List<Medicall> getMedicalrecords() {
        return medicalrecords;
    }
}
