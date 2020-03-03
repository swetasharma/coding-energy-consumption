package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.dao.CounterDao;
import com.zenhomes.boot.energyconsumptionpervillage.dao.VillageDao;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class CounterService{

    private static final Logger logger = LoggerFactory.getLogger(CounterService.class);

    private String uri = "https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/";

    @Autowired
    private CounterDao counterDao;

    @Autowired
    private VillageDao villageDao;

    @Autowired
    private RestTemplate restTemplate;

    public void save(CounterRegister counterRegister) {
        if(isAmountValid(counterRegister.getAmount())){
            //Here we are getting village name and id by hitting external url
            Village village = this.getVillageDetails(counterRegister.getCounter_id());
            if(villageDao.isVillageExists(village.getId())){
                villageDao.updateVillageName(new Village(village.getId(), village.getVillageName()));
            }else{
                villageDao.save(new Village (village.getId(), village.getVillageName()));
            }
            Counter counter = new Counter();
            counter.setCounterId(counterRegister.getCounter_id());
            counter.setAmount(counterRegister.getAmount());
            counter.setVillageId(village.getId());
            counter.setCreatedDate(LocalDateTime.now());
            counterDao.save(counter);
            //Long counterId = counterDao.save(counter);
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
        return amount <= 0;
    }

    /**
     * Hits the external link and get the village name and village id
     */
    private Village getVillageDetails(long counterId)
    {
        Assert.notNull(counterId, "Counter Id cannot be empty");
        logger.debug("Retrieving village data from :" + uri.concat(String.valueOf(counterId)));
        return restTemplate.getForObject(uri, Village.class, String.valueOf(counterId));
    }

    public Map<String, List<EnergyConsumption>> getEnergyConsumptionReport(){
        List<EnergyConsumption> energyConsumptionsList = new ArrayList<>();
        Iterator<Map<String, Object>> iterator = counterDao.consumptionReport().iterator();
        EnergyConsumption energyConsumption = null;
        while(iterator.hasNext()) {
            Map<String, Object> record = iterator.next();
            Set<Map.Entry<String, Object>> entrySet = record.entrySet();
            for(Map.Entry<String, Object> entry : entrySet){
                energyConsumption = new EnergyConsumption();
                energyConsumption.setVillage_name(entry.getKey());
                energyConsumption.setConsumption(Double.valueOf(entry.getValue().toString()).doubleValue());
                energyConsumptionsList.add(energyConsumption);
            }
        }

        Map<String, List<EnergyConsumption>> energyConsumptionReport = new HashMap<>();
        energyConsumptionReport.put("villages", energyConsumptionsList);

        return energyConsumptionReport;
    }
}
