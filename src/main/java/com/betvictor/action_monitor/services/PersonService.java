package com.betvictor.action_monitor.services;

import com.betvictor.action_monitor.domain.Person;

public interface PersonService {
    Iterable<Person> findAll();
}
