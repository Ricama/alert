package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.dao.MedicalRecordDao;
//import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.*;
import com.safetyNet.alert.repository.AllergyRepository;
import com.safetyNet.alert.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(classes = AlertApplication.class)
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MedicalRecordDao medicalRecordDao;


    @Test
    void postMedicalRecords() throws Exception {
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
        );
    }

    @Test
    void putMedicalRecords() throws Exception {
        Medication medication = new Medication("Test");
        Allergy allergy = new Allergy("Test");
        List<Medication> medicationList = new ArrayList<>();
        List<Allergy> allergyList = new ArrayList<>();
        medicationList.add(medication);
        allergyList.add(allergy);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("Test", "Test", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstFireStationTest);
        MedicalRecord medicalRecord = new MedicalRecord("Test", "Test", "02/18/2011", medicationList, allergyList,firstPersonTest);
        medicalRecordDao.create(medicalRecord);
        mockMvc.perform(put("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"birthdate\":\"02/18/2012\",\"medication\":\"[\"aznol:350mg\"]\", \"allergies\":[\"nillacilan\"] }")
        );
        medicalRecordDao.deleteMedical("Test", "Test");
    }

    @Test
    void deleteMedicalRecords() throws Exception {
        Medication medication = new Medication("Test");
        Allergy allergy = new Allergy("Test");
        List<Medication> medicationList = new ArrayList<>();
        List<Allergy> allergyList = new ArrayList<>();
        medicationList.add(medication);
        allergyList.add(allergy);
        FireStation firstFireStationTest = new FireStation("1509 Culver St", "3");
        Person firstPersonTest = new Person("Test", "Test", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboy@email.com", firstFireStationTest);
        MedicalRecord medicalRecord = new MedicalRecord("Test", "Test", "02/18/2011", medicationList, allergyList,firstPersonTest);
        medicalRecordDao.create(medicalRecord);
        mockMvc.perform(delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"birthdate\":\"02/18/2012\"}")
        );
    }
}