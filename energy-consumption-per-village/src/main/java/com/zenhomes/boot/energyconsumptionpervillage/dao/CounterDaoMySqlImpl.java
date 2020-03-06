package com.zenhomes.boot.energyconsumptionpervillage.dao;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class CounterDaoMySqlImpl implements CounterDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Counter counter) {
        jdbcTemplate.update("INSERT INTO counter(counterId, villageId, amount, createdDate) VALUES (?,?,?,?)",
                counter.getCounterId(), counter.getVillageId(), counter.getAmount(), LocalDateTime.now(), new BeanPropertyRowMapper<Counter>(Counter.class));
    }

    public List<Map<String,Object>> consumptionReport(){
        List<Map<String,Object>> energyConsumption =
                jdbcTemplate.queryForList("select village.villageName, sum(counter.amount) amount" +
                        "FROM counter" +
                        "LEFT JOIN village ON counter.villageId = village.id" +
                        "WHERE createdDate >= date_sub(now(), interval 24 hour)" +
                        "GROUP BY village.id");
        return energyConsumption;
    }
}
