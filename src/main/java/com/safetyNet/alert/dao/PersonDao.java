package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.ChildByAddress;
import com.safetyNet.alert.model.Person;
import com.safetyNet.alert.model.PersonByAddress;
import com.safetyNet.alert.model.PersonInfo;

public interface PersonDao {
    Person create(Person persons);
    Person update(Person persons);
    Person delete(String firstName, String lastName);
    ChildByAddress childByAddress(String address);
    PersonByAddress personByAddress(String address);
    PersonInfo personInfo(String firstName, String lastName);
    Person getPersonByEmail(String city);
}
