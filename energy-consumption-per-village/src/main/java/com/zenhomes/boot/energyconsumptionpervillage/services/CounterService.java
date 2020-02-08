package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import com.zenhomes.boot.energyconsumptionpervillage.repositories.CounterRepository;
import com.zenhomes.boot.energyconsumptionpervillage.repositories.VillageRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CounterService{

    final String uri = "https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1";

    @Autowired
    private CounterRepository counterRepository;


    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    RestTemplate restTemplate;


    public void save(CounterRegister counterRegister) {
        if(isAmountValid(counterRegister.amount)){
            //Here we are getting village name and id by hitting external url
            Village village = this.getVillageDetails();
            if(villageRepository.isVillageExists(village.getId())){
                villageRepository.updateVillageName(new Village(village.getId(), village.getVillageName()));
            }else{
                villageRepository.save(new Village (village.getId(), village.getVillageName()));
            }
            Counter counter = new Counter();
            counter.setCounterId(counterRegister.counter_id);
            counter.setAmount(counterRegister.amount);
            counter.setVillageId(village.getId());
            counter.setCreatedDate(LocalDateTime.now());
            counterRepository.save(counter);
        }else {
            throw new IllegalArgumentException("Amount cannot be zero or negative or alphanumeric");
        }
    }

    /**
     * Validate amount attribute
     * check for amount should be less than or equal to zero and should also not be alphanumeric
     * @param amount
     * @return
     */
    public boolean isAmountValid(Double amount){
        if(amount <= 0){
            return false;
        }
        return true;
    }

    /**
     * Hits the external link and get the village name and village id
     */
    private Village getVillageDetails()
    {
        return restTemplate.getForObject(uri, Village.class);
    }

    public List<Map<String,Object>> getEnergyConsumptionReport(){
        List<EnergyConsumption> energyConsumptionsList = new ArrayList<>();
        Iterator<Map<String, Object>> iterator =
                counterRepository.consumptionReport().iterator();
        EnergyConsumption energyConsumption = new EnergyConsumption();
        return null;
    }
}
