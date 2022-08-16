package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.Allergy;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Medication;

import com.safetyNet.alert.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalrecordsDaoImplTest {

    MedicalRecordDao medicalrecordsDao;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    private void setup() {
        medicalrecordsDao = new MedicalrecordsDaoImpl(medicalRecordRepository);
    }

    @Test
    void createTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        when(medicalRecordRepository.save(medicalRecordTest)).thenReturn(medicalRecordTest);

        assertAll(
                () -> assertEquals(medicalRecordTest.getFirstName(),medicalrecordsDao.create(medicalRecordTest).getFirstName()),
                () -> assertEquals(medicalRecordTest.getLastName(),medicalrecordsDao.create(medicalRecordTest).getLastName()),
                () -> assertEquals(medicalRecordTest.getBirthdate(),medicalrecordsDao.create(medicalRecordTest).getBirthdate()),
                () -> assertEquals(medicalRecordTest.getMedications(),medicalrecordsDao.create(medicalRecordTest).getMedications()),
                () -> assertEquals(medicalRecordTest.getAllergies(),medicalrecordsDao.create(medicalRecordTest).getAllergies())
        );
    }

    @Test
    void updateMedicalTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        MedicalRecord medicalRecord = new MedicalRecord("John", "Bod", "03/06/1983", medicationTestList, allergyTestList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John","Bod")).thenReturn(medicalRecord);

        assertAll(
                () -> assertEquals(medicalRecordTest.getFirstName(),medicalrecordsDao.updateMedical(medicalRecordTest).getFirstName()),
                () -> assertEquals(medicalRecordTest.getLastName(),medicalrecordsDao.updateMedical(medicalRecordTest).getLastName()),
                () -> assertEquals(medicalRecordTest.getBirthdate(),medicalrecordsDao.updateMedical(medicalRecordTest).getBirthdate()),
                () -> assertEquals(medicalRecordTest.getMedications(),medicalrecordsDao.updateMedical(medicalRecordTest).getMedications()),
                () -> assertEquals(medicalRecordTest.getAllergies(),medicalrecordsDao.updateMedical(medicalRecordTest).getAllergies())
        );
    }

    @Test
    void deleteMedicalTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John","Bod")).thenReturn(medicalRecordTest);

        assertAll(
                () -> assertEquals(medicalRecordTest.getFirstName(),medicalrecordsDao.deleteMedical("John","Bod").getFirstName()),
                () -> assertEquals(medicalRecordTest.getLastName(),medicalrecordsDao.deleteMedical("John","Bod").getLastName()),
                () -> assertEquals(medicalRecordTest.getBirthdate(),medicalrecordsDao.deleteMedical("John","Bod").getBirthdate()),
                () -> assertEquals(medicalRecordTest.getMedications(),medicalrecordsDao.deleteMedical("John","Bod").getMedications()),
                () -> assertEquals(medicalRecordTest.getAllergies(),medicalrecordsDao.deleteMedical("John","Bod").getAllergies())
        );
    }
}