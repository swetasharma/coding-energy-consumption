package com.zenhomes.boot.energyconsumptionpervillage.services;
import com.zenhomes.boot.energyconsumptionpervillage.dao.VillageDao;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService{

    @Autowired
    private VillageDao villageDao;

    public void save(Village village) {
        villageDao.save(village);
    }

    public Village findById(long id) {
        return villageDao.findById(id);
    }

}
