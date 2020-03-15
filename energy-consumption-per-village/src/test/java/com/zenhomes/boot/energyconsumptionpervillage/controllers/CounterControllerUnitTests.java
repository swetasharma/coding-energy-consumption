package com.zenhomes.boot.energyconsumptionpervillage.controllers;
import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.dto.EnergyConsumption;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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

        Map<String, List<EnergyConsumption>> actualResult = counterController.consumptionReport();

        List<EnergyConsumption> EnergyConsumptionList = new ArrayList<>();
        Map<String, List<EnergyConsumption>> expectedResult = new HashMap<>();
        expectedResult.put("villages", EnergyConsumptionList);

        assertEquals(1, actualResult.entrySet().size());
        assertEquals(true, actualResult.entrySet().iterator().hasNext());
        assertNull(actualResult.get("villages"));
        assertEquals(expectedResult.get("villages"), actualResult.getOrDefault("villages", actualResult.get("villages")));
        assertEquals(1, actualResult.size());
        assertEquals(true, actualResult.get("villages").contains("counter_id"));
        assertEquals(true, actualResult.get("villages").contains("amount"));

    }

    @Test
    public void testSuccessConsumptionReport() throws Exception{
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumptionReport();
        //compare actual result and expected result by using assert equals
    }

    @Test
    public void testEmptyConsumptionReport() throws Exception{
        CounterController counterController = new CounterController();
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumptionReport();
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
        Map<String, List<EnergyConsumption>> actualResult = counterController.consumptionReport();
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

    //understand how you can use this
    @Test
    void contains_basic(){
        String str = "abcdefgh";
        boolean result = str.contains("ijk");
        assertFalse(result);
    }

    @Test
    void toUpperCaseBasic(){
        String str = "abcd";
        String result = str.toUpperCase();


    }
}
