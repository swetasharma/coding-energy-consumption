package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Village village1 = villageDaoMySql.findById(Long.parseLong(village.getId()));

        //then
        assertEquals(village1.getName(), village.getName());
    }
}
