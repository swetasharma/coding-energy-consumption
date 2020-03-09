package com.zenhomes.boot.energyconsumptionpervillage.models;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class ParentVillage {
    private int id;
    private Village village;

    public ParentVillage() {
    }

    public ParentVillage(int id, Village village) {
        this.id = id;
        this.village = village;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
}
