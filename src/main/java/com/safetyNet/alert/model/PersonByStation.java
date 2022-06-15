package com.safetyNet.alert.model;

public class PersonByStation {
    String station;
    String firstName;
    String lastName;
    String address;
    String phone;
    String countAdult;
    String countChild;

    public PersonByStation(String station, String firstName, String lastName, String address, String phone, String countAdult, String countChild) {
        this.station = station;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.countAdult = countAdult;
        this.countChild = countChild;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountAdult() {
        return countAdult;
    }

    public void setCountAdult(String countAdult) {
        this.countAdult = countAdult;
    }

    public String getCountChild() {
        return countChild;
    }

    public void setCountChild(String countChild) {
        this.countChild = countChild;
    }

    @Override
    public String toString() {
        return "PersonByStation{" +
                "station='" + station + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", countAdult='" + countAdult + '\'' +
                ", countChild='" + countChild + '\'' +
                '}';
    }
}
