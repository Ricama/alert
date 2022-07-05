package com.safetyNet.alert.controller;

import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    PersonDao personDao;

    PersonController(PersonDao personsDao){

        this.personDao = personsDao;
    }

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping(path = "/person")
   public Person postNewPerson(@RequestBody Person person){


        return personDao.create(person);
    }

    @PutMapping(path = "/person")
   public Person putPerson(@RequestBody Person person){

        return personDao.update(person);
  }

   @DeleteMapping(path = "/person")
   public Person deletePerson(@RequestBody Person person){

      return personDao.delete(person.getFirstName(), person.getLastName());
  }

    @GetMapping(path = "/firestation")
    public PersonByStationList getPersonByStation(@RequestParam String stationNumber){

        return personDao.getPersonByStation(stationNumber);
    }

  @GetMapping(path = "/childAlert" )
    public ChildByAddress childByAddress(@RequestParam String address){

        return personDao.childByAddress(address);
  }

    @GetMapping(path = "/phoneAlert")
    public List<String> getPhoneByStation(@RequestParam String firestation){
        return personDao.getPhoneByStation(firestation);
    }

  @GetMapping(path = "/fire")
    public List<PersonByAddress> personByAddress(@RequestParam String address){
        return personDao.personByAddress(address);
  }

    @GetMapping(path = "/flood/{station}")
    public String getHomeByStation(@PathVariable String station){

        return personRepository.findFirstAddressByFireStationStation(station) ;
    }

  @GetMapping(path = "/personInfo")
    public PersonInfo personInfo(@RequestParam String firstName, @RequestParam String lastName){

        return personDao.personInfo(firstName,lastName);
  }

  @GetMapping(path = "/communityEmail")
    public List<String> getPersonByEmail(@RequestParam String city){
        return personDao.getPersonByEmail(city);
  }
}
