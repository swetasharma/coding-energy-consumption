package com.zenhomes.boot.energyconsumptionpervillage.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Objects;

public class Village {
    @Id
    private Long id;
    private String villageName;
    private LocalDateTime createdDate;

    public Village(){
    }

    Village(Long id, String villageName){
        this.id = id;
        this.villageName = villageName;
        this.createdDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", villageName='" + villageName + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Village)) return false;
        Village village = (Village) o;
        return Objects.equals(getId(), village.getId()) &&
                Objects.equals(getVillageName(), village.getVillageName()) &&
                Objects.equals(createdDate, village.createdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getVillageName(), createdDate);
    }
}
