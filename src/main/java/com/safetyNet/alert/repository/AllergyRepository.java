package com.safetyNet.alert.repository;

import com.safetyNet.alert.model.Allergy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends CrudRepository<Allergy,Long> {
}
