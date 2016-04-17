package com.betvictor.action_monitor.controllers;

import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.domain.Person;
import com.betvictor.action_monitor.services.PersonService;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
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
        Assert.assertNotNull(personService);
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
        p1 = personService.add(p1);
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(Joiner.on(",").join(Lists.newArrayList(p1))));

        // Adding another new Person, p2
        p2 = personService.add(p2);
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(Joiner.on(",").join(Lists.newArrayList(p1, p2))));

        personService.deleteAll();
        this.mockMvc.perform(get("/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

}
