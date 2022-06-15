package com.safetyNet.alert.model;

import java.util.List;

public class ChildByAddress {
    String firstName;
    String lastName;
    String birthdate;
    List<Person> personList;

    public ChildByAddress(String firstName, String lastName, String birthdate, List<Person> personList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.personList = personList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "ChildByAddress{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", personList=" + personList +
                '}';
    }
}
