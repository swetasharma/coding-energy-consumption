package com.zenhomes.boot.energyconsumptionpervillage.dao;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VillageDaoMySqlImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Village> findAll(){
        return jdbcTemplate.query("SELECT id, villageName FROM village", new BeanPropertyRowMapper<Village>(Village.class));
    }

    /**
     * Create a new record in village table
     * @param village
     */

    public int save(Village village) {
        return jdbcTemplate.update("INSERT INTO village(id, villageName) VALUES (?, ?)", new BeanPropertyRowMapper<Village>(Village.class));
    }

    /**
     * Get village details by Id
     * @param id
     * @return
     */
    public Village findById(long id){
        return jdbcTemplate.queryForObject("SELECT id, villageName FROM village WHERE ID = ?", new Object[] { id }, new BeanPropertyRowMapper<Village>(Village.class));
    }

    /**
     * Check if the village exist ot not
     * @param Id
     * @return
     */
    public boolean isVillageExists(long Id){
         return this.findById(Id) == null;
    }

    /**
     * If the record already exist we need to update the village name
     * @param village
     */
    public int updateVillageName(Village village){
        return jdbcTemplate.update("UPDATE village SET villageName = ? where id = ?", village.getVillageName(), village.getId());
    }
}
