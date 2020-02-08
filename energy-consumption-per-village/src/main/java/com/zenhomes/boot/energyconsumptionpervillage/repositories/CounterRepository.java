package com.zenhomes.boot.energyconsumptionpervillage.repositories;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class CounterRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Counter counter) {
        jdbcTemplate.update("INSERT INTO Counter(counterId, villageId, amount, createdDate) VALUES (?,?,?,?)",
                counter.getCounterId(), counter.getVillageId(), counter.getAmount(), LocalDateTime.now());
    }

    public List<Map<String,Object>> consumption_report(){
        List<Map<String,Object>> energyConsumption =
                jdbcTemplate.queryForList("select Village.villageName, sum(Counter.amount) amount" +
                        "FROM Counter" +
                        "LEFT JOIN Village ON Counter.villageId = Village.id" +
                        "WHERE createdDate >= now() - 24h" +
                        "GROUP BY Village.id");
        return energyConsumption;
    }
}
