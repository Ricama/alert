package com.safetyNet.alert.dao;


import com.safetyNet.alert.model.Person;
import com.safetyNet.alert.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PersonsDaoImpl implements PersonDao {
    @Autowired
    PersonRepository personRepository;


    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Person personToUpdate = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        personToUpdate.setAddress(person.getAddress());
        personToUpdate.setCity(person.getCity());
        personToUpdate.setZip(person.getZip());
        personToUpdate.setPhone(person.getPhone());
        personToUpdate.setEmail(person.getEmail());

        return personRepository.save(personToUpdate);
    }


    @Override
    public Person delete(String firstName, String lastName) {
Person personToDelete = personRepository.findByFirstNameAndLastName(firstName,lastName);
         personRepository.delete(personToDelete);
         return null;
    }
}
