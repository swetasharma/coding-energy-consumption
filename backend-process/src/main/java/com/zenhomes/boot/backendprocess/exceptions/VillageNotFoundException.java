package com.zenhomes.boot.backendprocess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class VillageNotFoundException extends RuntimeException{

    public VillageNotFoundException(long id) {
        super("Village with ID " + id + " could not be found");
    }
}
