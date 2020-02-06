package com.zenhomes.boot.energyconsumptionpervillage.repository;

import com.zenhomes.boot.energyconsumptionpervillage.model.Village;
import org.springframework.data.repository.CrudRepository;

public interface VillageRepository extends CrudRepository<Village, Long> {
}
