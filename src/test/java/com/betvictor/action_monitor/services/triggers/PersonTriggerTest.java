package com.betvictor.action_monitor.services.triggers;


import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.configs.MockedTableChangeMessageProducerConfig;
import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.PersonService;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import({MockedTableChangeMessageProducerConfig.class})
public class PersonTriggerTest extends AbstractTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private TableChangeMessageProducer messageProducer;

    @Before
    public void setup(){
        Assert.assertNotNull(personService);
        Assert.assertNotNull(messageProducer);
    }

    @Test
    public void testAfterInsertTrigger() {
        personService.addPerson(new Person("Peter"));
        verify(messageProducer, times(1));
    }

    @Test
    public void testAfterUpdateTrigger() {
        Person p=personService.addPerson(new Person("Peter"));
        p.setName("Andras");

        personService.updatePerson(p);
        verify(messageProducer, times(2));
    }

    @Test
    public void testAfterDeleteTrigger() {
        Person p=personService.addPerson(new Person("Peter"));
        personService.deletePerson(p);
        verify(messageProducer, times(2));
    }
    @Test
    public void testAfterDeleteTrigger_deleteAll() {
        personService.addPerson(new Person("Peter"));
        personService.addPerson(new Person("Andras"));
        personService.addPerson(new Person("Akos"));
        personService.deleteAll();
        verify(messageProducer, times(6));
    }
}
