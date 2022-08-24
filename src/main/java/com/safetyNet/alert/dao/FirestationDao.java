package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.HomePerson;
import com.safetyNet.alert.model.PersonByStation;

import java.util.List;


public interface FirestationDao {

    /**
     * save new fireStation in dataBase
     */
    FireStation create(FireStation firestations);

    /**
     * update fireStation
     */
    FireStation update(FireStation firestations);

    /**
     * @param fire = address or station
     * Tcheck if fire are a address or a station
     */
    FireStation delete(String fire);

}
