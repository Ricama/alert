package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicalrecordsDaoImpl implements MedicalRecordDao {
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    Logger logger = LoggerFactory.getLogger(MedicalrecordsDaoImpl.class);

    public MedicalrecordsDaoImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord create(MedicalRecord medicalrecord) {
        logger.debug("MedicalrecordsDaoImpl create", medicalrecord);
        return medicalRecordRepository.save(medicalrecord);
    }

    @Override
    public MedicalRecord updateMedical(MedicalRecord medicalrecord) {

        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalRecordToUpdate.setBirthdate(medicalrecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalrecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalrecord.getAllergies());

        logger.debug("MedicalrecordsDaoImpl updateMedical", medicalrecord, medicalRecordToUpdate);
        medicalRecordRepository.save(medicalRecordToUpdate);
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
