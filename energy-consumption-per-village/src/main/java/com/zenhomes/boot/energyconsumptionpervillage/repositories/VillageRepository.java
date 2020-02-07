package com.zenhomes.boot.energyconsumptionpervillage.repositories;


import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VillageRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Village> findAll(){
        List<Village> result = jdbcTemplate.query(
                "SELECT id, villageName FROM village",
                (rs, rowNum) ->
                        new Village(
                            rs.getLong("id"),
                            rs.getString("villageName")
                )
        );

        return result;
    }

    public void save(String villageName) {
        //Check if the village entry already exist then update else insert the entry into the table
        //add this condition into service class
        jdbcTemplate.update("INSERT INTO village(villageName) VALUES (?)",
                villageName);
    }

    public Village findById(Long id){
        return jdbcTemplate.queryForObject("SELECT id, villageName FROM village WHERE ID = ?", new Object[] { id }, new BeanPropertyRowMapper<Village>(Village.class));
    }

    public Village findByname(String villageName){
        return jdbcTemplate.queryForObject("SELECT id, villageName FROM village WHERE villageName = ?", new Object[] { villageName }, new BeanPropertyRowMapper<Village>(Village.class));
    }
}
