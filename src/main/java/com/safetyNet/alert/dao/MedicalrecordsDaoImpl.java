package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.Allergy;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Medication;
import com.safetyNet.alert.model.Person;
import com.safetyNet.alert.repository.AllergyRepository;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.MedicationRepository;
import com.safetyNet.alert.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MedicalrecordsDaoImpl implements MedicalRecordDao {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    AllergyRepository allergyRepository;

    Logger logger = LoggerFactory.getLogger(MedicalrecordsDaoImpl.class);

    public MedicalrecordsDaoImpl(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository, MedicationRepository medicationRepository, AllergyRepository allergyRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.medicationRepository = medicationRepository;
        this.allergyRepository = allergyRepository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalrecord) {
        logger.debug("MedicalrecordsDaoImpl create", medicalrecord);
        Person person = personRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalrecord.setPerson(person);
        medicalRecordRepository.save(medicalrecord);
        if (medicalrecord.getMedications() != null) {
            for (int i = 0; i < medicalrecord.getMedications().size(); i++) {
                Medication medication = new Medication(medicalrecord.getMedications().get(i).getMedications(), medicalrecord);
                medicationRepository.save(medication);
            }
            for (int i = 0; i < medicalrecord.getAllergies().size(); i++) {
                Allergy allergy = new Allergy(medicalrecord.getAllergies().get(i).getAllergies(), medicalrecord);
                allergyRepository.save(allergy);
            }
        }
        return medicalrecord;
    }

    @Override
    public MedicalRecord updateMedical(MedicalRecord medicalrecord) {
        Person person = personRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalRecordToUpdate.setBirthdate(medicalrecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalrecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalrecord.getAllergies());
        medicalRecordToUpdate.setPerson(person);
        logger.debug("MedicalrecordsDaoImpl updateMedical", medicalrecord, medicalRecordToUpdate);
        medicalRecordRepository.save(medicalRecordToUpdate);
        for (int i = 0; i < medicalRecordToUpdate.getMedications().size(); i++) {
            Medication medication = new Medication(medicalRecordToUpdate.getMedications().get(i).getMedications(), medicalRecordToUpdate);
            medicationRepository.save(medication);
        }
        for (int i = 0; i < medicalRecordToUpdate.getAllergies().size(); i++) {
            Allergy allergy = new Allergy(medicalRecordToUpdate.getAllergies().get(i).getAllergies(), medicalRecordToUpdate);
            allergyRepository.save(allergy);
        }
        return medicalRecordToUpdate;
    }

    @Override
    public MedicalRecord deleteMedical(String firstName, String lastName) {
        MedicalRecord medicalRecordapp= medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Medication> medication = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(firstName, lastName);
        List<Allergy> allergy = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(firstName, lastName);
        List<Medication> medicationList = new ArrayList<>();
        List<Allergy> allergyList = new ArrayList<>();
        for (int i = 0;i < medication.size();i++){
           medicationRepository.delete(medication.get(i));
            Medication medication1 = new Medication(medication.get(i).getMedications());
            medicationList.add(medication1);
        }
        for (int i = 0;i < allergy.size();i++){
            allergyRepository.delete(allergy.get(i));
            Allergy allergy1 = new Allergy(allergy.get(i).getAllergies());
            allergyList.add(allergy1);
        }
        MedicalRecord medicalRecord = new MedicalRecord(firstName,lastName,medicalRecordapp.getBirthdate(),medication,allergy);
        medicalRecordRepository.delete(medicalRecord);
        logger.debug("MedicalrecordsDaoImpl deleteMedical", medicalRecord);
        return medicalRecord;
    }
}
