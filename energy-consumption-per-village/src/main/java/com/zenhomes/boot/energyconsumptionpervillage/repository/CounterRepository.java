package com.zenhomes.boot.energyconsumptionpervillage.repository;

import com.zenhomes.boot.energyconsumptionpervillage.model.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Long> {
}
