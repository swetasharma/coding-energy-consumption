package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.dao.CounterDao;
import com.zenhomes.boot.energyconsumptionpervillage.dao.CounterQueueDao;
import com.zenhomes.boot.energyconsumptionpervillage.dao.VillageDao;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterCallbackResponse;
import com.zenhomes.boot.energyconsumptionpervillage.models.CounterQueue;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


@Service
public class CounterService{

    private static final Logger logger = LoggerFactory.getLogger(CounterService.class);

    private String url = "https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/";

    @Autowired
    private CounterDao counterDao;

    @Autowired
    private VillageDao villageDao;

    @Autowired
    private CounterQueueDao counterQueueDao;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public void save(CounterRegister counterRegister) throws IOException{
        //Here we are getting village name and id by hitting external url
        Village village = this.getVillageDetails(counterRegister.getCounter_id());
        if(villageDao.isVillageExists(Long.parseLong(village.getId()))){
            villageDao.updateName(new Village(village.getId(), village.getName()));
        }else{
            villageDao.save(new Village (village.getId(), village.getName()));
        }
        Counter counter = new Counter();
        counter.setCounterId(counterRegister.getCounter_id());
        counter.setAmount(counterRegister.getAmount());
        counter.setVillageId(Long.parseLong(village.getId()));
        counter.setCreatedDate(LocalDateTime.now());
        double lastRecordAmount = this.getLastRecordToCalculateNetAmount(counter);
        if(lastRecordAmount == 0.0){
            counter.setNetAmount(counterRegister.amount);
        }else{
            counter.setNetAmount(counterRegister.amount - lastRecordAmount);
        }
        counterDao.save(counter);
    }

    /**
     * Hits the external link and get the village name and village id
     */
    private Village getVillageDetails(long counterId) throws IOException
    {
        //Convert village endpoint from json to POJO
        restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter() );
        CounterCallbackResponse counterCallbackResponse = restTemplate.getForObject(url.concat(String.valueOf(counterId)), CounterCallbackResponse.class);
        return counterCallbackResponse.getVillage();
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
                energyConsumption.setVillage_name(entry.getValue().toString());
                energyConsumption.setConsumption(Double.valueOf(entry.getValue().toString()).doubleValue());
                energyConsumptionsList.add(energyConsumption);
            }
        }

        Map<String, List<EnergyConsumption>> energyConsumptionReport = new HashMap<>();
        energyConsumptionReport.put("villages", energyConsumptionsList);

        return energyConsumptionReport;
    }

    /**
     * Here return values 0.0 represents that it is a first record else ot gets last record to calculate the net amount
     * @param counter
     * @return
     */
    public double getLastRecordToCalculateNetAmount(Counter counter){
        return counterDao.getLastRecordToCalculateNetAmount(counter);
    }

    /**
     * saving all the counter data to queue
     */
    public void saveCounterQueueRecord(CounterRegister counterRegister){
        CounterQueue counterQueue = new CounterQueue();
        counterQueue.setCounterId(counterRegister.getCounter_id());
        counterQueue.setAmount(counterRegister.getAmount());
        counterQueueDao.save(counterQueue);
    }
}
