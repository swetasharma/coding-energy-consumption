package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.exceptions.VillageNotFoundException;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import com.zenhomes.boot.energyconsumptionpervillage.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VillageController {
    @Autowired
    private VillageService villageService;

    @GetMapping(value = "/villages/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Village> getVillageById(@PathVariable long id) throws VillageNotFoundException {
        Village village =  villageService.findById(id);
        return new ResponseEntity<>(village, HttpStatus.FOUND);
    }
}
