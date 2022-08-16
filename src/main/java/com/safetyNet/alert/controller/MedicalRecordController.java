package com.safetyNet.alert.controller;

import com.safetyNet.alert.dao.MedicalRecordDao;
import com.safetyNet.alert.model.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalRecordDao medicalRecordDao;

    MedicalRecordController(MedicalRecordDao medicalRecordDao){

        this.medicalRecordDao = medicalRecordDao;
    }


    @PostMapping
     public MedicalRecord postMedicalRecords(@RequestBody MedicalRecord medicalRecord){

      return medicalRecordDao.create(medicalRecord);
    }

    @PutMapping
     public MedicalRecord putMedicalRecords(@RequestBody MedicalRecord medicalRecord){

        return medicalRecordDao.updateMedical(medicalRecord);
    }

    @DeleteMapping
     public MedicalRecord deleteMedicalRecords(@RequestBody MedicalRecord medicalRecord){

        return medicalRecordDao.deleteMedical(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

}
