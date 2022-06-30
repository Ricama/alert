package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.HomePerson;
import com.safetyNet.alert.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FirestationsDaoImpl implements FirestationDao {

    @Autowired
    FireStationRepository fireStationRepository;

    @Override
    public FireStation create(FireStation firestations){
        return fireStationRepository.save(firestations);
    }

    @Override
    public FireStation update(FireStation firestations){
        FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(firestations.getAddress());
        fireStationToUpdate.setAddress(firestations.getAddress());
        fireStationToUpdate.setStation(firestations.getStation());

        return fireStationRepository.save(fireStationToUpdate);
    }

    @Override
    public FireStation delete(String fire){
      if(fire == "1" || fire == "2" || fire == "3" || fire == "4"){
          FireStation fireStationToDelete = fireStationRepository.findByStation(fire);
          fireStationRepository.delete(fireStationToDelete);
      }
      else {
          FireStation fireStationToDelete = fireStationRepository.findFirstByAddress(fire);
          fireStationRepository.delete(fireStationToDelete);
      }
        return null;
    }



    @Override
    public List<String> getPhoneByStation(String station){
        return null;
    }

    @Override
    public List<HomePerson> getHomeByStation(String station){
        return null;
    }

}
