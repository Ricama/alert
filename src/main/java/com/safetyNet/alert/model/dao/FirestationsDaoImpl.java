package com.safetyNet.alert.model.dao;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.repository.FireStationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
        try {
            fireStationRepository.save(firestation);
            logger.info("FireStation create.");
            return firestation;
        }catch (Exception e ){
            logger.error("FireStationDaoImpl create.(Exception: "+e+" FireStation: " + firestation.toString() + ")");
            return null;
        }
    }

    @Override
    public FireStation update(FireStation firestation) {
        try {
            FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(firestation.getAddress());
            fireStationToUpdate.setAddress(firestation.getAddress());
            fireStationToUpdate.setStation(firestation.getStation());
            fireStationRepository.save(fireStationToUpdate);
            logger.info("FireStation update.");
            return fireStationToUpdate;
        }catch (Exception e ){
            logger.error("FireStationDaoImpl update.(Exception: "+e+" FireStation: " + firestation.toString() + ")");
            return null;
        }
    }

    @Override
    public FireStation delete(String fire) {
        try {
            if (fire == "1" || fire == "2" || fire == "3" || fire == "4" || fire == "5") {
                logger.info("FireStation delete by station number");
                FireStation fireStationToDelete = fireStationRepository.findByStation(fire);
                fireStationRepository.delete(fireStationToDelete);
                return fireStationToDelete;
            } else {
                logger.info("FireStation delete by address");
                FireStation fireStationToDelete = fireStationRepository.findFirstByAddress(fire);
                fireStationRepository.delete(fireStationToDelete);
                return fireStationToDelete;
            }
        }catch (Exception e ){
            logger.error("FireStationDaoImpl delete by address.(Exception: "+e+" Fire: " + fire + ")");
            return null;
        }
    }


}
