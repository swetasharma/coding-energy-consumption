package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createCounterCallback(@Valid @RequestBody CounterRegister counterRegister, Errors errors) throws Exception{
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }else{
            counterService.save(counterRegister);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/consumption_report", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<EnergyConsumption>> consumptionReport(){
        return counterService.getEnergyConsumptionReport();
    }

    private String createErrorString(Errors errors) {
        return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
    }
}
