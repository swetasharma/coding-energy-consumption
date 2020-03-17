package com.zenhomes.boot.backendprocess.dao;

import com.zenhomes.boot.backendprocess.models.CounterQueue;

import java.util.List;

public interface CounterQueueDao {
    void save(CounterQueue counterQueue);
    List<CounterQueue> findAll();
    void update(boolean flag, long id);
}
