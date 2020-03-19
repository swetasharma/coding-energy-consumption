package com.zenhomes.boot.backendprocess.services;

import com.zenhomes.boot.backendprocess.dao.CounterDao;
import com.zenhomes.boot.backendprocess.dao.CounterQueueDao;
import com.zenhomes.boot.backendprocess.dao.VillageDao;
import com.zenhomes.boot.backendprocess.dto.CounterCallbackResponse;
import com.zenhomes.boot.backendprocess.dto.CounterRegister;
import com.zenhomes.boot.backendprocess.dto.EnergyConsumption;
import com.zenhomes.boot.backendprocess.models.Counter;
import com.zenhomes.boot.backendprocess.models.CounterQueue;
import com.zenhomes.boot.backendprocess.models.Village;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class CounterService{

    private static final Logger logger = LoggerFactory.getLogger(CounterService.class);

    private String url = "https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/";

    @Autowired
    private TaskExecutor taskExecutor;

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

    @Scheduled(cron = "0 43 19 * * ?")
    @Async("threadPoolTaskExecutor")
    public void processCounterData() throws IOException {

    List<CounterQueue> counterQueueList = new ArrayList<>();
    long startTime = System.nanoTime();

    while(true){

        //System.out.println("START Execute method asynchronously." + Thread.currentThread().getName());
        counterQueueList = counterQueueDao.findAll();

        if(counterQueueList.size() <= 0){
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            long totalTimeInSeconds = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
            System.out.println(totalTimeInSeconds+" SECONDS ");
            break;
        }

        for(CounterQueue counterQueue : counterQueueList)
        {
            try {

                Thread.sleep(115);

                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("START Execute method asynchronously." + Thread.currentThread().getName());
                        //Here we are getting village name and id by hitting external url
                        Village village = this.getVillageDetails(counterQueue.getCounterId());
                        if(villageDao.isVillageExists(Long.parseLong(village.getId()))){
                            villageDao.updateName(new Village(village.getId(), village.getName()));
                        }else{
                            villageDao.save(new Village (village.getId(), village.getName()));
                        }

                        Counter counter = new Counter();
                        counter.setCounterId(counterQueue.getCounterId());
                        counter.setAmount(counterQueue.getAmount());
                        counter.setVillageId(Long.parseLong(village.getId()));
                        counter.setCreatedDate(counterQueue.getCreatedDate());

                        double lastRecordAmount = this.getLastRecordToCalculateNetAmount(counter);
                        if(lastRecordAmount == 0.0){
                            counter.setNetAmount(counterQueue.getAmount());
                        }else{
                            counter.setNetAmount(counterQueue.getAmount() - lastRecordAmount);
                        }

                        counterDao.save(counter);
                        counterQueueDao.update(true, counterQueue.getId());
                        System.out.println("End  Execute method asynchronously." + Thread.currentThread().getName());
                    }

                    private double getLastRecordToCalculateNetAmount(Counter counter) {
                        return counterDao.getLastRecordToCalculateNetAmount(counter);
                    }

                    private Village getVillageDetails(long counterId) {
                        //Convert village endpoint from json to POJO
                        restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter() );
                        CounterCallbackResponse counterCallbackResponse = restTemplate.getForObject(url.concat(String.valueOf(counterId)), CounterCallbackResponse.class);
                        return counterCallbackResponse.getVillage();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}

    /**
     * Hits the external link and get the village name and village id
     */
    /*private Village getVillageDetails(long counterId) throws IOException
    {
        //Convert village endpoint from json to POJO
        restTemplate.getMessageConverters().add( new MappingJackson2HttpMessageConverter() );
        CounterCallbackResponse counterCallbackResponse = restTemplate.getForObject(url.concat(String.valueOf(counterId)), CounterCallbackResponse.class);
        return counterCallbackResponse.getVillage();
    }*/

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
   /* public double getLastRecordToCalculateNetAmount(Counter counter){
        return counterDao.getLastRecordToCalculateNetAmount(counter);
    }*/

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
