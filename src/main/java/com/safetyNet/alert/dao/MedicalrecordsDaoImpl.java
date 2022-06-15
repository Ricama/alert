package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MedicalrecordsDaoImpl implements MedicalrecordsDao{
    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @Override
    public MedicalRecord create(MedicalRecord medicalrecord){
        return medicalRecordRepository.save(medicalrecord);
    }

    @Override
    public MedicalRecord updateMedical(MedicalRecord medicalrecord){

        MedicalRecord medicalRecordToUpdate = medicalRecordRepository.findByFirstNameAndLastName(medicalrecord.getFirstName(), medicalrecord.getLastName());
        medicalRecordToUpdate.setBirthdate(medicalrecord.getBirthdate());
        medicalRecordToUpdate.setMedications(medicalrecord.getMedications());
        medicalRecordToUpdate.setAllergies(medicalrecord.getAllergies());

        return medicalRecordRepository.save(medicalRecordToUpdate);
    }

    @Override
    public MedicalRecord deleteMedical(String firstName, String lastName){
        MedicalRecord medicalRecordToDelete = medicalRecordRepository.findByFirstNameAndLastName(firstName,lastName);
        medicalRecordRepository.delete(medicalRecordToDelete);
        return null;
    }
}
