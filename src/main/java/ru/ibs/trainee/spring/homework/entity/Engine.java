package ru.ibs.trainee.spring.homework.entity;

import javax.persistence.*;

@Entity
@Table
public class Engine {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    @OneToOne(mappedBy = "engine", cascade = CascadeType.ALL)
    private Car car;

    public Engine() {
    }

    public Engine(Long id, String type, Car car) {
        this.id = id;
        this.type = type;
        this.car = car;
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
