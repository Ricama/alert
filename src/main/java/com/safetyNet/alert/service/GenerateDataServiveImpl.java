package com.safetyNet.alert.service;

import com.google.gson.Gson;
import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.*;
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
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    AllergyRepository allergyRepository;

    private final Logger logger = LoggerFactory.getLogger(GenerateDataServiveImpl.class);

    @Override
    public void generateData() {
        if (personRepository.count() == 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.json"))) {

                var gson = new Gson();


                DataPerson dataPerson = gson.fromJson(reader, DataPerson.class);

                for (int i = 0; i < dataPerson.getMedicalrecords().size(); i++)
                {
                    MedicalRecord medicalRecord = new MedicalRecord();
                    medicalRecord.setFirstName(dataPerson.getMedicalrecords().get(i).getFirstName());
                    medicalRecord.setLastName(dataPerson.getMedicalrecords().get(i).getLastName());
                    medicalRecord.setBirthdate(dataPerson.getMedicalrecords().get(i).getBirthdate());
                    medicalRecordRepository.save(medicalRecord);
                }
                for (int i = 0; i < dataPerson.getMedicalrecords().size(); i++)
                {
                    for (int a = 0; a < dataPerson.getMedicalrecords().get(i).getMedications().size(); a++) {
                        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(dataPerson.getMedicalrecords().get(i).getFirstName(),dataPerson.getMedicalrecords().get(i).getLastName());
                        Medication medication = new Medication(dataPerson.getMedicalrecords().get(i).getMedications().get(a),medicalRecord);
                        medicationRepository.save(medication);
                    }
                    for (int a = 0; a < dataPerson.getMedicalrecords().get(i).getAllergies().size(); a++) {
                        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(dataPerson.getMedicalrecords().get(i).getFirstName(),dataPerson.getMedicalrecords().get(i).getLastName());
                        Allergy allergy = new Allergy(dataPerson.getMedicalrecords().get(i).getAllergies().get(a),medicalRecord);
                        allergyRepository.save(allergy);
                    }
                }
                fireStationRepository.saveAll(dataPerson.getFirestations());

                for (int i = 0; i < dataPerson.getPersons().size(); i++) {
                    Person person = dataPerson.getPersons().get(i);
                    FireStation fireStation = fireStationRepository.findFirstByAddress(person.getAddress());
                    person.setFireStation(fireStation);
                    MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                    person.setMedicalRecord(medicalRecord);
                    personRepository.save(person);
                }

            } catch (Exception e) {
                logger.debug("generateData()",e);
            }
        }
    }

    }
