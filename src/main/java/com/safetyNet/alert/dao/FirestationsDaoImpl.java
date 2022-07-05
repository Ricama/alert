package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.HomePerson;
import com.safetyNet.alert.repository.FireStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FirestationsDaoImpl implements FirestationDao {

    @Autowired
    FireStationRepository fireStationRepository;

    Logger logger = LoggerFactory.getLogger(FirestationsDaoImpl.class);

    @Override
    public FireStation create(FireStation firestations){
        logger.debug("FirestationsDaoImpl create",firestations);
        return fireStationRepository.save(firestations);
    }

    @Override
    public FireStation update(FireStation firestations){
        FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(firestations.getAddress());
        fireStationToUpdate.setAddress(firestations.getAddress());
        fireStationToUpdate.setStation(firestations.getStation());

        logger.debug("FirestationsDaoImpl update",firestations,fireStationToUpdate);
        return fireStationRepository.save(fireStationToUpdate);
    }

    @Override
    public FireStation delete(String fire){
      if(fire == "1" || fire == "2" || fire == "3" || fire == "4"){
          FireStation fireStationToDelete = fireStationRepository.findByStation(fire);
          fireStationRepository.delete(fireStationToDelete);
          logger.debug("FirestationsDaoImpl create",fireStationToDelete);
      }
      else {
          FireStation fireStationToDelete = fireStationRepository.findFirstByAddress(fire);
          fireStationRepository.delete(fireStationToDelete);
          logger.debug("FirestationsDaoImpl create",fireStationToDelete);
      }

        return null;
    }






}
