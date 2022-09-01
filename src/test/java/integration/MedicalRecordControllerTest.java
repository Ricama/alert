package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.dao.MedicalRecordDao;
//import com.safetyNet.alert.dao.PersonDao;
import com.safetyNet.alert.model.Allergy;
import com.safetyNet.alert.model.MedicalRecord;
import com.safetyNet.alert.model.Medication;
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
        List<Medication> medicationList = new ArrayList<>();
        List<Allergy> allergyList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord("Test", "Test", "02/18/2011", medicationList, allergyList);
        medicalRecordDao.create(medicalRecord);
        mockMvc.perform(put("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"birthdate\":\"02/18/2012\",\"medication\":\"[\"aznol:350mg\"]\", \"allergies\":[\"nillacilan\"] }")
        );
        medicalRecordDao.deleteMedical("Test", "Test");
    }

    @Test
    void deleteMedicalRecords() throws Exception {
        List<Medication> medicationList = new ArrayList<>();
        List<Allergy> allergyList = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord("Test", "Test", "02/18/2011", medicationList, allergyList);
        medicalRecordDao.create(medicalRecord);
        mockMvc.perform(delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Test\",\"lastName\":\"Test\",\"birthdate\":\"02/18/2012\"}")
        );
    }
}