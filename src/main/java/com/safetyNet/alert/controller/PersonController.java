package com.safetyNet.alert.controller;

import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/person")
public class PersonController {

    PersonDao personDao;

    PersonController(PersonDao personsDao){

        this.personDao = personsDao;
    }

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping
   public Person postNewPerson(@RequestBody Person person){


        return personDao.create(person);
    }

    @PutMapping
   public Person putPerson(@RequestBody Person person){

        return personDao.update(person);
  }

   @DeleteMapping
   public Person deletePerson(@RequestBody Person person){

      return personDao.delete(person.getFirstName(), person.getLastName());
  }
}
