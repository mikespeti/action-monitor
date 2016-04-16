package com.betvictor.action_monitor.repositories;

import com.betvictor.action_monitor.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mikes on 2016. 04. 15..
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
}
