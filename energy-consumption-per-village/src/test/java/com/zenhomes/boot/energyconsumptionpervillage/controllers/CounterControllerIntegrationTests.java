package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CounterController.class)
@AutoConfigureMockMvc
public class CounterControllerIntegrationTests {

    /**
     * spring boot replaces the real bean with mock
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterController counterController;

    @Test
    public void contextLoads(){
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(counterController);
    }

    @Test
    public void verifyHTTPRequestPOSTCounterCallback() throws Exception {
        this.mockMvc.perform( post("/counter_callback").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void verifyHTTPRequestGETEnergyConsumptionReport() throws Exception {
        this.mockMvc.perform( get("/consumption_report?duration=24h").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getEnergyConsumptionReport() throws Exception{
        List<EnergyConsumption> energyConsumptionsList = new ArrayList<>();

        EnergyConsumption energyConsumption = new EnergyConsumption();
        energyConsumption.setVillage_name("Bhayander");
        energyConsumption.setConsumption(6000);
        energyConsumptionsList.add(energyConsumption);

        Map<String, List<EnergyConsumption>> energyConsumptionReport = new HashMap<>();
        energyConsumptionReport.put("villages", energyConsumptionsList);

        given(counterController.consumptionReport()).willReturn(energyConsumptionReport);

        mockMvc.perform(get("/consumption_report?duration=24h").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['villages']", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$['villages'][0].village_name", energyConsumption.village_name))
                .andExpect((ResultMatcher) jsonPath("$['villages'][0].consumption", energyConsumption.consumption));
    }
}