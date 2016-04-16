package com.betvictor.action_monitor.services;

import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.domain.Person;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;


public class PersonServiceTest extends AbstractTest {

    @Autowired
    private PersonService personService;

    @Before
    public void smoakTest() {
        Assert.assertNotNull(personService);
    }

    @Test
    public void testAddPerson() {
        Person person = new Person("Peter");

        person = personService.addPerson(person);
        Assert.assertNotNull(person.getId());
    }

    @Test
    public void testUpdatePerson() {
        Person person = new Person("Peter");

        person = personService.addPerson(person);
        Assert.assertNotNull(person.getId());
        Assert.assertEquals("Peter", person.getName());

        person.setName("Andras");
        person = personService.updatePerson(person);
        Assert.assertEquals("Andras", person.getName());
    }


    @Test
    public void testDeletePerson() {
        Person person = new Person("Peter");

        person = personService.addPerson(person);
        Assert.assertNotNull(person.getId());
        Assert.assertEquals("Peter", person.getName());

        Long id = person.getId();
        personService.deletePerson(person);

        Assert.assertNull(personService.findById(id));
    }

    @Test
    public void testDeleteAllPerson() {
        Person p1 = new Person("Peter");
        Person p2 = new Person("Andras");
        Person p3 = new Person("Akos");

        personService.addPerson(p1);
        personService.addPerson(p2);
        personService.addPerson(p3);

        Assert.assertEquals(3, Lists.newArrayList(personService.findAll()).size());

        personService.deleteAll();
        Assert.assertEquals(0, Lists.newArrayList(personService.findAll()).size());
    }
}
