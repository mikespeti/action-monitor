package com.betvictor.action_monitor.repositories;

import com.betvictor.action_monitor.domain.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Long> {
}
