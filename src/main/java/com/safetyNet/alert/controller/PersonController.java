package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.dao.PersonDao;
import com.safetyNet.alert.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class PersonController {

    PersonDao personDao;

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    PersonController(PersonDao personsDao) {

        this.personDao = personsDao;
    }

    /**
     * Mapping post person
     */
    @PostMapping(path = "/person")
    public Person postNewPerson(@RequestBody Person person) {
        try {
            logger.info("POST Person executed");
            return personDao.create(person);
        }catch (Exception e){
            logger.error("POST Person.(Exception: "+e+" Person :"+person.toString()+"");
            return null;
        }

    }

    /**
     * Mapping post person
     */
    @PutMapping(path = "/person")
    public Person putPerson(@RequestBody Person person) {
        try {
            logger.info("PUT Person executed");
            return personDao.update(person);
        }catch (Exception e){
            logger.error("Put Person.(Exception: "+e+" Person :"+person.toString()+"");
            return null;
        }

    }

    /**
     * Mapping delete person
     */
    @DeleteMapping(path = "/person")
    public Person deletePerson(@RequestBody Person person) {
        try {
            logger.info("Delete Person executed");
            return personDao.delete(person.getFirstName(), person.getLastName());
        }catch (Exception e){
            logger.error("Delete Person.(Exception: "+e+" Person :"+person.toString()+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return list of person and count of the number of adults and the number of children
     */
    @GetMapping(path = "/firestation")
    public PersonByStationList getPersonByStation(@RequestParam String stationNumber) {
        try {
            logger.info("getPersonByStation executed");
            return personDao.getPersonByStation(stationNumber);
        }catch (Exception e){
            logger.error("getPersonByStation.(Exception: "+e+" stationNumber :"+stationNumber+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return List of childrenand a list of others household members
     */
    @GetMapping(path = "/childAlert")
    public ChildByAddress childByAddress(@RequestParam String address) {
        try {
            logger.info("childByAddress executed");
            return personDao.childByAddress(address);
        }catch (Exception e){
            logger.error("childByAddress.(Exception: "+e+" address :"+address+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return telephone Number list
     */
    @GetMapping(path = "/phoneAlert")
    public List<String> getPhoneByStation(@RequestParam String firestation) {
        try {
            logger.info("getPhoneByStation executed");
            return personDao.getPhoneByStation(firestation);
        }catch (Exception e){
            logger.error("getPhoneByStation.(Exception: "+e+" firestation :"+firestation+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return person list
     */
    @GetMapping(path = "/fire")
    public List<PersonByAddress> personByAddress(@RequestParam String address) {
        try {
            logger.info("personByAddress executed");
            return personDao.personByAddress(address);
        }catch (Exception e){
            logger.error("personByAddress.(Exception: "+e+" address :"+address+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return household list
     */
    @GetMapping(path = "/flood/stations")
    public List<Home> getHomeByStation(@RequestParam String station) {
        try {
            logger.info("getHomeByStation executed");
            return personDao.getHomeByStation(station);
        }catch (Exception e){
            logger.error("getHomeByStation.(Exception: "+e+" station :"+station+"");
            return null;
        }

    }

    /**
     * Mapping get
     * @return person list
     */
    @GetMapping(path = "/personInfo")
    public PersonInfo personInfo(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            logger.info("personInfo executed");
            return personDao.personInfo(firstName, lastName);
        }catch (Exception e){
            logger.error("personInfo.(Exception: "+e+" firstName :"+firstName+" lastName"+lastName+"");
            return null;
        }

    }

    /**
     * Mapping get
     *
     * @return email list
     */
    @GetMapping(path = "/communityEmail")
    public List<String> getPersonByEmail(@RequestParam String city) {
        try {
            logger.info("getPersonByEmail executed");
            return personDao.getEmailByCity(city);
        }catch (Exception e){
            logger.error("getPersonByEmail.(Exception: "+e+" city :"+city+")");
            return null;
        }

    }
}
