package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.MedicalRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, Long> {
    MedicalRecord findByFirstNameAndLastName(String firstName, String lastName);
}
