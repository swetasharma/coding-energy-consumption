package com.zenhomes.boot.energyconsumptionpervillage.services;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VillageServiceUnitTests {

    @Autowired
    VillageService villageService;

    @Test
    public void save(){
        Village village = new Village();
        village.setId("1");
        village.setName("Jaipur");

        villageService.save(village);

        Village villageActualRecord = villageService.findById(Long.parseLong(village.getId()));
        assertNotNull(villageActualRecord);
        assertEquals(village.getName(), villageActualRecord.getName());
        assertEquals(village.getId(), villageActualRecord.getId());
    }

    @Test
    public void testGetVillageById(){
        Village village = villageService.findById(1);
        assertNotNull(village);
        assertEquals(1, village.getId());
    }
}
