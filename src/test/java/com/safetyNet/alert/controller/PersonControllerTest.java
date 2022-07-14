package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.Person;
import com.safetyNet.alert.model.PersonByStation;
import com.safetyNet.alert.model.PersonByStationList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest {

    @Test
    void postNewPersonTest() {

    }

    @Test
    void putPersonTest() {
    }

    @Test
    void deletePersonTest() {
    }

    @Test
    void getPersonByStationTest() {
        List<PersonByStation> personTestList = new ArrayList<>();
        PersonByStation personTest = new PersonByStation("Lily","Cooper","489 Manchester St","841-874-9845");
        personTestList.add(personTest);
        PersonByStationList personByStationListTest = new PersonByStationList(personTestList,0,1);


    }

    @Test
    void childByAddressTest() {
    }

    @Test
    void getPhoneByStationTest() {
    }

    @Test
    void personByAddressTest() {
    }

    @Test
    void getHomeByStationTest() {
    }

    @Test
    void personInfoTest() {
    }

    @Test
    void getPersonByEmailTest() {
    }
}