package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.*;

import java.util.List;

public interface PersonDao {

    /**
     * save new person in dataBase
     */
    Person create(Person persons);

    /**
     * update person in dataBase
     */
    Person update(Person persons);

    /**
     * delete person in dataBase
     */
    Person delete(String firstName, String lastName);

    /**
     * @return list of person and count of the number of adults and the number of children
     */
    PersonByStationList getPersonByStation(String station);

    /**
     * @return List of childrenand a list of others household members
     */
    ChildByAddress childByAddress(String address);

    /**
     * @return telephone Number list
     */
    List<String> getPhoneByStation(String station);

    /**
     * @return person list
     */
    List<PersonByAddress> personByAddress(String address);

    /**
     * @return household list
     */
    List<Home> getHomeByStation(String station);

    /**
     * @return person list
     */
    PersonInfo personInfo(String firstName, String lastName);

    /**
     * @return email list
     */
    List<String> getEmailByCity(String city);
}
