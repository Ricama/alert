//package com.safetyNet.alert.controller;
//
//import com.safetyNet.alert.dao.PersonDao;
//import com.safetyNet.alert.model.*;
//import com.safetyNet.alert.repository.PersonRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping
//public class PersonController {
//
//    PersonDao personDao;
//
//    Logger logger = LoggerFactory.getLogger(PersonController.class);
//
//    PersonController(PersonDao personsDao) {
//
//        this.personDao = personsDao;
//    }
//
//    /**
//     * Mapping post person
//     */
//    @PostMapping(path = "/person")
//    public Person postNewPerson(@RequestBody Person person) {
//
//
//        return personDao.create(person);
//    }
//
//    /**
//     * Mapping post person
//     */
//    @PutMapping(path = "/person")
//    public Person putPerson(@RequestBody Person person) {
//
//        return personDao.update(person);
//    }
//
//    /**
//     * Mapping delete person
//     */
//    @DeleteMapping(path = "/person")
//    public Person deletePerson(@RequestBody Person person) {
//
//        return personDao.delete(person.getFirstName(), person.getLastName());
//    }
//
//    /**
//     * Mapping get
//     * @return list of person and count of the number of adults and the number of children
//     */
//    @GetMapping(path = "/firestation")
//    public PersonByStationList getPersonByStation(@RequestParam String stationNumber) {
//
//        return personDao.getPersonByStation(stationNumber);
//    }
//
//    /**
//     * Mapping get
//     * @return List of childrenand a list of others household members
//     */
//    @GetMapping(path = "/childAlert")
//    public ChildByAddress childByAddress(@RequestParam String address) {
//
//        return personDao.childByAddress(address);
//    }
//
//    /**
//     * Mapping get
//     * @return telephone Number list
//     */
//    @GetMapping(path = "/phoneAlert")
//    public List<String> getPhoneByStation(@RequestParam String firestation) {
//        return personDao.getPhoneByStation(firestation);
//    }
//
//    /**
//     * Mapping get
//     * @return person list
//     */
//    @GetMapping(path = "/fire")
//    public List<PersonByAddress> personByAddress(@RequestParam String address) {
//        return personDao.personByAddress(address);
//    }
//
//    /**
//     * Mapping get
//     * @return household list
//     */
//    @GetMapping(path = "/flood/stations")
//    public List<Home> getHomeByStation(@RequestParam String station) {
//
//        return personDao.getHomeByStation(station);
//    }
//
//    /**
//     * Mapping get
//     * @return person list
//     */
//    @GetMapping(path = "/personInfo")
//    public PersonInfo personInfo(@RequestParam String firstName, @RequestParam String lastName) {
//
//        return personDao.personInfo(firstName, lastName);
//    }
//
//    /**
//     * Mapping get
//     *
//     * @return email list
//     */
//    @GetMapping(path = "/communityEmail")
//    public List<String> getPersonByEmail(@RequestParam String city) {
//        return personDao.getEmailByCity(city);
//    }
//}
