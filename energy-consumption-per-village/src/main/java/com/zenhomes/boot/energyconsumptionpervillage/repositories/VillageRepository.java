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

    /**
     * Create a new record in village table
     * @param village
     */
    public void save(Village village) {
        jdbcTemplate.update("INSERT INTO village(id, villageName) VALUES (?, ?)",
                village.getId(), village.getVillageName());
    }

    /**
     * Get village details by Id
     * @param id
     * @return
     */
    public Village findById(Long id){
        return jdbcTemplate.queryForObject("SELECT id, villageName FROM village WHERE ID = ?", new Object[] { id }, new BeanPropertyRowMapper<Village>(Village.class));
    }

    /**
     * Check if the village exist ot not
     * @param Id
     * @return
     */
    public boolean isVillageExists(long Id){
        if(this.findById(Id) != null) {
            return true;
        }
        return false;
    }

    /**
     * If the record already exist we need to update the village name
     * @param village
     */
    public void updateVillageName(Village village){
        jdbcTemplate.update("UPDATE village SET villageName = ? where id = ?", village.getVillageName(), village.getId());
    }
}
