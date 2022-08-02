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
        List<Medication> medicationTestList = new ArrayList<>();
        Medication medication = new Medication();
        medication.setMedications("Test");
        List<Allergy> allergyTestList = new ArrayList<>();
        Allergy allergy = new Allergy();
        allergy.setAllergies("Test");
        MedicalRecord medicalRecordTest = new MedicalRecord();
        medicalRecordTest.setFirstName("John");
        medicalRecordTest.setLastName("Bod");
        medicalRecordTest.setBirthdate("03/06/1984");
        medicalRecordTest.setMedications(medicationTestList);
        medicalRecordTest.setAllergies(allergyTestList);
        FireStation fireStationTest = new FireStation();
        fireStationTest.setAddress("1509 Culver St");
        fireStationTest.setStation("3");
        Person personTest = new Person();
        personTest.setFirstName("John");
        personTest.setLastName("Bod");
        personTest.setAddress("1509 Culver St");
        personTest.setCity("Culver");
        personTest.setZip("97451");
        personTest.setPhone("841-874-6512");
        personTest.setEmail("jaboy@email.com");
        personTest.setMedicalRecord(medicalRecordTest);
        personTest.setFireStation(fireStationTest);
        when(personRepository.save(personTest)).thenReturn(personTest);
        assertAll(
                () -> assertEquals(personTest.getFirstName(), personDao.create(personTest).getFirstName()),
                () -> assertEquals(personTest.getLastName(), personDao.create(personTest).getLastName()),
                () -> assertEquals(personTest.getAddress(), personDao.create(personTest).getAddress()),
                () -> assertEquals(personTest.getCity(), personDao.create(personTest).getCity()),
                () -> assertEquals(personTest.getZip(), personDao.create(personTest).getZip()),
                () -> assertEquals(personTest.getPhone(), personDao.create(personTest).getPhone()),
                () -> assertEquals(personTest.getEmail(), personDao.create(personTest).getEmail()),
                () -> assertEquals(personTest.getMedicalRecord(), personDao.create(personTest).getMedicalRecord()),
                () -> assertEquals(personTest.getFireStation(), personDao.create(personTest).getFireStation())

        );
    }

    @Test
    void updateTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", medicalRecordTest, fireStationTest);
        MedicalRecord medicalRecordTests = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation fireStationTests = new FireStation("1510 Culve St", "3");
        Person personTests = new Person("John", "Bod", "1510 Culve St", "Culve", "97450", "841-873-6511", "jaboyyd@email.com", medicalRecordTests, fireStationTests);
        when(personRepository.findByFirstNameAndLastName("John", "Bod")).thenReturn(personTest);
        when(personRepository.save(personTest)).thenReturn(personTest);
        assertAll(
                () -> assertEquals(personTests.getFirstName(), personDao.update(personTests).getFirstName()),
                () -> assertEquals(personTests.getLastName(), personDao.update(personTests).getLastName()),
                () -> assertEquals(personTests.getAddress(), personDao.update(personTests).getAddress()),
                () -> assertEquals(personTests.getCity(), personDao.update(personTests).getCity()),
                () -> assertEquals(personTests.getZip(), personDao.update(personTests).getZip()),
                () -> assertEquals(personTests.getPhone(), personDao.update(personTests).getPhone()),
                () -> assertEquals(personTests.getEmail(), personDao.update(personTests).getEmail())
        );
    }

    @Test
    void deleteTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord medicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation fireStationTest = new FireStation("1509 Culver St", "3");
        Person personTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", medicalRecordTest, fireStationTest);
        when(personRepository.findByFirstNameAndLastName("John", "Bod")).thenReturn(personTest);
        assertAll(
                () -> assertEquals(personTest.getFirstName(), personDao.delete("John", "Bod").getFirstName()),
                () -> assertEquals(personTest.getLastName(), personDao.delete("John", "Bod").getLastName()),
                () -> assertEquals(personTest.getAddress(), personDao.delete("John", "Bod").getAddress()),
                () -> assertEquals(personTest.getCity(), personDao.delete("John", "Bod").getCity()),
                () -> assertEquals(personTest.getZip(), personDao.delete("John", "Bod").getZip()),
                () -> assertEquals(personTest.getPhone(), personDao.delete("John", "Bod").getPhone()),
                () -> assertEquals(personTest.getEmail(), personDao.delete("John", "Bod").getEmail()),
                () -> assertEquals(personTest.getMedicalRecord(), personDao.delete("John", "Bod").getMedicalRecord()),
                () -> assertEquals(personTest.getFireStation(), personDao.delete("John", "Bod").getFireStation())

        );
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
        PersonByStation personByStationTest = new PersonByStation();
        personByStationTest.setFirstName("John");
        personByStationTest.setLastName("Bod");
        personByStationTest.setAddress("1509 Culver St");
        personByStationTest.setPhone("841-874-6512");
        List<PersonByStation> personByStationTestList = new ArrayList<>();
        personByStationTestList.add(personByStationTest);
        PersonByStationList personByStationListTest = new PersonByStationList();
        personByStationListTest.setPerson(personByStationTestList);
        personByStationListTest.setCountAdults(1);
        personByStationListTest.setCountChilds(0);

        assertAll(
                () -> assertEquals(personByStationListTest.getPerson().get(0).getFirstName(), personDao.getPersonByStation("1").getPerson().get(0).getFirstName()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getLastName(), personDao.getPersonByStation("1").getPerson().get(0).getLastName()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getAddress(), personDao.getPersonByStation("1").getPerson().get(0).getAddress()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getPhone(), personDao.getPersonByStation("1").getPerson().get(0).getPhone()),
                () -> assertEquals(personByStationListTest.getCountAdults(), personDao.getPersonByStation("1").getCountAdults()),
                () -> assertEquals(personByStationListTest.getCountChilds(), personDao.getPersonByStation("1").getCountChilds())
        );

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
        PersonByStationList personByStationListTest = new PersonByStationList(personByStationTestList, 0, 1);

        assertAll(
                () -> assertEquals(personByStationListTest.getPerson().get(0).getFirstName(), personDao.getPersonByStation("1").getPerson().get(0).getFirstName()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getLastName(), personDao.getPersonByStation("1").getPerson().get(0).getLastName()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getAddress(), personDao.getPersonByStation("1").getPerson().get(0).getAddress()),
                () -> assertEquals(personByStationListTest.getPerson().get(0).getPhone(), personDao.getPersonByStation("1").getPerson().get(0).getPhone()),
                () -> assertEquals(personByStationListTest.getCountAdults(), personDao.getPersonByStation("1").getCountAdults()),
                () -> assertEquals(personByStationListTest.getCountChilds(), personDao.getPersonByStation("1").getCountChilds())
        );

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
        Child childTest = new Child();
        childTest.setFirstName("Jo");
        childTest.setLastName("Bode");
        childTest.setBirthdate("03/06/2012");
        childListTest.add(childTest);
        adultListTest.add(firstPersonTest);
        ChildByAddress childByAddressListTest = new ChildByAddress();
        childByAddressListTest.setChild(childListTest);
        childByAddressListTest.setPersonList(adultListTest);

        assertAll(
                () -> assertEquals(childByAddressListTest.getChild().get(0).getFirstName(), personDao.childByAddress("1509 Culver St").getChild().get(0).getFirstName()),
                () -> assertEquals(childByAddressListTest.getChild().get(0).getLastName(), personDao.childByAddress("1509 Culver St").getChild().get(0).getLastName()),
                () -> assertEquals(childByAddressListTest.getChild().get(0).getBirthdate(), personDao.childByAddress("1509 Culver St").getChild().get(0).getBirthdate()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getFirstName(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getFirstName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getLastName(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getLastName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getAddress(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getAddress()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getPhone(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getPhone()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getEmail(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getEmail()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getMedicalRecord(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getMedicalRecord()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getFireStation(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getFireStation())
        );
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
        adultListTest.add(firstPersonTest);
        adultListTest.add(personTest);
        ChildByAddress childByAddressListTest = new ChildByAddress(childListTest, adultListTest);

        assertAll(

                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getFirstName(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getFirstName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getLastName(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getLastName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getAddress(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getAddress()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getPhone(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getPhone()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getEmail(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getEmail()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getMedicalRecord(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getMedicalRecord()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(0).getFireStation(), personDao.childByAddress("1509 Culver St").getPersonList().get(0).getFireStation()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getFirstName(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getFirstName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getLastName(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getLastName()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getAddress(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getAddress()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getPhone(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getPhone()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getEmail(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getEmail()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getMedicalRecord(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getMedicalRecord()),
                () -> assertEquals(childByAddressListTest.getPersonList().get(1).getFireStation(), personDao.childByAddress("1509 Culver St").getPersonList().get(1).getFireStation())
        );
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
        Child childTestTwo = new Child("John", "Bod", "03/06/2020");
        Child childTest = new Child("Jo", "Bode", "03/06/2012");
        childListTest.add(childTestTwo);
        childListTest.add(childTest);
        ChildByAddress childByAddressListTest = new ChildByAddress(childListTest, adultListTest);

        assertAll(
                () -> assertEquals(childByAddressListTest.getChild().get(0).getFirstName(), personDao.childByAddress("1509 Culver St").getChild().get(0).getFirstName()),
                () -> assertEquals(childByAddressListTest.getChild().get(0).getLastName(), personDao.childByAddress("1509 Culver St").getChild().get(0).getLastName()),
                () -> assertEquals(childByAddressListTest.getChild().get(0).getBirthdate(), personDao.childByAddress("1509 Culver St").getChild().get(0).getBirthdate()),
                () -> assertEquals(childByAddressListTest.getChild().get(1).getFirstName(), personDao.childByAddress("1509 Culver St").getChild().get(1).getFirstName()),
                () -> assertEquals(childByAddressListTest.getChild().get(1).getLastName(), personDao.childByAddress("1509 Culver St").getChild().get(1).getLastName()),
                () -> assertEquals(childByAddressListTest.getChild().get(1).getBirthdate(), personDao.childByAddress("1509 Culver St").getChild().get(1).getBirthdate())
        );
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

        assertAll(
                () -> assertEquals(phoneListTest.get(0), personDao.getPhoneByStation("1").get(0)),
                () -> assertEquals(phoneListTest.get(1), personDao.getPhoneByStation("1").get(1))
        );
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
        PersonByAddress firstPersonByAddressTest = new PersonByAddress();
        firstPersonByAddressTest.setStation(firstPersonTest.getFireStation().getStation());
        firstPersonByAddressTest.setFirstName(firstPersonTest.getFirstName());
        firstPersonByAddressTest.setLastName(firstPersonTest.getLastName());
        firstPersonByAddressTest.setPhone(firstPersonTest.getPhone());
        firstPersonByAddressTest.setBirthdate(firstPersonTest.getMedicalRecord().getBirthdate());
        firstPersonByAddressTest.setMedications(firstPersonTest.getMedicalRecord().getMedications());
        firstPersonByAddressTest.setAllergies(firstPersonTest.getMedicalRecord().getAllergies());
        personByAddressListTest.add(firstPersonByAddressTest);
        PersonByAddress personByAddressTest = new PersonByAddress(personTest.getFireStation().getStation(), personTest.getFirstName(), personTest.getLastName(), personTest.getPhone(), personTest.getMedicalRecord().getBirthdate(), personTest.getMedicalRecord().getMedications(), personTest.getMedicalRecord().getAllergies());
        personByAddressListTest.add(personByAddressTest);

        assertAll(
                () -> assertEquals(personByAddressListTest.get(0).getStation(), personDao.personByAddress("1509 Culver St").get(0).getStation()),
                () -> assertEquals(personByAddressListTest.get(0).getFirstName(), personDao.personByAddress("1509 Culver St").get(0).getFirstName()),
                () -> assertEquals(personByAddressListTest.get(0).getLastName(), personDao.personByAddress("1509 Culver St").get(0).getLastName()),
                () -> assertEquals(personByAddressListTest.get(0).getPhone(), personDao.personByAddress("1509 Culver St").get(0).getPhone()),
                () -> assertEquals(personByAddressListTest.get(0).getBirthdate(), personDao.personByAddress("1509 Culver St").get(0).getBirthdate()),
                () -> assertEquals(personByAddressListTest.get(0).getMedications(), personDao.personByAddress("1509 Culver St").get(0).getMedications()),
                () -> assertEquals(personByAddressListTest.get(0).getAllergies(), personDao.personByAddress("1509 Culver St").get(0).getAllergies()),
                () -> assertEquals(personByAddressListTest.get(1).getStation(), personDao.personByAddress("1509 Culver St").get(1).getStation()),
                () -> assertEquals(personByAddressListTest.get(1).getFirstName(), personDao.personByAddress("1509 Culver St").get(1).getFirstName()),
                () -> assertEquals(personByAddressListTest.get(1).getLastName(), personDao.personByAddress("1509 Culver St").get(1).getLastName()),
                () -> assertEquals(personByAddressListTest.get(1).getPhone(), personDao.personByAddress("1509 Culver St").get(1).getPhone()),
                () -> assertEquals(personByAddressListTest.get(1).getBirthdate(), personDao.personByAddress("1509 Culver St").get(1).getBirthdate()),
                () -> assertEquals(personByAddressListTest.get(1).getMedications(), personDao.personByAddress("1509 Culver St").get(1).getMedications()),
                () -> assertEquals(personByAddressListTest.get(1).getAllergies(), personDao.personByAddress("1509 Culver St").get(1).getAllergies())
        );
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
        HomePerson firstHomePersonTest = new HomePerson();
        firstHomePersonTest.setLastName(firstPersonTest.getLastName());
        firstHomePersonTest.setFirstName(firstPersonTest.getFirstName());
        firstHomePersonTest.setPhone(firstPersonTest.getPhone());
        firstHomePersonTest.setBirthdate(firstPersonTest.getMedicalRecord().getBirthdate());
        firstHomePersonTest.setMedications(firstPersonTest.getMedicalRecord().getMedications());
        firstHomePersonTest.setAllergies(firstPersonTest.getMedicalRecord().getAllergies());
        homePersonListTest.add(firstHomePersonTest);
        HomePerson secondHomePersonTest = new HomePerson(secondPersonTest.getLastName(), secondPersonTest.getFirstName(), secondPersonTest.getPhone(), secondPersonTest.getMedicalRecord().getBirthdate(), secondPersonTest.getMedicalRecord().getMedications(), secondPersonTest.getMedicalRecord().getAllergies());
        homePersonListTest.add(secondHomePersonTest);
        Home homeTest = new Home();
        homeTest.setAddress(firstPersonTest.getAddress());
        homeTest.setHome(homePersonListTest);
        homeListTest.add(homeTest);
        HomePerson homePersonTest = new HomePerson(personTest.getLastName(), personTest.getFirstName(), personTest.getPhone(), personTest.getMedicalRecord().getBirthdate(), personTest.getMedicalRecord().getMedications(), personTest.getMedicalRecord().getAllergies());
        secondHomePersonListTest.add(homePersonTest);
        homeTest = new Home(personTest.getAddress(), secondHomePersonListTest);
        homeListTest.add(homeTest);

        assertAll(
                () -> assertEquals(homeListTest.get(0).getAddress(), personDao.getHomeByStation("1").get(0).getAddress()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getLastName(), personDao.getHomeByStation("1").get(0).getHome().get(0).getLastName()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getFirstName(), personDao.getHomeByStation("1").get(0).getHome().get(0).getFirstName()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getPhone(), personDao.getHomeByStation("1").get(0).getHome().get(0).getPhone()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getBirthdate(), personDao.getHomeByStation("1").get(0).getHome().get(0).getBirthdate()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getMedications(), personDao.getHomeByStation("1").get(0).getHome().get(0).getMedications()),
                () -> assertEquals(homeListTest.get(0).getHome().get(0).getAllergies(), personDao.getHomeByStation("1").get(0).getHome().get(0).getAllergies()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getLastName(), personDao.getHomeByStation("1").get(0).getHome().get(1).getLastName()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getFirstName(), personDao.getHomeByStation("1").get(0).getHome().get(1).getFirstName()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getPhone(), personDao.getHomeByStation("1").get(0).getHome().get(1).getPhone()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getBirthdate(), personDao.getHomeByStation("1").get(0).getHome().get(1).getBirthdate()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getMedications(), personDao.getHomeByStation("1").get(0).getHome().get(1).getMedications()),
                () -> assertEquals(homeListTest.get(0).getHome().get(1).getAllergies(), personDao.getHomeByStation("1").get(0).getHome().get(1).getAllergies()),
                () -> assertEquals(homeListTest.get(1).getAddress(), personDao.getHomeByStation("1").get(1).getAddress()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getLastName(), personDao.getHomeByStation("1").get(1).getHome().get(0).getLastName()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getFirstName(), personDao.getHomeByStation("1").get(1).getHome().get(0).getFirstName()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getPhone(), personDao.getHomeByStation("1").get(1).getHome().get(0).getPhone()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getBirthdate(), personDao.getHomeByStation("1").get(1).getHome().get(0).getBirthdate()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getMedications(), personDao.getHomeByStation("1").get(1).getHome().get(0).getMedications()),
                () -> assertEquals(homeListTest.get(1).getHome().get(0).getAllergies(), personDao.getHomeByStation("1").get(1).getHome().get(0).getAllergies())
        );
    }

    @Test
    void personInfoTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        when(personRepository.findByFirstNameAndLastName("John", "Bod")).thenReturn(firstPersonTest);
        PersonInfo personInfotest = new PersonInfo();
        personInfotest.setLastName(firstPersonTest.getLastName());
        personInfotest.setBirthdate(firstPersonTest.getMedicalRecord().getBirthdate());
        personInfotest.setEmail(firstPersonTest.getEmail());
        personInfotest.setMedications(firstPersonTest.getMedicalRecord().getMedications());
        personInfotest.setAllergies(firstPersonTest.getMedicalRecord().getAllergies());

        assertAll(
                () -> assertEquals(personInfotest.getLastName(), personDao.personInfo("John", "Bod").getLastName()),
                () -> assertEquals(personInfotest.getBirthdate(), personDao.personInfo("John", "Bod").getBirthdate()),
                () -> assertEquals(personInfotest.getEmail(), personDao.personInfo("John", "Bod").getEmail()),
                () -> assertEquals(personInfotest.getMedications(), personDao.personInfo("John", "Bod").getMedications()),
                () -> assertEquals(personInfotest.getAllergies(), personDao.personInfo("John", "Bod").getAllergies())
        );

    }

    @Test
    void getEmailByCityTest() {
        List<Medication> medicationTestList = new ArrayList<>();
        List<Allergy> allergyTestList = new ArrayList<>();
        MedicalRecord firstMedicalRecordTest = new MedicalRecord("John", "Bod", "03/06/1984", medicationTestList, allergyTestList);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("John", "Bod", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstMedicalRecordTest, firstFireStationTest);
        List<String> email = new ArrayList<>();
        email.add(firstPersonTest.getEmail());
        when(personRepository.findEmailByCity("Culver")).thenReturn(email);

        assertEquals(email, personDao.getEmailByCity("Culver"));
    }
}