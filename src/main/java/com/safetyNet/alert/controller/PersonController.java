package com.safetyNet.alert.controller;

import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class PersonController {

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
    public List<Person> getPersonByStation(@RequestParam String stationNumber){

        return personDao.getPersonByStation(stationNumber);
    }

  @GetMapping(path = "/childAlert/{address}" )
    public ChildByAddress childByAddress(@PathVariable String address){
        return null;
  }

  @GetMapping(path = "/fire/{address}")
    public PersonByAddress personByAddress(@PathVariable String address){
        return null;
  }

  @GetMapping(path = "/personInfo/{firstName}/{lastName}")
    public PersonInfo personInfo(@PathVariable String firstName, @PathVariable String lastName){
        return null;
  }

  @GetMapping(path = "/communityEmail/{city}")
    public Person getPersonByEmail(@PathVariable String city){
        return null;
  }
}
