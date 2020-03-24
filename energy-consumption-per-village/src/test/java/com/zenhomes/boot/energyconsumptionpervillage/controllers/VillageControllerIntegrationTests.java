package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterCallbackResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class VillageControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetVillageUrl() throws Exception {
        CounterCallbackResponse counterCallbackResponse =  new CounterCallbackResponse();
        counterCallbackResponse = testRestTemplate.getForObject("https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1", CounterCallbackResponse.class);
        assertNotNull(counterCallbackResponse);
    }

    @Test
    public void testGetVillageById() throws Exception {
        mockMvc.perform(get("/villages/100")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}
