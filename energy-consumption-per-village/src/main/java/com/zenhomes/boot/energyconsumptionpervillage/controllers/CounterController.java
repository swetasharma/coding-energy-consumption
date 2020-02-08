package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
/**
 * This class is used to create the APIs which will be exposed to the clients
 */
public class CounterController {
    /**
     * get bean for counter service
     */
    @Autowired
    CounterService counterService;

    /**
     * Create counter,
     * and returns the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created queue
     * @return the HTTP 201[CREATED] along with a LocationHeader containing the locations of newly created counter
     */
    @PostMapping("/counter_callback")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCounterCallback(@RequestBody CounterRegister counterRegister) {
        counterService.save(counterRegister);
    }

    @GetMapping("/consumption_report?duration=24h")
    public Map<String, EnergyConsumption> consumption_report(){
        return null;
    }
}
