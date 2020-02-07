package com.zenhomes.boot.energyconsumptionpervillage;

import com.zenhomes.boot.energyconsumptionpervillage.repositories.VillageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EnergyConsumptionPerVillageApplicationTests {

	@Autowired
    VillageRepository villageRepository;

	@Test
	void contextLoads() {
	}

}
