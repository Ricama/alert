package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.HomePerson;
import com.safetyNet.alert.model.PersonByStation;

import java.util.List;


public interface FirestationDao {
    FireStation create(FireStation firestations);
    FireStation update(FireStation firestations);
    FireStation delete(String fire);

}
