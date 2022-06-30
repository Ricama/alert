package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByFireStationStation(String station);
}