package com.safetyNet.alert.model;

import java.util.List;

public class PersonByStationList {

    List<PersonByStation> person;
    int countAdults;
    int countChilds;

    public PersonByStationList(List<PersonByStation> person, int countAdults, int countChilds) {
        this.person = person;
        this.countAdults = countAdults;
        this.countChilds = countChilds;
    }

    public PersonByStationList() {
    }


    public List<PersonByStation> getPerson() {
        return person;
    }

    public void setPerson(List<PersonByStation> person) {
        this.person = person;
    }

    public int getCountAdults() {
        return countAdults;
    }

    public void setCountAdults(int countAdults) {
        this.countAdults = countAdults;
    }

    public int getCountChilds() {
        return countChilds;
    }

    public void setCountChilds(int countChilds) {
        this.countChilds = countChilds;
    }

    @Override
    public String toString() {
        return "PersonByStationList{" +
                "person=" + person +
                ", countAdults='" + countAdults + '\'' +
                ", countChilds='" + countChilds + '\'' +
                '}';
    }
}
