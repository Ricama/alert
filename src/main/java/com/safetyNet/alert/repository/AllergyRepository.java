package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Allergy;
import com.safetyNet.alert.model.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllergyRepository extends CrudRepository<Allergy, Long> {
    List<Allergy> findByMedicalRecordFirstNameAndMedicalRecordLastName(String firstName, String lastName);
}
