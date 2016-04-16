package com.betvictor.action_monitor.services.impl;

import com.betvictor.action_monitor.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betvictor.action_monitor.repositories.PersonRepository;
import com.betvictor.action_monitor.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void setProductRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }
}
