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
        logger.info("POST FireStation executed");
        logger.error("POST FireStation.(FireStation :"+firestation.toString()+"");
        return firestationsDao.create(firestation);
    }

    /**
     * Mapping put fireStation
     */
    @PutMapping(path = "/firestation")
    public FireStation putFirestation(@RequestBody FireStation firestation) {
        logger.info("PUT FireStation executed");
        logger.error("PUT FireStation.(FireStation :"+firestation.toString()+"");
        return firestationsDao.update(firestation);
    }

    /**
     * Mapping delete fireStation
     */
    @DeleteMapping(path = "/firestation/{fire}")
    public FireStation deleteStationFirestation(@PathVariable String fire) {
        logger.info("DELETE FireStation executed");
        logger.error("DELETE FireStation.(Param :"+fire+"");
        return firestationsDao.delete(fire);
    }

}
