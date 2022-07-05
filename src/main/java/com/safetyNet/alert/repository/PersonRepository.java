package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository  extends CrudRepository<Person, Long> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddress(String address);
    List<Person> findByFireStationStation(String station);
    String findFirstAddressByFireStationStation(String station);
    @Query("Select p.email from Person p where p.city = :city")
    List<String> findEmailByCity(String city);
}