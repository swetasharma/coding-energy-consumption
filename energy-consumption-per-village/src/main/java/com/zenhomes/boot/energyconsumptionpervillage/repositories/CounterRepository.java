package com.zenhomes.boot.energyconsumptionpervillage.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Long counterId, Long villageId, Double amount) {
        jdbcTemplate.update("INSERT INTO counter(counterId, villageId, amount) VALUES (?,?,?)",
                counterId, villageId, amount);
    }

}
