package com.betvictor.action_monitor.controllers;

import com.google.common.base.Joiner;
import com.betvictor.action_monitor.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.betvictor.action_monitor.services.PersonService;


@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/healthCheck")
    public String healtCheck(){
        return "OK";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        Iterable<Person> persons=personService.findAll();
        return Joiner.on(",").join(persons);
    }
}
