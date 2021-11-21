package ru.ibs.trainee.spring.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table

public class SteeringWheel {
    @Id
    @GeneratedValue
    private Long id;
    private String type;

    @OneToOne(mappedBy = "steeringWheel", cascade = CascadeType.ALL)
    private Car car;


    public SteeringWheel(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public SteeringWheel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
