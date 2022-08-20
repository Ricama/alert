package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.FireStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.safetyNet.alert.dao.FirestationDao;

@RestController
@RequestMapping
public class FireStationController {

    private final Logger logger = LoggerFactory.getLogger(FireStationController.class);

    private final FirestationDao firestationsDao;

    public FireStationController(FirestationDao firestationsDao){

        this.firestationsDao = firestationsDao;
    }

    @PostMapping(path = "/firestation")
     public FireStation postFirestation(@RequestBody FireStation firestation){

        return firestationsDao.create(firestation);
    }

    @PutMapping(path = "/firestation")
     public FireStation putFirestation(@RequestBody FireStation firestation){

        return firestationsDao.update(firestation);
    }

    @DeleteMapping(path ="/firestation/{fire}")
    public FireStation deleteStationFirestation(@PathVariable String fire){

        return firestationsDao.delete(fire);
    }

}
