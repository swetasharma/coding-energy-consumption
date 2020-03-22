package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CounterServiceUnitTests {

    @Autowired
    CounterService counterService;

    public void saveCounterQueueRecord(){
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.setCounter_id(1);
        counterRegister.setAmount(1000);
        counterService.saveCounterQueueRecord(counterRegister);
    }
}
