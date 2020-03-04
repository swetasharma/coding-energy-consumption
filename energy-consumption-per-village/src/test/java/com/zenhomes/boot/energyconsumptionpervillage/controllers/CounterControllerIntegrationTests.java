package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.services.CounterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = CounterController.class)
public class CounterControllerIntegrationTests {

    /**
     * spring boot replaces the real bean with mock
     */
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CounterService counterService;

    @Test
    public void contextLoads(){
        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(counterService);
    }

    @Test
    public void verifyHTTPRequestPOSTCounterCallback() throws Exception {
        this.mockMvc.perform( post("/counter_callback").contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    public void verifyHTTPRequestGETEnergyConsumptionReport() throws Exception {
        this.mockMvc.perform( get("/consumption_report?duration=24h").contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    public void getEnergyConsumption() throws Exception{
        List<EnergyConsumption> energyConsumptionsList = new ArrayList<>();

        EnergyConsumption energyConsumption = new EnergyConsumption();
        energyConsumption.setVillage_name("Bhayander");
        energyConsumption.setConsumption(6000);
        energyConsumptionsList.add(energyConsumption);

        Map<String, List<EnergyConsumption>> energyConsumptionReport = new HashMap<>();
        energyConsumptionReport.put("villages", energyConsumptionsList);
        given(this.counterService.getEnergyConsumptionReport()).willReturn(energyConsumptionReport);
        this.mockMvc.perform(get("/consumption_report?duration=24h").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'villages':[{'village_name':'Bhayander', 'amount':6000}]}"));
    }
}
