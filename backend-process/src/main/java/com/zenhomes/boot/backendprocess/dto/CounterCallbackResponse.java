package com.zenhomes.boot.backendprocess.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zenhomes.boot.backendprocess.models.Village;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class CounterCallbackResponse {
    private int id;
    private Village village;

    public CounterCallbackResponse() {
    }

    public CounterCallbackResponse(int id, Village village) {
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
