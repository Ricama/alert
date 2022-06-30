package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.FireStation;
import com.safetyNet.alert.model.HomePerson;
import com.safetyNet.alert.model.PersonByStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.safetyNet.alert.dao.FirestationDao;

import java.util.List;

@RestController
@RequestMapping
public class FireStastionController {

    private final Logger logger = LoggerFactory.getLogger(FireStastionController.class);

    private final FirestationDao firestationsDao;

    public FireStastionController(FirestationDao firestationsDao){

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


    @GetMapping(path = "/phoneAlert/{station}")
    public List<String> getPhoneByStation(@PathVariable String station){
        return null;
    }

    @GetMapping(path = "/flood/{station}")
    public List<HomePerson> getHomeByStation(@PathVariable String station){
        return null;
    }


}
