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
        logger.debug("PersonDaoImpl create", person);
        FireStation fireStation = fireStationRepository.findFirstByAddress(person.getAddress());
        person.setFireStation(fireStation);
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Person personToUpdate = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        FireStation fireStationToUpdate = fireStationRepository.findFirstByAddress(person.getAddress());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setCity(person.getCity());
        personToUpdate.setZip(person.getZip());
        personToUpdate.setPhone(person.getPhone());
        personToUpdate.setEmail(person.getEmail());
        personToUpdate.setFireStation(fireStationToUpdate);
        logger.debug("PersonDaoImpl update", person, personToUpdate);
        personRepository.save(personToUpdate);
        return personToUpdate;
    }

    @Override
    public Person delete(String firstName, String lastName) {
        Person personToDelete = personRepository.findByFirstNameAndLastName(firstName, lastName);
        personRepository.delete(personToDelete);
        logger.debug("PersonDaoImpl delete", personToDelete);
        return personToDelete;
    }

    @Override
    public PersonByStationList getPersonByStation(String station) {
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
            logger.debug("PersonDaoImpl  getPersonByStation {} {}", personByStations, result);
            return result;
        } catch (Exception e) {
            logger.error("Exception getPersonByStation {}", e);
        }
        return null;
    }

    @Override
    public ChildByAddress childByAddress(String address) {
        try {
            List<Person> personList = personRepository.findByAddress(address);
            List<Child> childList = new ArrayList<>();
            List<Person> adult = new ArrayList<>();
            List<ChildByAddress> childByAddressList = new ArrayList<>();
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
                return childByAddress;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("Exception getPersonByStation {}", e);
        }
        return null;
    }

    @Override
    public List<String> getPhoneByStation(String station) {
        List<String> phoneList = new ArrayList<>();
        List<Person> personList = personRepository.findByFireStationStation(station);
        for (int i = 0; i < personList.size(); i++) {
            phoneList.add(personList.get(i).getPhone());
        }
        logger.debug("PersonDaoImpl getPhoneByStation", personList, phoneList);
        return phoneList;
    }

    @Override
    public List<PersonByAddress> personByAddress(String address) {
        List<Person> person = personRepository.findByAddress(address);
        List<PersonByAddress> personByAddressList = new ArrayList<>();
        for (int i = 0; i < person.size(); i++) {
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            List<Medication> medicationList = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            List<Allergy> allergyList = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.get(i).getFirstName(),person.get(i).getLastName());
            PersonByAddress personByAddress = new PersonByAddress(person.get(i).getFireStation().getStation(), person.get(i).getFirstName(), person.get(i).getLastName(), person.get(i).getPhone(), medicalRecord.getBirthdate(), medicationList,allergyList);
            personByAddressList.add(personByAddress);
        }
        logger.debug("PersonDaoImpl persoByAddress", person, personByAddressList);
        return personByAddressList;

    }


    @Override
    public List<Home> getHomeByStation(String station) {
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
        return homeList;
    }

    @Override
    public PersonInfo personInfo(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Medication> medicationList = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.getFirstName(),person.getLastName());
        List<Allergy> allergyList = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(person.getFirstName(),person.getLastName());
        PersonInfo personInfo = new PersonInfo(person.getLastName(), medicalRecord.getBirthdate(), person.getEmail(),medicationList,allergyList);
        logger.debug("PersonDaoImpl getHomeByStation", person, personInfo);
        return personInfo;
    }

    @Override
    public List<String> getEmailByCity(String city) {
        logger.debug("PersonDaoImpl persoByAddress");
        return personRepository.findEmailByCity(city);
    }
}
