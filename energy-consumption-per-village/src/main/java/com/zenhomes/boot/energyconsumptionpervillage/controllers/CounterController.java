package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void createCounterCallback(@Valid @RequestBody CounterRegister counterRegister) throws Exception{
        counterService.save(counterRegister);
    }

    @GetMapping(value = "/consumption_report?duration=24h", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<EnergyConsumption>> consumptionReport(){
        return counterService.getEnergyConsumptionReport();
    }
}
