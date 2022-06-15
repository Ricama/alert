package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Person;
import org.springframework.data.repository.CrudRepository;



public interface PersonRepository  extends CrudRepository<Person, Long> {
    Person findByFirstNameAndLastName(String firstName, String lastName);


}