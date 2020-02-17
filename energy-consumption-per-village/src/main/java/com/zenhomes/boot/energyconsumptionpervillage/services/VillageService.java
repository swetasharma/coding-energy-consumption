package com.zenhomes.boot.energyconsumptionpervillage.services;
import com.zenhomes.boot.energyconsumptionpervillage.dao.VillageDao;
import com.zenhomes.boot.energyconsumptionpervillage.models.Village;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VillageService{

    private static final Logger logger = LoggerFactory.getLogger(CounterService.class);

    @Autowired
    private VillageDao villageDao;

    public void save(Village village) {
        villageDao.save(village);
    }

    public Village findById(long id) {
        return villageDao.findById(id);
    }

}
