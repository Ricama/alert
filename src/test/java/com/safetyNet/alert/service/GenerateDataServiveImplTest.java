package com.safetyNet.alert.service;

import com.safetyNet.alert.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GenerateDataServiveImplTest {

    GenerateDataService generateDataService;

    @Mock
    private FireStationRepository fireStationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private AllergyRepository allergyRepository;


    @BeforeEach
    private void setup() { generateDataService = new GenerateDataServiceImpl(fireStationRepository,personRepository,medicalRecordRepository,medicationRepository,allergyRepository);}

    @Test
    void generateData() {

    }
}
