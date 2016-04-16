package com.betvictor.action_monitor.services;

import com.betvictor.action_monitor.domain.Person;

import javax.transaction.Transactional;

public interface PersonService {
    Person findById(Long id);

    Iterable<Person> findAll();

    Person addPerson(Person entity);

    Person updatePerson(Person entity);

    void deletePerson(Person entity);

    @Transactional
    void deleteAll();
}
