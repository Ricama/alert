package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.MedicalRecord;


public interface MedicalRecordDao {
    MedicalRecord create(MedicalRecord medicalrecords);
    MedicalRecord updateMedical(MedicalRecord medicalrecords);
    MedicalRecord deleteMedical(String firstName, String lastName);
}
