package ru.ibs.trainee.spring.homework.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

@Entity
@Table (name = "Steering_wheels")
public class SteeringWheel {
    @Id
    @GeneratedValue
    private Long id;
    private String type;

    @OneToOne(mappedBy = "steeringWheel", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "car-steeringWheel")
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
