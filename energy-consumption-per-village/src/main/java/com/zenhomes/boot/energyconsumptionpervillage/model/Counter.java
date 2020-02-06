package com.zenhomes.boot.energyconsumptionpervillage.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Counter {

    private Long counterId;
    private Long villageId;
    private Double amount;
    private LocalDateTime createdDate;

    public Counter() {
    }

    public Counter(Long counter_id, Long village_id, Double amount) {
        this.counterId = counter_id;
        this.counterId = village_id;
        this.amount = amount;
        this.createdDate = LocalDateTime.now();
    }

    public Long getCounterId() {
        return counterId;
    }

    public void setCounterId(Long counterId) {
        this.counterId = counterId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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
