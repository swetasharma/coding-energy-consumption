package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import com.zenhomes.boot.energyconsumptionpervillage.repositories.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService{

    @Autowired
    private VillageRepository villageRepository;


    public void save(Village village) {
        villageRepository.save(village);
    }


    public Village findById(Long id) {
        return villageRepository.findById(id);
    }

}
