package com.zenhomes.boot.energyconsumptionpervillage.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Village {
    @Id
    private String id;
    @NotEmpty(message = "please provide village name")
    private String name;
    private LocalDateTime createdDate;

    public Village(){
    }

    public Village(String id, String name){
        this.id = id;
        this.name = name;
        this.createdDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Village)) return false;
        Village village = (Village) o;
        return Objects.equals(getId(), village.getId()) &&
                Objects.equals(name, village.name) &&
                Objects.equals(createdDate, village.createdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), name, createdDate);
    }
}
