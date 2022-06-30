package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.FireStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation,String> {
    FireStation findFirstByAddress(String address);
    FireStation findByStation(String station);
}
