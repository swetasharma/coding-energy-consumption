package com.zenhomes.boot.energyconsumptionpervillage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class EnergyConsumptionPerVillageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnergyConsumptionPerVillageApplication.class, args);
	}
}
