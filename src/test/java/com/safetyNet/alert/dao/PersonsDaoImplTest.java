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
    void getPersonByStationOneAdultTest() {

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
    void getPersonByStationOneChildTest() {

        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", medicalRecordTest, fireStationTest);
        List<Person> PersonTestList = new ArrayList<>();
        PersonTestList.add(personTest);
        when(personRepository.findByFireStationStation("1")).thenReturn(PersonTestList);
        PersonByStation personByStationTest = new PersonByStation("John", "Bod", "1509 Culver St", "841-874-6512");
        List<PersonByStation> personByStationTestList = new ArrayList<>();
        personByStationTestList.add(personByStationTest);
        PersonByStationList personByStationListTest = new PersonByStationList(personByStationTestList,0 , 1);


        assertEquals(personByStationListTest, personDao.getPersonByStation("1"));
    }

    @Test
    void childByAddressOneAdultOneChildTest() {
        List<Person> PersonTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        PersonTestList.add(firstPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1509 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        PersonTestList.add(personTest);
        when(personRepository.findByAddress("1509 Culver St")).thenReturn(PersonTestList);
        List<Child> childListTest = new ArrayList<>();
        List<Person> adultListTest = new ArrayList<>();
        Child childTest = new Child("Jo","Bode","03/06/2012");
        childListTest.add(childTest);
        adultListTest.add(firstPersonTest);
        ChildByAddress childByAddressListTest = new ChildByAddress(childListTest,adultListTest);

        assertEquals(childByAddressListTest,personDao.childByAddress("1509 Culver St"));
    }
    @Test
    void childByAddressJustAdultTest() {
        List<Person> PersonTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        PersonTestList.add(firstPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2000", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1509 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        PersonTestList.add(personTest);
        when(personRepository.findByAddress("1509 Culver St")).thenReturn(PersonTestList);
        List<Child> childListTest = new ArrayList<>();
        List<Person> adultListTest = new ArrayList<>();
        adultListTest.add(personTest);
        adultListTest.add(firstPersonTest);
        ChildByAddress childByAddressListTest = new ChildByAddress(childListTest,adultListTest);

        assertEquals(childByAddressListTest,personDao.childByAddress("1509 Culver St"));
    }

    @Test
    void childByAddressJustChildTest() {
        List<Person> PersonTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/2020", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        PersonTestList.add(firstPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1509 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        PersonTestList.add(personTest);
        when(personRepository.findByAddress("1509 Culver St")).thenReturn(PersonTestList);
        List<Child> childListTest = new ArrayList<>();
        List<Person> adultListTest = new ArrayList<>();
        Child childTest = new Child("Jo","Bode","03/06/2012");
        Child childTestTwo = new Child("John","Bod","03/06/2020");
        childListTest.add(childTest);
        childListTest.add(childTestTwo);
        ChildByAddress childByAddressListTest = new ChildByAddress(childListTest,adultListTest);

        assertEquals(childByAddressListTest,personDao.childByAddress("1509 Culver St"));
    }

    @Test
    void getPhoneByStationTest() {
        List<Person> PersonTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/2020", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        PersonTestList.add(firstPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1509 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        PersonTestList.add(personTest);
        when(personRepository.findByFireStationStation("1")).thenReturn(PersonTestList);
        List<String> phoneListTest = new ArrayList<>();
        phoneListTest.add(firstPersonTest.getPhone());
        phoneListTest.add(personTest.getPhone());

        assertEquals(phoneListTest,personDao.getPhoneByStation("1"));
    }

    @Test
    void personByAddressTest() {
        List<Person> PersonTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        PersonTestList.add(firstPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1509 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        PersonTestList.add(personTest);
        when(personRepository.findByAddress("1509 Culver St")).thenReturn(PersonTestList);
        List<PersonByAddress> personByAddressListTest = new ArrayList<>();
        PersonByAddress firstPersonByAddressTest = new PersonByAddress(firstPersonTest.getFireStation().getStation(),firstPersonTest.getFirstName(),firstPersonTest.getLastName(),firstPersonTest.getPhone(),firstPersonTest.getMedicalRecord().getBirthdate(),firstPersonTest.getMedicalRecord().getMedications(),firstPersonTest.getMedicalRecord().getAllergies());
        personByAddressListTest.add(firstPersonByAddressTest);
        PersonByAddress personByAddressTest = new PersonByAddress(personTest.getFireStation().getStation(),personTest.getFirstName(),personTest.getLastName(),personTest.getPhone(),personTest.getMedicalRecord().getBirthdate(),personTest.getMedicalRecord().getMedications(),personTest.getMedicalRecord().getAllergies());
        personByAddressListTest.add(personByAddressTest);

        assertEquals(personByAddressListTest,personDao.personByAddress("1509 Culver St"));
    }

    @Test
    void getHomeByStationTest() {
        List<String> address = new ArrayList<>();
        String a = "1509 Culver St";
        address.add(a);
        String b = "1510 Culver St";
        address.add(b);
        when(personRepository.findAddressByFireStationStation("1")).thenReturn(address);
        List<Person> firstPersonTestList = new ArrayList<>();
        List<Person> personTestList = new ArrayList<>();
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/2020", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        firstPersonTestList.add(firstPersonTest);
        MedicalRecord secondMedicalRecordTest = new MedicalRecord("Jon", "Bods", "03/06/2020", medicationTestList, allergyTestList);
        FireStation secondFireStationTest = new FireStation("1509 Culver St", "3");
        Person secondPersonTest = new Person("Jon", "Bods", "1509 Culver St", "Culver", "97451", "841-874-6513", "jaboys@email.com", secondMedicalRecordTest, secondFireStationTest);
        firstPersonTestList.add(secondPersonTest);
        MedicalRecord medicalRecordTest = new MedicalRecord("Jo", "Bode", "03/06/2012", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1510 Culver St", "3");
        Person personTest = new Person("Jo", "Bode", "1510 Culver St", "Culver", "97451", "841-873-6512", "jaboyd@email.com", medicalRecordTest, fireStationTest);
        personTestList.add(personTest);
        when(personRepository.findByAddress("1509 Culver St")).thenReturn(firstPersonTestList);
        when(personRepository.findByAddress("1510 Culver St")).thenReturn(personTestList);
        List<HomePerson> homePersonListTest = new ArrayList<>();
        List<HomePerson> secondHomePersonListTest = new ArrayList<>();
        List<Home> homeListTest = new ArrayList<>();
        HomePerson firstHomePersonTest = new HomePerson(firstPersonTest.getLastName(),firstPersonTest.getFirstName(),firstPersonTest.getPhone(),firstPersonTest.getMedicalRecord().getBirthdate(),firstPersonTest.getMedicalRecord().getMedications(),firstPersonTest.getMedicalRecord().getAllergies());
        homePersonListTest.add(firstHomePersonTest);
        HomePerson secondHomePersonTest = new HomePerson(secondPersonTest.getLastName(),secondPersonTest.getFirstName(),secondPersonTest.getPhone(),secondPersonTest.getMedicalRecord().getBirthdate(),secondPersonTest.getMedicalRecord().getMedications(),secondPersonTest.getMedicalRecord().getAllergies());
        homePersonListTest.add(secondHomePersonTest);
        Home homeTest = new Home(firstPersonTest.getAddress(),homePersonListTest);
        homeListTest.add(homeTest);
        HomePerson homePersonTest = new HomePerson(personTest.getLastName(),personTest.getFirstName(),personTest.getPhone(),personTest.getMedicalRecord().getBirthdate(),personTest.getMedicalRecord().getMedications(),personTest.getMedicalRecord().getAllergies());
        secondHomePersonListTest.add(homePersonTest);
        homeTest = new Home(personTest.getAddress(),secondHomePersonListTest);
        homeListTest.add(homeTest);

        assertEquals(homeListTest,personDao.getHomeByStation("1"));
    }

    @Test
    void personInfoTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        when(personRepository.findByFirstNameAndLastName("John","Boyd")).thenReturn(firstPersonTest);
        PersonInfo personInfotest = new PersonInfo(firstPersonTest.getLastName(),firstPersonTest.getMedicalRecord().getBirthdate(),firstPersonTest.getEmail(),firstPersonTest.getMedicalRecord().getMedications(),firstPersonTest.getMedicalRecord().getAllergies());

        assertEquals(personInfotest,personDao.personInfo("John","Boyd"));

    }

    @Test
    void getEmailByCityTest() {

    }
}