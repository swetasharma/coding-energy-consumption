package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.CounterQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CounterQueueRepositoryUnitTests {

    @Autowired
    private CounterQueueDaoMySqlImpl counterQueueDaoMySql;

    @Test
    public void saveCounterQueue(){
        //given
        CounterQueue counterQueue = new CounterQueue();
        counterQueue.setId(1);
        counterQueue.setCounterId(1);
        counterQueue.setAmount(1000);
        counterQueue.setProcessed(Byte.parseByte("0"));
        counterQueue.setCreatedDate(LocalDateTime.now());

        //when
        counterQueueDaoMySql.save(counterQueue);

        //then
        List<CounterQueue> counterQueueList = new ArrayList<>();
        counterQueueList = counterQueueDaoMySql.findAll();

        assertNotNull(counterQueueList);
        assertEquals(1, counterQueueList.size());
        assertEquals(false, counterQueueList.isEmpty());
        assertEquals(1, counterQueueList.get(0).getId());
        assertEquals(1, counterQueueList.get(0).getCounterId());
        assertEquals(1000, counterQueueList.get(0).getAmount());
        assertEquals(false, counterQueueList.get(0).getProcessed());
    }

    @Test
    public void setProcessFlagCounterQueueRecord(){
        //given
        CounterQueue counterQueue = new CounterQueue();
        counterQueue.setId(1);
        counterQueue.setCounterId(1);
        counterQueue.setAmount(1000);
        counterQueue.setProcessed(Byte.parseByte("0"));
        counterQueue.setCreatedDate(LocalDateTime.now());

        //when
        counterQueueDaoMySql.save(counterQueue);
        counterQueueDaoMySql.update(true, counterQueue.getId());

        //then
        List<CounterQueue> counterQueueList = new ArrayList<>();
        counterQueueList = counterQueueDaoMySql.findAll();

        assertNotNull(counterQueueList);
        assertEquals(1, counterQueueList.size());
        assertEquals(false, counterQueueList.isEmpty());
        assertEquals(1, counterQueueList.get(0).getId());
        assertEquals(1, counterQueueList.get(0).getCounterId());
        assertEquals(1000, counterQueueList.get(0).getAmount());
        assertEquals(true, counterQueueList.get(0).getProcessed());
    }

    @Test
    public void findAll(){
        //given
        CounterQueue counterQueueFirstRecord = new CounterQueue();
        counterQueueFirstRecord.setId(1);
        counterQueueFirstRecord.setCounterId(1);
        counterQueueFirstRecord.setAmount(1000);
        counterQueueFirstRecord.setProcessed(Byte.parseByte("0"));
        counterQueueFirstRecord.setCreatedDate(LocalDateTime.now());

        CounterQueue counterQueueSecondRecord = new CounterQueue();
        counterQueueSecondRecord.setId(2);
        counterQueueSecondRecord.setCounterId(1);
        counterQueueSecondRecord.setAmount(2000);
        counterQueueSecondRecord.setProcessed(Byte.parseByte("0"));
        counterQueueSecondRecord.setCreatedDate(LocalDateTime.now());

        //when
        counterQueueDaoMySql.save(counterQueueFirstRecord);
        counterQueueDaoMySql.save(counterQueueSecondRecord);

        //then
        List<CounterQueue> counterQueueList = new ArrayList<>();
        counterQueueList = counterQueueDaoMySql.findAll();

        assertNotNull(counterQueueList);
        assertEquals(false, counterQueueList.isEmpty());
        assertEquals(2, counterQueueList.size());

        assertEquals(1, counterQueueList.get(0).getId());
        assertEquals(1, counterQueueList.get(0).getCounterId());
        assertEquals(1000, counterQueueList.get(0).getAmount());
        assertEquals(false, counterQueueList.get(0).getProcessed());

        assertEquals(2, counterQueueList.get(1).getId());
        assertEquals(1, counterQueueList.get(1).getCounterId());
        assertEquals(2000, counterQueueList.get(1).getAmount());
        assertEquals(false, counterQueueList.get(1).getProcessed());
    }
}
