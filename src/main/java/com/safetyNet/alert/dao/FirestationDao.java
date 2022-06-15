package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.FireStation;


public interface FirestationDao {
    FireStation create(FireStation firestations);
    FireStation update(FireStation firestations);
    FireStation delete(String fire);
}
