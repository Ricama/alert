package com.safetyNet.alert.dao;


import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(PersonsDaoImpl.class);

    @Override
    public Person create(Person person) {
        logger.debug("PersonDaoImpl create",person);
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
        logger.debug("PersonDaoImpl update",person,personToUpdate);
        return personRepository.save(personToUpdate);
    }

    @Override
    public Person delete(String firstName, String lastName) {
Person personToDelete = personRepository.findByFirstNameAndLastName(firstName,lastName);
         personRepository.delete(personToDelete);
        logger.debug("PersonDaoImpl delete",personToDelete);
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
            logger.debug("PersonDaoImpl getPersonByStation",personByStations,result);
       return result;
        }
       catch (ParseException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public ChildByAddress childByAddress(String address){
        try {
            List<Person> personList = personRepository.findByAddress(address);
            List<Child>  childList = new ArrayList<>();
            List<Person> adult = new ArrayList<>();
            List<ChildByAddress> childByAddressList = new ArrayList<>();
            for (int i = 0; i < personList.size(); i++) {
                MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(personList.get(i).getFirstName(), personList.get(i).getLastName());
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = dateFormat.parse(medicalRecord.getBirthdate());
                Date compare = dateFormat.parse("07/04/2004");
                if (date.before(compare)){
                    Child child = new Child(personList.get(i).getFirstName(),personList.get(i).getLastName(),medicalRecord.getBirthdate());
                    childList.add(child);
                }
                else {
                    adult.add(personList.get(i));
                }
            }
                ChildByAddress childByAddress = new ChildByAddress(childList,adult);
            return childByAddress;
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getPhoneByStation(String station){
        List<String> phoneList = new ArrayList<>();
       List<Person> personList =  personRepository.findByFireStationStation(station);
       for (int i = 0; i < personList.size(); i++)
       {
           phoneList.add(personList.get(i).getPhone());
       }
        logger.debug("PersonDaoImpl getPgoneByStation",personList,phoneList);
        return phoneList;
    }

    @Override
    public List<PersonByAddress> personByAddress(String address){
        List<Person> person = personRepository.findByAddress(address);
        List<PersonByAddress> personByAddressList = new ArrayList<>();
        for (int i = 0 ; i < person.size(); i++){
            PersonByAddress personByAddress = new PersonByAddress(person.get(i).getFireStation().getStation(),person.get(i).getFirstName(),person.get(i).getLastName(),person.get(i).getPhone(),person.get(i).getMedicalRecord().getBirthdate(),person.get(i).getMedicalRecord().getMedications(),person.get(i).getMedicalRecord().getAllergies());
            personByAddressList.add(personByAddress);
        }
        logger.debug("PersonDaoImpl persoByAddress",person,personByAddressList);
        return personByAddressList;

    }


    @Override
    public Home getHomeByStation(String station){
        return null;
    }

    @Override
    public PersonInfo personInfo(String firstName, String lastName){
     Person person = personRepository.findByFirstNameAndLastName(firstName,lastName);
     PersonInfo personInfo = new PersonInfo(person.getLastName(),person.getMedicalRecord().getBirthdate(),person.getEmail(),person.getMedicalRecord().getMedications(),person.getMedicalRecord().getAllergies());
        logger.debug("PersonDaoImpl getHomeByStation",person,personInfo);
        return personInfo ;
    }

    @Override
    public List<String> getEmailByCity(String city){
        logger.debug("PersonDaoImpl persoByAddress");
        return personRepository.findEmailByCity(city);
    }
}
