package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VillageRepositoryUnitTests {

    @Autowired
    private VillageDaoMySqlImpl villageDaoMySql;

    @Test
    public void findById(){
        //given
        Village village = new Village();
        village.setId("1");
        village.setName("Jaipur");

        //when
        Village villageActualRecord = villageDaoMySql.findById(Long.parseLong(village.getId()));

        //then
        assertNotNull(villageActualRecord);
        assertEquals(village.getName(), villageActualRecord.getName());
    }
}
