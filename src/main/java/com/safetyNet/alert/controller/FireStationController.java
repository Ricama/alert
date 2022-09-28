package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.FireStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.safetyNet.alert.model.dao.FirestationDao;

@RestController
@RequestMapping
public class FireStationController {

    private final Logger logger = LoggerFactory.getLogger(FireStationController.class);

    private final FirestationDao firestationsDao;

    public FireStationController(FirestationDao firestationsDao) {

        this.firestationsDao = firestationsDao;
    }

    /**
     * Mapping post fireStation
     */
    @PostMapping(path = "/firestation")
    public FireStation postFirestation(@RequestBody FireStation firestation) {
        try{
        logger.info("POST FireStation executed");
        return firestationsDao.create(firestation);
        }catch (Exception e){
            logger.error("POST FireStation.(Exception: "+e+" FireStation :"+firestation.toString()+"");
            return null;
        }
    }

    /**
     * Mapping put fireStation
     */
    @PutMapping(path = "/firestation")
    public FireStation putFirestation(@RequestBody FireStation firestation) {
        try {
            logger.info("PUT FireStation executed");
            return firestationsDao.update(firestation);
        }catch (Exception e ){
            logger.error("PUT FireStation.(Exception: "+e+" FireStation :" + firestation.toString() + "");
            return null;
        }
    }

    /**
     * Mapping delete fireStation
     */
    @DeleteMapping(path = "/firestation/{fire}")
    public FireStation deleteStationFirestation(@PathVariable String fire) {
        try {
            logger.info("DELETE FireStation executed");
            return firestationsDao.delete(fire);
        }catch (Exception e ){
            logger.error("DELETE FireStation.(Exception: "+e+" Param :" + fire + "");
            return null;
        }
    }

}
