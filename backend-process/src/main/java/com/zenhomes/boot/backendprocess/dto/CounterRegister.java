package com.zenhomes.boot.backendprocess.dto;

import javax.validation.constraints.Positive;

public class CounterRegister {
    @Positive( message = "You cannot have zero or negative counterId" )
    public long counter_id;

    @Positive( message = "You cannot have zero or negative amount" )
    public double amount;

    public long getCounter_id() {
        return counter_id;
    }

    public void setCounter_id(long counter_id) {
        this.counter_id = counter_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
