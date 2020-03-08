package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VillageController {
    @GetMapping(value = "/villages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Village> getVillages(){
        return null;
    }
}
