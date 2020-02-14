package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterControllerUnitTests {

    @Test
    public void testSuccessCreateCounterCallback(){
        //here you need to implement actual result and expected result
        // you need to return some json value which will help you to compare actual result and expected result and use assertEquals to compare expected and actual result

        CounterController counterController = new CounterController();
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.setCounter_id(1);
        counterRegister.setAmount(1000);
        counterController.createCounterCallback(counterRegister);
    }

    @Test
    public void testSuccessConsumptionReport(){
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();
        //compare actual result and expected result by using assert equals
    }

    @Test
    public void testEmptyConsumptionReport(){
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();
        List<EnergyConsumption> EnergyConsumptionList = new ArrayList<>();
        Map<String, List<EnergyConsumption>> expectedResult = new HashMap<>();
        expectedResult.put("villages", EnergyConsumptionList);
        assertEquals(expectedResult.get("villages"), actualResult.getOrDefault("villages", actualResult.get("villages")));
        assertEquals(expectedResult.size(), 0);
    }

    @Test
    public void testOneRecordOfConsumptionReport(){
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();

        List<EnergyConsumption> EnergyConsumptionList = new ArrayList<>();
        EnergyConsumption energyConsumption = new EnergyConsumption();
        energyConsumption.setVillage_name("Bhayander");
        energyConsumption.setConsumption(2000.00);
        EnergyConsumptionList.add(energyConsumption);

        Map<String, List<EnergyConsumption>> expectedResult = new HashMap<>();
        expectedResult.put("villages", EnergyConsumptionList);
        assertEquals(expectedResult.get("villages"), actualResult.getOrDefault("villages", actualResult.get("villages")));
        assertEquals(expectedResult.size(), 1);
    }



}
