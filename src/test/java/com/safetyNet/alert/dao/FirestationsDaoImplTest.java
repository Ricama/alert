package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.*;
import com.safetyNet.alert.model.dao.FirestationDao;
import com.safetyNet.alert.model.dao.FirestationsDaoImpl;
import com.safetyNet.alert.repository.FireStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FirestationsDaoImplTest {

    FirestationDao firestationDao;

    @Mock
    private FireStationRepository fireStationRepository;

    @BeforeEach
    private void setup() {
        firestationDao = new FirestationsDaoImpl(fireStationRepository);
    }

    @Test
    void createTest() {
        FireStation fireStation = new FireStation("1509 Culver St", "3");
        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        assertAll(
                () -> assertEquals(fireStation.getAddress(), firestationDao.create(fireStation).getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.create(fireStation).getStation())
        );
    }

    @Test
    void updateTest() {
        FireStation fireStation = new FireStation("1509 Culver St", "3");
        FireStation fireStations = new FireStation("1509 Culver St", "4");
        when(fireStationRepository.findFirstByAddress("1509 Culver St")).thenReturn(fireStations);

        assertAll(
                () -> assertEquals(fireStation.getAddress(), firestationDao.update(fireStation).getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.update(fireStation).getStation())
        );
    }

    @Test
    void deleteTest() {
        FireStation fireStation = new FireStation("1509 Culver St", "3");
        when(fireStationRepository.findByStation("1")).thenReturn(fireStation);
        when(fireStationRepository.findByStation("2")).thenReturn(fireStation);
        when(fireStationRepository.findByStation("3")).thenReturn(fireStation);
        when(fireStationRepository.findByStation("4")).thenReturn(fireStation);
        when(fireStationRepository.findFirstByAddress("1509 Culver St")).thenReturn(fireStation);

        assertAll(
                () -> assertEquals(fireStation.getAddress(), firestationDao.delete("1").getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.delete("1").getStation()),
                () -> assertEquals(fireStation.getAddress(), firestationDao.delete("2").getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.delete("2").getStation()),
                () -> assertEquals(fireStation.getAddress(), firestationDao.delete("3").getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.delete("3").getStation()),
                () -> assertEquals(fireStation.getAddress(), firestationDao.delete("4").getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.delete("4").getStation()),
                () -> assertEquals(fireStation.getAddress(), firestationDao.delete("1509 Culver St").getAddress()),
                () -> assertEquals(fireStation.getStation(), firestationDao.delete("1509 Culver St").getStation())
        );
    }
}