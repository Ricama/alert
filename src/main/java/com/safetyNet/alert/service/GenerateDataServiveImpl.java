package com.safetyNet.alert.service;

import com.google.gson.Gson;
import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.FireStationRepository;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class GenerateDataServiveImpl implements GenerateDataService {
    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    private final Logger logger = LoggerFactory.getLogger(GenerateDataServiveImpl.class);

    @Override
    public void generateData() {
        personRepository.deleteAll();
        fireStationRepository.deleteAll();
        if (personRepository.count() == 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.json"))) {

                var gson = new Gson();


                DataPerson dataPerson = gson.fromJson(reader, DataPerson.class);
                logger.info("dataPerson = {}",dataPerson.getPersons());
                logger.info("dataFirestation = {}",dataPerson.getFirestations());
                logger.info("dataMedicalRecord = {}",dataPerson.getMedicalrecords());


                fireStationRepository.saveAll(dataPerson.getFirestations());

                for (int i = 0; i < dataPerson.getPersons().size(); i++) {
                    Person person = dataPerson.getPersons().get(i);
                    FireStation fireStation = fireStationRepository.findFirstByAddress(person.getAddress());
                    person.setFireStation(fireStation);
                    personRepository.save(person);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    }
