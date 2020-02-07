package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import com.zenhomes.boot.energyconsumptionpervillage.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService {

    @Autowired
    VillageRepository villageRepository;

    public void addVillage(Village village){
        villageRepository.save(village.getVillageName());
    }

}
