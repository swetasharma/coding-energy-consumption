package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import org.junit.jupiter.api.Test;

public class CounterServiceControllerUnitTests {

    @Test
    public void testSuccessCreateCounterCallback(){
        CounterController counterController =  new CounterController();
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.setCounter_id(1);
        counterRegister.setAmount(1000);
        counterController.createCounterCallback(counterRegister);
    }



}
