package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class CounterServiceUnitTests {

    @Autowired
    CounterService counterService;

    @Test
    public void saveCounterQueueRecord(){
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.setCounter_id(1);
        counterRegister.setAmount(1000);
        counterService.saveCounterQueueRecord(counterRegister);
    }

    @Test
    public void processCounterData(){
        CounterRegister counterRegisterFirstRecord = new CounterRegister();
        counterRegisterFirstRecord.setCounter_id(1);
        counterRegisterFirstRecord.setAmount(1000);
        counterService.saveCounterQueueRecord(counterRegisterFirstRecord);

        CounterRegister counterRegisterSecondRecord = new CounterRegister();
        counterRegisterSecondRecord.setCounter_id(1);
        counterRegisterSecondRecord.setAmount(2000);
        counterService.saveCounterQueueRecord(counterRegisterSecondRecord);

        CounterRegister counterRegisterThirdRecord = new CounterRegister();
        counterRegisterThirdRecord.setCounter_id(1);
        counterRegisterThirdRecord.setAmount(3000);
        counterService.saveCounterQueueRecord(counterRegisterThirdRecord);

        try {
            counterService.processCounterData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
