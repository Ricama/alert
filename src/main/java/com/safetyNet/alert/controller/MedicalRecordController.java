package com.safetyNet.alert.controller;

import com.safetyNet.alert.dao.MedicalrecordsDao;
import com.safetyNet.alert.model.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {
    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

    private final MedicalrecordsDao medicalrecordsDao;

    MedicalRecordController(MedicalrecordsDao medicalrecordsDao){

        this.medicalrecordsDao = medicalrecordsDao;
    }


    @PostMapping
     public MedicalRecord postMedicalRecords(@RequestBody MedicalRecord medicalRecord){

      return medicalrecordsDao.create(medicalRecord);
    }

    @PutMapping
     public MedicalRecord putMedicalRecords(@RequestBody MedicalRecord medicalRecord){

        return medicalrecordsDao.updateMedical(medicalRecord);
    }

    @DeleteMapping
     public MedicalRecord deleteMedicalRecords(@RequestBody MedicalRecord medicalRecord){

        return medicalrecordsDao.deleteMedical(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

}
