package com.betvictor.action_monitor.controllers;

import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class RestControllerTest extends AbstractTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PersonService personService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        Assert.assertNotNull(wac);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testHealthCheckPage() throws Exception {
        this.mockMvc.perform(get("/healthCheck"))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }

    @Test
    public void testFindAllPersonPage() throws Exception {
        Person p1 = new Person("Peter");
        Person p2 = new Person("Andras");

        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        // Adding one new Person, p1
        personService.addPerson(p1);
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person{id=1, name='Peter'}"));

        // Adding another new Person, p2
        personService.addPerson(p2);
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person{id=1, name='Peter'},Person{id=2, name='Andras'}"));

        personService.deleteAll();
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

}
