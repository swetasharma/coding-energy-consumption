package com.zenhomes.boot.backendprocess.dto;

public class EnergyConsumption {
    public String village_name;
    public double consumption;

    public void setVillage_name(String village_name) {
        this.village_name = village_name;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }
}
