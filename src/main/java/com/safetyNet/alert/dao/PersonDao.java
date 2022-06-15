package com.safetyNet.alert.dao;

import com.safetyNet.alert.model.Person;

public interface PersonDao {
    Person create(Person persons);
    Person update(Person persons);
    Person delete(String firstName, String lastName);
}
