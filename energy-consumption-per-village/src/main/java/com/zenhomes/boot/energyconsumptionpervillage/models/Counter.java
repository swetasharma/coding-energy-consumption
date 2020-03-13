package com.zenhomes.boot.energyconsumptionpervillage.models;

import org.springframework.util.Assert;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

public class Counter {

    @NotEmpty(message = "Please provide counter id")
    private long counterId;

    @NotEmpty(message = "Please provide village id")
    private long villageId;

    @NotEmpty(message = "Please provide amount")
    private double amount;

    private double netAmount;

    private LocalDateTime createdDate;

    public Counter() {
    }

    public Counter(long counter_id, long village_id, double amount) {
        Assert.notNull(counter_id, "CounterId cannot be empty");
        Assert.notNull(village_id, "Village Id cannot be empty");
        Assert.notNull(amount, "Amount cannot be empty");

        this.counterId = counter_id;
        this.villageId = village_id;
        this.amount = amount;
        this.netAmount = 0.0;
        this.createdDate = LocalDateTime.now();
    }

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    public long getVillageId() {
        return villageId;
    }

    public void setVillageId(long villageId) {
        this.villageId = villageId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "counterId=" + counterId +
                ", villageId=" + villageId +
                ", amount=" + amount +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        Counter counter = (Counter) o;
        return Objects.equals(getCounterId(), counter.getCounterId()) &&
                Objects.equals(getVillageId(), counter.getVillageId()) &&
                Objects.equals(getAmount(), counter.getAmount()) &&
                Objects.equals(getCreatedDate(), counter.getCreatedDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCounterId(), getVillageId(), getAmount(), getCreatedDate());
    }
}
