package com.safetyNet.alert.dao;


import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class PersonsDaoImpl implements PersonDao {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Person personToUpdate = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setCity(person.getCity());
        personToUpdate.setZip(person.getZip());
        personToUpdate.setPhone(person.getPhone());
        personToUpdate.setEmail(person.getEmail());

        return personRepository.save(personToUpdate);
    }

    @Override
    public Person delete(String firstName, String lastName) {
Person personToDelete = personRepository.findByFirstNameAndLastName(firstName,lastName);
         personRepository.delete(personToDelete);
         return null;
    }

    @Override
    public PersonByStationList getPersonByStation(String station){
        try {
       List<Person> person =  personRepository.findByFireStationStation(station);
       int adult = 0;
       int child = 0;
       List<PersonByStation> personByStations = new ArrayList<>();
       for(int i = 0; i < person.size();i++ )
       {
           MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.get(i).getFirstName(),person.get(i).getLastName());

               SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
               Date date = dateFormat.parse(medicalRecord.getBirthdate());
               Date compare = dateFormat.parse("07/04/2004");
               if(date.before(compare)){
                   child++;
               }
               else
               {
                   adult++;
               }
               PersonByStation personByStationadd = new PersonByStation(person.get(i).getFirstName(),person.get(i).getLastName(),person.get(i).getAddress(),person.get(i).getPhone());
               personByStations.add(personByStationadd);
           }
       PersonByStationList result = new PersonByStationList(personByStations,adult,child);
       return result;
        }
       catch (ParseException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public ChildByAddress childByAddress(String address){

        return null;
    }

    @Override
    public PersonByAddress personByAddress(String address){
        return null;
    }

    @Override
    public PersonInfo personInfo(String firstName, String lastName){
        return null;
    }

    @Override
    public Person getPersonByEmail(String email){
        return null;
    }
}
