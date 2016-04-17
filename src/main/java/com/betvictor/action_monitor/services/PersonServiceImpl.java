package com.betvictor.action_monitor.services;

import com.betvictor.action_monitor.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.betvictor.action_monitor.repository.PersonRepository;
import com.betvictor.action_monitor.services.PersonService;

import javax.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
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
    public Person add(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    @Transactional
    public Person update(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Person entity) {
        personRepository.delete(entity);
    }

    @Override
    @Transactional
    public void deleteAll() {
        personRepository.deleteAll();
    }
}
