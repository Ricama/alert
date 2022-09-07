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

    /**
     * Mapping post medicalRecord
     */
    @PostMapping
     public MedicalRecord postMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        logger.info("POST MedicalRecord executed");
        logger.error("POST MedicalRecord.(MedicalRecord :"+medicalRecord.toString()+"");
      return medicalRecordDao.create(medicalRecord);
    }

    /**
     * Mapping put medicalRecord
     */
    @PutMapping
     public MedicalRecord putMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        logger.info("PUT MedicalRecord executed");
        logger.error("PUT MedicalRecord.(MedicalRecord :"+medicalRecord.toString()+"");
        return medicalRecordDao.updateMedical(medicalRecord);
    }

    /**
     * Mapping delete medicalRecord
     */
    @DeleteMapping
     public MedicalRecord deleteMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        logger.info("DELETE MedicalRecord executed");
        logger.error("DELETE MedicalRecord.(MedicalRecord :"+medicalRecord.toString()+"");
        return medicalRecordDao.deleteMedical(medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

}
