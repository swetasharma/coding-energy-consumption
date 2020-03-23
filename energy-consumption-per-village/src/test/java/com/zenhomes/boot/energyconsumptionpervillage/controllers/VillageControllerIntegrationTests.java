package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterCallbackResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VillageControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private VillageController villageController;

    @Test
    public void testGetVillageById() throws Exception {
        CounterCallbackResponse counterCallbackResponse =  new CounterCallbackResponse();
        counterCallbackResponse = testRestTemplate.getForObject("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1", CounterCallbackResponse.class);
        assertNotNull(counterCallbackResponse);
    }
}
