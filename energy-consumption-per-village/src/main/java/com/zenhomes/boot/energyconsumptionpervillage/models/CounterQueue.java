package com.zenhomes.boot.energyconsumptionpervillage.models;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Objects;

public class CounterQueue {

    @Positive( message = "You cannot have zero or negative counterId" )
    private long counterId;

    @Positive( message = "You cannot have zero or negative amount" )
    private double amount;

    private byte processed;

    private LocalDateTime createdDate;

    public CounterQueue(long counterId, double amount, byte processed) {
        this.counterId = counterId;
        this.amount = amount;
        this.processed = processed;
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CounterQueue)) return false;
        CounterQueue that = (CounterQueue) o;
        return counterId == that.counterId &&
                Double.compare(that.amount, amount) == 0 &&
                processed == that.processed &&
                Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(counterId, amount, processed, createdDate);
    }

    @Override
    public String toString() {
        return "CounterQueue{" +
                "counterId=" + counterId +
                ", amount=" + amount +
                ", processed=" + processed +
                ", createdDate=" + createdDate +
                '}';
    }
}
