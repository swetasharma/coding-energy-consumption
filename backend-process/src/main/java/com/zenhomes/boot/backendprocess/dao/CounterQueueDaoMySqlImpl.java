package com.zenhomes.boot.backendprocess.dao;

import com.zenhomes.boot.backendprocess.models.CounterQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CounterQueueDaoMySqlImpl implements CounterQueueDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(CounterQueue counterQueue) {
        jdbcTemplate.update("INSERT INTO counterqueue(counterId, amount, createdDate) VALUES (?,?,?)",
                counterQueue.getCounterId(), counterQueue.getAmount(), LocalDateTime.now());
    }

    /**
     * Get the list of unprocessed data
     * @return
     */
    public List<CounterQueue> findAll() {
        String sql = " SELECT id, counterId, amount, createdDate FROM counterqueue WHERE processed = 0 ORDER BY createdDate LIMIT 0, 5 ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CounterQueue.class));
    }

    /**
     * update the processed flag to 1
     */
    public void update(boolean flag, long id) {
        jdbcTemplate.update(" UPDATE counterqueue SET processed = ? WHERE id = ?", flag, id);
    }

}
