package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;

import java.util.List;
import java.util.Map;

public interface CounterDao {
    void save(Counter counter);
    List<Map<String,Object>> consumptionReport();
    double getLastRecordToCalculateNetAmount(Counter counter);
}
