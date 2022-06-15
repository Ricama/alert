package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.MedicalRecord;
import org.springframework.data.repository.CrudRepository;

public interface MedicalRecordRepository extends CrudRepository<MedicalRecord,Long> {
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
}
