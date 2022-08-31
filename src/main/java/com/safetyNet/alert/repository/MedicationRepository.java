package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, Long> {
List<Medication> findByMedicalRecordFirstNameAndMedicalRecordLastName(String firstName, String lastName);
}
