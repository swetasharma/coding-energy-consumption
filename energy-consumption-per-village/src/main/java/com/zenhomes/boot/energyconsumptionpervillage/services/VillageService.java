package com.zenhomes.boot.energyconsumptionpervillage.services;
import com.zenhomes.boot.energyconsumptionpervillage.dao.VillageDaoMySqlImpl;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService{

    @Autowired
    private VillageDaoMySqlImpl villageDaoMySqlImpl;

    public void save(Village village) {
        villageDaoMySqlImpl.save(village);
    }

    public Village findById(long id) {
        return villageDaoMySqlImpl.findById(id);
    }

}
