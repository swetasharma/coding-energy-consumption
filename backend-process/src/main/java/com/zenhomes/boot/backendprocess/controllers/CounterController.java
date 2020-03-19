package com.zenhomes.boot.backendprocess.controllers;

import com.zenhomes.boot.backendprocess.dto.CounterRegister;
import com.zenhomes.boot.backendprocess.dto.EnergyConsumption;
import com.zenhomes.boot.backendprocess.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
/**
 * This class is used to create the APIs which will be exposed to the clients
 */
public class CounterController {
    /**
     * get bean for counter servicsele
     */
    @Autowired
    CounterService counterService;

    /**
     * Create counter
     * @return the HTTP 201[CREATED]
     */
    @PostMapping("/counter_callback")
    public ResponseEntity<?> createCounterCallback(@Valid @RequestBody CounterRegister counterRegister) throws Exception{
        //counterService.save(counterRegister);
        for(int i = 0; i < 2000; i++){
            counterService.saveCounterQueueRecord(counterRegister, i);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/consumption_report", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<EnergyConsumption>> consumptionReport(){
        return counterService.getEnergyConsumptionReport();
    }

    @PostMapping("/processdata")
    public ResponseEntity<?> processdata() throws IOException {
        counterService.processCounterData();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
