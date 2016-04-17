package com.betvictor.action_monitor.services;

import com.betvictor.action_monitor.domain.Person;

import javax.transaction.Transactional;

public interface PersonService {
    Person findById(Long id);

    Iterable<Person> findAll();

    Person add(Person entity);

    Person update(Person entity);

    void delete(Person entity);

    void deleteAll();
}
