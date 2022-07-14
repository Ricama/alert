package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.MedicalRecordRepository;
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
class PersonsDaoImplTest {

    PersonDao personDao;


    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    private void setup() {
        personDao = new PersonDaoImpl(personRepository, medicalRecordRepository);
    }

    @Test
    void createTest() {

    }

    @Test
    void updateTest() {
    }

    @Test
    void deleteTest() {
    }

    @Test
    void getPersonByStationTest() {

        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", medicalRecordTest, fireStationTest);
        List<Person> PersonTestList = new ArrayList<>();
        PersonTestList.add(personTest);
        when(personRepository.findByFireStationStation("1")).thenReturn(PersonTestList);
        when(medicalRecordRepository.findByFirstNameAndLastName("John", "Bod")).thenReturn(medicalRecordTest);
        PersonByStation personByStationTest = new PersonByStation("John", "Bod", "1509 Culver St", "841-874-6512");
        List<PersonByStation> personByStationTestList = new ArrayList<>();
        personByStationTestList.add(personByStationTest);
        PersonByStationList personByStationListTest = new PersonByStationList(personByStationTestList, 1, 0);


        assertEquals(personByStationListTest, personDao.getPersonByStation("1"));
    }

    @Test
    void childByAddressTest() {
    }

    @Test
    void getPhoneByStationTest() {
    }

    @Test
    void personByAddressTest() {
    }

    @Test
    void getHomeByStationTest() {
    }

    @Test
    void personInfoTest() {
    }

    @Test
    void getEmailByCityTest() {
    }
}