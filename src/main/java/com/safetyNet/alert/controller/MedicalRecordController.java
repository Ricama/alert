package com.safetyNet.alert.controller;

import com.safetyNet.alert.model.dao.MedicalRecordDao;
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
        try {
            logger.info("POST MedicalRecord executed");
            return medicalRecordDao.create(medicalRecord);
        }catch (Exception e ){
            logger.error("POST MedicalRecord.(Exception: "+e+" MedicalRecord :" + medicalRecord.toString() + "");
            return null;
        }
    }

    /**
     * Mapping put medicalRecord
     */
    @PutMapping
     public MedicalRecord putMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        try {
            logger.info("PUT MedicalRecord executed");
            return medicalRecordDao.updateMedical(medicalRecord);
        }catch (Exception e ){
            logger.error("PUT MedicalRecord.(Exception: "+e+" MedicalRecord :" + medicalRecord.toString() + "");
            return null;
        }
    }

    /**
     * Mapping delete medicalRecord
     */
    @DeleteMapping
     public MedicalRecord deleteMedicalRecords(@RequestBody MedicalRecord medicalRecord){
        try {
        logger.info("DELETE MedicalRecord executed");
        return medicalRecordDao.deleteMedical(medicalRecord.getFirstName(), medicalRecord.getLastName());
        }catch (Exception e ) {
            logger.error("DELETE MedicalRecord.(Exception: "+e+" MedicalRecord :"+medicalRecord.toString()+"");
            return null;
        }
    }

}
