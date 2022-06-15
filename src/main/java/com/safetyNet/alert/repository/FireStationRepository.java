package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.FireStation;
import org.springframework.data.repository.CrudRepository;

public interface FireStationRepository extends CrudRepository<FireStation,String> {
    FireStation findByAddress(String address);
    FireStation findByStation(String station);
}
