package com.zenhomes.boot.backendprocess.dao;

import com.zenhomes.boot.backendprocess.models.Counter;

import java.util.List;
import java.util.Map;

public interface CounterDao {
    void save(Counter counter);
    List<Map<String,Object>> consumptionReport();
    double getLastRecordToCalculateNetAmount(Counter counter);
}
