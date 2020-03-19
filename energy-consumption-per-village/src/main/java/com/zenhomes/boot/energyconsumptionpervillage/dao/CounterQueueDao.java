package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.CounterQueue;

import java.util.List;

public interface CounterQueueDao {
    void save(CounterQueue counterQueue);
    List<CounterQueue> findAll();
    void update(boolean flag, long id);
}
