package com.zenhomes.boot.energyconsumptionpervillage.dao;

import com.zenhomes.boot.energyconsumptionpervillage.models.Village;

import java.util.List;

public interface VillageDao {
     List<Village> findAll();
     void save(Village village);
     Village findById(long id);
     boolean isVillageExists(long Id);
     void updateVillageName(Village village);
}
