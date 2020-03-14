package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.CounterQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class CounterQueueDaoMySqlImpl implements CounterQueueDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(CounterQueue counterQueue) {
        jdbcTemplate.update("INSERT INTO counterqueue(counterId, amount, createdDate) VALUES (?,?,?,?,?)",
                counterQueue.getCounterId(), counterQueue.getAmount(), LocalDateTime.now());
    }
}
