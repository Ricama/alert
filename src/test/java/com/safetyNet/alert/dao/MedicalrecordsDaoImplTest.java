package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.*;

import com.safetyNet.alert.model.dao.MedicalRecordDao;
import com.safetyNet.alert.model.dao.MedicalrecordsDaoImpl;
import com.safetyNet.alert.repository.AllergyRepository;
import com.safetyNet.alert.repository.MedicalRecordRepository;
import com.safetyNet.alert.repository.MedicationRepository;
import com.safetyNet.alert.repository.PersonRepository;
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
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private AllergyRepository allergyRepository;

    @BeforeEach
    private void setup() {
        medicalrecordsDao = new MedicalrecordsDaoImpl(personRepository,medicalRecordRepository,medicationRepository,allergyRepository);
    }

    @Test
    void createTest() {
        Medication medication = new Medication("Test");
        Allergy allergy = new Allergy("Test");
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        medicationTestList.add(medication);
        allergyTestList.add(allergy);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstFireStationTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        when(personRepository.findByFirstNameAndLastName(medicalRecordTest.getFirstName(),medicalRecordTest.getLastName())).thenReturn(firstPersonTest);
        when(medicalRecordRepository.save(medicalRecordTest)).thenReturn(medicalRecordTest);

        assertAll(
                () -> assertEquals(medicalRecordTest.getFirstName(), medicalrecordsDao.create(medicalRecordTest).getFirstName()),
                () -> assertEquals(medicalRecordTest.getLastName(), medicalrecordsDao.create(medicalRecordTest).getLastName()),
                () -> assertEquals(medicalRecordTest.getBirthdate(), medicalrecordsDao.create(medicalRecordTest).getBirthdate()),
                () -> assertEquals(medicalRecordTest.getMedications(), medicalrecordsDao.create(medicalRecordTest).getMedications()),
                () -> assertEquals(medicalRecordTest.getAllergies(), medicalrecordsDao.create(medicalRecordTest).getAllergies())
        );
    }

    @Test
    void updateMedicalTest() {
        Medication medication = new Medication("Test");
        Allergy allergy = new Allergy("Test");
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        medicationTestList.add(medication);
        allergyTestList.add(allergy);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstFireStationTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        MedicalRecord medicalRecord = new MedicalRecord("John", "Bod", "03/06/1983", medicationTestList, allergyTestList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John","Bod")).thenReturn(medicalRecord);
        when(personRepository.findByFirstNameAndLastName(medicalRecordTest.getFirstName(),medicalRecordTest.getLastName())).thenReturn(firstPersonTest);

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
        Medication medication = new Medication("Test");
        Allergy allergy = new Allergy("Test");
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        medicationTestList.add(medication);
        allergyTestList.add(allergy);
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John","Bod")).thenReturn(medicalRecordTest);
        when(medicationRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName("John","Bod")).thenReturn(medicationTestList);
        when(allergyRepository.findByMedicalRecordFirstNameAndMedicalRecordLastName("John","Bod")).thenReturn(allergyTestList);
        assertAll(
                () -> assertEquals(medicalRecordTest.getFirstName(),medicalrecordsDao.deleteMedical("John","Bod").getFirstName()),
                () -> assertEquals(medicalRecordTest.getLastName(),medicalrecordsDao.deleteMedical("John","Bod").getLastName()),
                () -> assertEquals(medicalRecordTest.getBirthdate(),medicalrecordsDao.deleteMedical("John","Bod").getBirthdate()),
                () -> assertEquals(medicalRecordTest.getMedications(),medicalrecordsDao.deleteMedical("John","Bod").getMedications()),
                () -> assertEquals(medicalRecordTest.getAllergies(),medicalrecordsDao.deleteMedical("John","Bod").getAllergies())
        );
    }
}