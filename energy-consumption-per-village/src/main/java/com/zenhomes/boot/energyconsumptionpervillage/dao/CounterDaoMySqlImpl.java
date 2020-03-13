package com.zenhomes.boot.energyconsumptionpervillage.dao;
import com.zenhomes.boot.energyconsumptionpervillage.models.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        //first entry for that village
        jdbcTemplate.update("INSERT INTO counter(counterId, villageId, amount, netAmount, createdDate) VALUES (?,?,?,?,?)",
                counter.getCounterId(), counter.getVillageId(), counter.getAmount(), counter.getAmount() - counter.getNetAmount(), LocalDateTime.now());
    }

    public List<Map<String,Object>> consumptionReport(){
        List<Map<String,Object>> energyConsumption =
                jdbcTemplate.queryForList("SELECT village.villageName, sum(counter.amount) amount" +
                        "FROM counter" +
                        "LEFT JOIN village ON counter.villageId = village.id" +
                        "WHERE createdDate >= date_sub(now(), interval 24 hour)" +
                        "GROUP BY village.id");
        return energyConsumption;
    }

    /**
     * Here er are getting the last record as per the village id and counter id to calculate the net amount
     * @param counter
     * @return
     */
    public double getLastRecordToCalculateNetAmount(Counter counter){
        try{
            String query = "SELECT amount from counter where counterId = ? AND villageId = ? ORDER BY createdDate DESC LIMIT 0, 1;";
            Object[] inputs = new Object[] {counter.getCounterId(), counter.getVillageId()};
            return jdbcTemplate.queryForObject(query, inputs, Double.class).doubleValue();
        }catch (EmptyResultDataAccessException e){
            return 0.0;
        }
    }
}
