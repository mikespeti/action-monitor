package com.betvictor.action_monitor.services.impl;

import com.betvictor.action_monitor.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betvictor.action_monitor.repositories.PersonRepository;
import com.betvictor.action_monitor.services.PersonService;

import javax.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void setProductRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(Long id){
        return personRepository.findOne(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person addPerson(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    @Transactional
    public Person updatePerson(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    @Transactional
    public void deletePerson(Person entity) {
        personRepository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteAll() {
        personRepository.deleteAll();
    }
}
