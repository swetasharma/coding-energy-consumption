package com.zenhomes.boot.energyconsumptionpervillage.dto;

public class CounterRegister {
    public long counter_id;

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
