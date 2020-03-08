package com.zenhomes.boot.energyconsumptionpervillage.models;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;


public class Village {
    @Id
    private long id;
    @NotEmpty(message = "please provide village name")
    private String villageName;
    private LocalDateTime createdDate;

    public Village(){
    }

    public Village(long id, String villageName){
        Assert.notNull(id, "Village Id cannot be empty");
        Assert.notNull(villageName, "Village Name cannot be empty");
        this.id = id;
        this.villageName = villageName;
        this.createdDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
