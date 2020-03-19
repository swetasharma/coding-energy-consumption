package com.zenhomes.boot.backendprocess.models;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.Objects;

public class CounterQueue {

    @Id
    private long id;

    @Positive( message = "You cannot have zero or negative counterId" )
    private long counterId;

    @Positive( message = "You cannot have zero or negative amount" )
    private double amount;

    private byte processed;

    private LocalDateTime createdDate;

    public CounterQueue(){
        
    }

    public CounterQueue(long counterId, double amount, byte processed) {
        this.counterId = counterId;
        this.amount = amount;
        this.processed = 0;
        this.createdDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte getProcessed() {
        return processed;
    }

    public void setProcessed(byte processed) {
        this.processed = processed;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CounterQueue)) return false;
        CounterQueue that = (CounterQueue) o;
        return getId() == that.getId() &&
                getCounterId() == that.getCounterId() &&
                Double.compare(that.getAmount(), getAmount()) == 0 &&
                getProcessed() == that.getProcessed() &&
                Objects.equals(getCreatedDate(), that.getCreatedDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCounterId(), getAmount(), getProcessed(), getCreatedDate());
    }

    @Override
    public String toString() {
        return "CounterQueue{" +
                "id=" + id +
                ", counterId=" + counterId +
                ", amount=" + amount +
                ", processed=" + processed +
                ", createdDate=" + createdDate +
                '}';
    }
}
