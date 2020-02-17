package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CounterControllerUnitTests {

    @Test
    public void testSuccessCreateCounterCallback() throws Exception{
        //here you need to implement actual result and expected result
        // you need to return some json value which will help you to compare actual result and expected result and use assertEquals to compare expected and actual result

        CounterController counterController = new CounterController();
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.setCounter_id(1);
        counterRegister.setAmount(1000);
        counterController.createCounterCallback(counterRegister);
    }

    @Test
    public void testSuccessConsumptionReport() throws Exception{
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();
        //compare actual result and expected result by using assert equals
    }

    @Test
    public void testEmptyConsumptionReport() throws Exception{
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();
        List<EnergyConsumption> EnergyConsumptionList = new ArrayList<>();
        Map<String, List<EnergyConsumption>> expectedResult = new HashMap<>();
        expectedResult.put("villages", EnergyConsumptionList);

        assertNull(actualResult.get("villages"));
        assertEquals(expectedResult.get("villages"), actualResult.getOrDefault("villages", actualResult.get("villages")));
        assertEquals(expectedResult.size(), actualResult.size());
    }

    @Test
    public void testSuccessOneRecordOfConsumptionReport() throws  Exception{
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumption_report();
        //here you need to write code for post call
        List<EnergyConsumption> EnergyConsumptionList = new ArrayList<>();
        EnergyConsumption energyConsumption = new EnergyConsumption();
        energyConsumption.setVillage_name("Bhayander");
        energyConsumption.setConsumption(2000.00);
        EnergyConsumptionList.add(energyConsumption);

        Map<String, List<EnergyConsumption>> expectedResult = new HashMap<>();
        expectedResult.put("villages", EnergyConsumptionList);

        assertNotNull(actualResult);
        assertEquals(expectedResult.get("villages"), actualResult.getOrDefault("villages", actualResult.get("villages")));
        assertEquals(expectedResult.size(), actualResult.size());

    }

    @Test
    public void postCounterCallbackWhenAmountIsNegativeShouldThrowException() throws Exception{

    }



}
