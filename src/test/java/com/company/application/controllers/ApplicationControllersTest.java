package com.company.application.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class ApplicationControllersTest {
    @InjectMocks
    private ApplicationControllers applicationControllers;

    @Test
    public void testShowHomePage() throws Exception {
        String viewName = "index";
        assertEquals(viewName,applicationControllers.showHomePage());
    }
}
