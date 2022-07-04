package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.*;

import java.util.List;

public interface PersonDao {
    Person create(Person persons);
    Person update(Person persons);
    Person delete(String firstName, String lastName);
    PersonByStationList getPersonByStation(String station);
    ChildByAddress childByAddress(String address);
    PersonByAddress personByAddress(String address);
    PersonInfo personInfo(String firstName, String lastName);
    Person getPersonByEmail(String city);
}
