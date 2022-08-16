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

    public FirestationsDaoImpl(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    @Override
    public FireStation create(FireStation firestation) {
        logger.debug("FirestationsDaoImpl create", firestation);
        return fireStationRepository.save(firestation);
    }

    @Override
    public FireStation update(FireStation firestation) {
        FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(firestation.getAddress());
        fireStationToUpdate.setAddress(firestation.getAddress());
        fireStationToUpdate.setStation(firestation.getStation());

        logger.debug("FirestationsDaoImpl update", firestation, fireStationToUpdate);
        fireStationRepository.save(fireStationToUpdate);
        return fireStationToUpdate;
    }

    @Override
    public FireStation delete(String fire) {
        if (fire == "1" || fire == "2" || fire == "3" || fire == "4") {
            FireStation fireStationToDelete = fireStationRepository.findByStation(fire);
            fireStationRepository.delete(fireStationToDelete);
            logger.debug("FirestationsDaoImpl create", fireStationToDelete);
            return fireStationToDelete;
        } else {
            FireStation fireStationToDelete = fireStationRepository.findFirstByAddress(fire);
            fireStationRepository.delete(fireStationToDelete);
            logger.debug("FirestationsDaoImpl create", fireStationToDelete);
            return fireStationToDelete;
        }
    }


}
