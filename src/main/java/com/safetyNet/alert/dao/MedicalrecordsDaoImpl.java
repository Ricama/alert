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
        logger.info("MedicalRecord create");
        Person person = personRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalrecord.setPerson(person);
        medicalRecordRepository.save(medicalrecord);
        logger.debug("MedicalRecordImpl create. (MedicalRecord: "+medicalrecord.toString()+"Person: "+person.toString()+")");
        if (medicalrecord.getMedications() != null) {
            for (int i = 0; i < medicalrecord.getMedications().size(); i++) {
                Medication medication = new Medication(medicalrecord.getMedications().get(i).getMedications(), medicalrecord);
                logger.info("medication save");
                medicationRepository.save(medication);
                logger.debug("MedicalRecordsDaoImpl create medication save. (Medication: "+medication+")");
            }
            for (int i = 0; i < medicalrecord.getAllergies().size(); i++) {
                Allergy allergy = new Allergy(medicalrecord.getAllergies().get(i).getAllergies(), medicalrecord);
                logger.info("allergy save");
                allergyRepository.save(allergy);
                logger.debug("MedicalRecordsDaoImpl create allergy save. (Allergy: "+allergy+")");
            }
        }
        return medicalrecord;
    }

    @Override
    public MedicalRecord updateMedical(MedicalRecord medicalrecord) {
        logger.info("MedicalRecord update");
        Person person = personRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalRecordToUpdate.setBirthdate(medicalrecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalrecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalrecord.getAllergies());
        medicalRecordToUpdate.setPerson(person);
        logger.debug("MedicalRecordImpl update debug. (MedicalRecord: "+medicalrecord.toString()+" MedicalRecordToUpdate: "+medicalRecordToUpdate.toString()+" Person: "+person.toString()+")");
        medicalRecordRepository.save(medicalRecordToUpdate);
        for (int i = 0; i < medicalRecordToUpdate.getMedications().size(); i++) {
            logger.info("medication update");
            Medication medication = new Medication(medicalRecordToUpdate.getMedications().get(i).getMedications(), medicalRecordToUpdate);
            medicationRepository.save(medication);
            logger.debug("MedicalRecordsDaoImpl update medication save. (Medication: "+medication+")");
        }
        for (int i = 0; i < medicalRecordToUpdate.getAllergies().size(); i++) {
            logger.info("allergy update");
            Allergy allergy = new Allergy(medicalRecordToUpdate.getAllergies().get(i).getAllergies(), medicalRecordToUpdate);
            allergyRepository.save(allergy);
            logger.debug("MedicalRecordsDaoImpl update allergy save. (Allergy: "+allergy+")");
        }
        return medicalRecordToUpdate;
    }

    @Override
    public MedicalRecord deleteMedical(String firstName, String lastName) {
        logger.info("MedicalRecord delete");
        MedicalRecord medicalRecordapp= medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        List<Medication> medication = medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(firstName, lastName);
        List<Allergy> allergy = allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName(firstName, lastName);
        for (int i = 0;i < medication.size();i++){
           medicationRepository.delete(medication.get(i));
        }
        for (int i = 0;i < allergy.size();i++){
            allergyRepository.delete(allergy.get(i));
        }
        MedicalRecord medicalRecord = new MedicalRecord(firstName,lastName,medicalRecordapp.getBirthdate(),medication,allergy);
        medicalRecordRepository.delete(medicalRecord);
        logger.debug("MedicalRecordDaoImpl delete. (MedicalRecord: "+medicalRecord.toString()+" Medication: "+medication.toString()+" Allergy: "+allergy+")");
        return medicalRecord;
    }
}
