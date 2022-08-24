package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.MedicalRecord;


public interface MedicalRecordDao {

    /**
     * save new medicalRecord in dataBase
     */
    MedicalRecord create(MedicalRecord medicalrecords);

    /**
     * update medicalRecord in dataBase
     */
    MedicalRecord updateMedical(MedicalRecord medicalrecords);

    /**
     * delete medicalRecord in dataBase
     */
    MedicalRecord deleteMedical(String firstName, String lastName);
}
