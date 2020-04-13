package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterCallbackResponse;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class VillageControllerIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetVillageUrl() {
        ResponseEntity<CounterCallbackResponse> responseEntity = restTemplate.getForEntity("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1", CounterCallbackResponse.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().getId());
        assertEquals("100", responseEntity.getBody().getVillage().getId());
        assertEquals("Villarriba", responseEntity.getBody().getVillage().getName());
    }

    @Test
    public void testGetVillageById() {
        ResponseEntity<Village> responseEntity = restTemplate.getForEntity("/villages/100", Village.class);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals("100", responseEntity.getBody().getId());
        assertEquals("Villarriba", responseEntity.getBody().getName());
    }

}
