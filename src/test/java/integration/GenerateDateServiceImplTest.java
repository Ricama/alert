package integration;

import com.safetyNet.alert.AlertApplication;
import com.safetyNet.alert.repository.*;
import com.safetyNet.alert.service.GenerateDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AlertApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GenerateDateServiceImplTest {

    @Autowired
    FireStationRepository fireStationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    AllergyRepository allergyRepository;
    @Autowired
    GenerateDataService generateDataService;

    @Test
    public void generateDataTest() {
        fireStationRepository.deleteAll();
        personRepository.deleteAll();
        medicationRepository.deleteAll();
        allergyRepository.deleteAll();
        medicalRecordRepository.deleteAll();

        long count = personRepository.count();
        generateDataService.generateData();
        long newCount = personRepository.count();

        assertAll(
                () -> assertEquals(0, count),
                () -> assertEquals(23, newCount)
        );
    }
}
