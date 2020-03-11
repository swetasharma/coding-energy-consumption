package com.zenhomes.boot.energyconsumptionpervillage.exceptions;

public class VillageNotFoundException extends RuntimeException{

    public VillageNotFoundException(long id) {
        super("Village with ID " + id + " could not be found");
    }
}
