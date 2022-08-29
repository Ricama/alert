package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.Allergy;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Medication;
import com.safetyNet.alert.repository.AllergyRepository;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.MedicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicalrecordsDaoImpl implements MedicalRecordDao {
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Autowired
    MedicationRepository medicationRepository;

    @Autowired
    AllergyRepository allergyRepository;

    Logger logger = LoggerFactory.getLogger(MedicalrecordsDaoImpl.class);

    public MedicalrecordsDaoImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalrecord) {
        logger.debug("MedicalrecordsDaoImpl create", medicalrecord);
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
        else{
            Medication medication = new Medication("",medicalrecord);
            Allergy allergy = new Allergy("",medicalrecord);
        }

        return medicalrecord;
    }

    @Override
    public MedicalRecord updateMedical(MedicalRecord medicalrecord) {

        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalRecordToUpdate.setBirthdate(medicalrecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalrecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalrecord.getAllergies());

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
        MedicalRecord medicalRecordToDelete = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);
        medicalRecordRepository.delete(medicalRecordToDelete);
        logger.debug("MedicalrecordsDaoImpl deleteMedical", medicalRecordToDelete);
        return medicalRecordToDelete;
    }
}
