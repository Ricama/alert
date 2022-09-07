package com.safetyNet.alert.dao;


import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component
public class PersonDaoImpl implements PersonDao {
    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    AllergyRepository allergyRepository;

    private final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);


    public PersonDaoImpl(FireStationRepository fireStationRepository, PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, MedicationRepository medicationRepository, AllergyRepository allergyRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicationRepository = medicationRepository;
        this.allergyRepository = allergyRepository;
    }

    @Override
    public Person create(Person person) {
        logger.info("Person create");
        FireStation fireStation = fireStationRepository.findFirstByAddress(person.getAddress());
        person.setFireStation(fireStation);
        logger.debug("PersonDaoImpl create. (Person: "+person.toString()+" FireStation: "+fireStation.toString()+")");
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        logger.info("Person update");
        Person personToUpdate = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(person.getAddress());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setCity(person.getCity());
        personToUpdate.setZip(person.getZip());
        personToUpdate.setPhone(person.getPhone());
        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setFireStation(fireStationToUpdate);
        logger.debug("PersonDaoImpl update. (Person: "+person.toString()+" PersonToUpdate: "+personToUpdate.toString()+" FireStationToUpdate: "+fireStationToUpdate.toString()+")");
        personRepository.save(personToUpdate);
        return personToUpdate;
    }

    @Override
    public Person delete(String firstName, String lastName) {
        logger.info("Person delete");
        Person personToDelete = personRepository.findByFirstNameAndLastName(firstName, lastName);
        personRepository.delete(personToDelete);
        logger.debug("PersonDaoImpl delete. (Person: "+personToDelete.toString()+" Param: "+firstName+","+lastName+")");
        return personToDelete;
    }

    @Override
    public PersonByStationList getPersonByStation(String station) {
        logger.info("PersonByStationList");
        try {
            List<Person> person = personRepository.findByFireStationStation(station);
            int adult = 0;
            int child = 0;
            List<PersonByStation> personByStations = new ArrayList<>();
            for (int i = 0; i < person.size(); i++) {
                MedicalRecord medicalRecord= medicalRecordRepository.findByFirstNameAndLastName(person.get(i).getFirstName(),person.get(i).getLastName());
                LocalDate currentDate = LocalDate.now();
                LocalDate personDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (currentDate.getYear() - personDate.getYear() < 18) {
                    child++;
                } else {

                    adult++;
                }

                PersonByStation personByStationadd = new PersonByStation(person.get(i).getFirstName(), person.get(i).getLastName(), person.get(i).getAddress(), person.get(i).getPhone());
                personByStations.add(personByStationadd);
            }
            PersonByStationList result = new PersonByStationList(personByStations, adult, child);
            logger.debug("PersonByStationList. (Station: "+station+" Persons: "+person.toString()+" PersonByStations: "+personByStations.toString()+"");
            return result;
        } catch (Exception e) {
            logger.error("Exception getPersonByStation {}", e);
        }
        return null;
    }

    @Override
    public ChildByAddress childByAddress(String address) {
        logger.info("childByAddress");
        try {
            List<Person> personList = personRepository.findByAddress(address);
            List<Child> childList = new ArrayList<>();
            List<Person> adult = new ArrayList<>();
            int childCount = 0;
            for (int i = 0; i < personList.size(); i++) {
                MedicalRecord medicalRecord= medicalRecordRepository.findByFirstNameAndLastName(personList.get(i).getFirstName(),personList.get(i).getLastName());
                LocalDate currentDate = LocalDate.now();
                LocalDate personDate = LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (currentDate.getYear() - personDate.getYear() < 18) {
                    Child child = new Child(personList.get(i).getFirstName(), personList.get(i).getLastName(), medicalRecord.getBirthdate());
                    childList.add(child);
                    childCount++;
                } else {
                    adult.add(personList.get(i));
                }
            }
            ChildByAddress childByAddress = new ChildByAddress(childList, adult);
            if (childCount > 0) {
                logger.debug("PersonDaoImpl childByAddress. (Address: "+address+" PersonList: "+personList.toString()+" ChildList: "+childList.toString()+" Adult: "+adult.toString()+" ChildByAddress: "+childByAddress.toString()+" ChildCount: "+childCount+")");
                return childByAddress;
            } else {
                logger.debug("PersonDaoImpl childByAddress return null ChildCount:"+childCount+"");
                return null;
            }

        } catch (Exception e) {

            logger.error("Exception getPersonByStation {}", e);
        }
        return null;
    }

    @Override
    public List<String> getPhoneByStation(String station) {
        logger.info("getPhoneByStation");
        List<String> phoneList = new ArrayList<>();
        List<Person> personList = personRepository.findByFireStationStation(station);
        for (int i = 0; i < personList.size(); i++) {
            phoneList.add(personList.get(i).getPhone());
        }
        logger.debug("PersonDaoImpl getPhoneByStation. (Station: "+station+" PhoneList: "+phoneList.toString()+" PersonList: "+personList.toString()+")");
        return phoneList;
    }

    @Override
    public List<PersonByAddress> personByAddress(String address) {
        logger.info("personByAddress");
        List<Person> person = personRepository.findByAddress(address);
        List<PersonByAddress> personByAddressList = new ArrayList<>();
        for (int i = 0; i < person.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            List<Medication> medicationList = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            List<Allergy> allergyList = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            PersonByAddress personByAddress = new PersonByAddress(person.get(i).getFireStation().getStation(), person.get(i).getFirstName(), person.get(i).getLastName(), person.get(i).getPhone(), medicalRecord.getBirthdate(), medicationList,allergyList);
            personByAddressList.add(personByAddress);
        }
        logger.debug("PersonDaoImpl personByAddress. (Address: "+address+" Person: "+person.toString()+" PersonByAddressList: "+ personByAddressList.toString()+")");
        return personByAddressList;

    }


    @Override
    public List<Home> getHomeByStation(String station) {
        logger.info("getHomeByStation");
        List<String> address = personRepository.findAddressByFireStationStation(station);
        List<Home> homeList = new ArrayList<>();
        for (int i = 0; i < address.size(); i++) {
            List<Person> personList = personRepository.findByAddress(address.get(i));
            List<HomePerson> homePersonList = new ArrayList<>();
            for (int a = 0; a < personList.size(); a++) {
                MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(personList.get(a).getFirstName(),personList.get(a).getLastName());
                List<Medication> medicationList = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(personList.get(a).getFirstName(),personList.get(a).getLastName());
                List<Allergy> allergyList = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(personList.get(a).getFirstName(),personList.get(a).getLastName());
                HomePerson homePerson = new HomePerson(personList.get(a).getLastName(), personList.get(a).getFirstName(), personList.get(a).getPhone(), medicalRecord.getBirthdate(), medicationList, allergyList);
                homePersonList.add(homePerson);
            }
            Home home = new Home(address.get(i), homePersonList);
            homeList.add(home);
        }
        logger.debug("PersonDaoImpl getHomeByStation. (Station: "+station+" Address: "+address.toString()+" HomeList: "+homeList.toString()+")");
        return homeList;
    }

    @Override
    public PersonInfo personInfo(String firstName, String lastName) {
        logger.info("personInfo");
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Medication> medicationList = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.getFirstName(),person.getLastName());
        List<Allergy> allergyList = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.getFirstName(),person.getLastName());
        PersonInfo personInfo = new PersonInfo(person.getLastName(), medicalRecord.getBirthdate(), person.getEmail(),medicationList,allergyList);
        logger.debug("PersonDaoImpl personInfo. (FirstName: "+firstName+" LastName: "+lastName+" Person: "+person.toString()+" MedicalRecord: "+medicalRecord.toString()+" MedicationList: "+medicationList.toString()+" AllergyList: "+allergyList.toString()+" PersonInfo: "+personInfo.toString()+")");
        return personInfo;
    }

    @Override
    public List<String> getEmailByCity(String city) {
        logger.info("getEmailByCity");
        logger.debug("PersonDaoImpl getEmailByCity. (City: "+city+"");
        return personRepository.findEmailByCity(city);
    }
}
