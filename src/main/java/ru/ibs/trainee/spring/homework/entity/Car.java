package ru.ibs.trainee.spring.homework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cars")
@Component
public class Car {
    @Id
    @GeneratedValue
    public Long id;
    public String mnfName;
    public String modelName;
    @JsonBackReference(value = "car-steeringWheel")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "steering_wheel_id")
    private SteeringWheel steeringWheel;
    @JsonBackReference(value = "car-engine")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-gears")
    private List<Gear> gears;


    public Car() {

    }

    public Car(Long id, String mnfName, String modelName) {
        this.id = id;
        this.mnfName = mnfName;
        this.modelName = modelName;
    }

    public Car(Long id, String mnfName, String modelName, SteeringWheel steeringWheel) {
        this.id = id;
        this.mnfName = mnfName;
        this.modelName = modelName;
        this.steeringWheel = steeringWheel;
    }

    public Car(Long id, String mnfName, String modelName, SteeringWheel steeringWheel, Engine engine) {
        this.id = id;
        this.mnfName = mnfName;
        this.modelName = modelName;
        this.steeringWheel = steeringWheel;
        this.engine = engine;
    }

    public Car(Long id, String mnfName, String modelName, SteeringWheel steeringWheel, Engine engine, List<Gear> gears) {
        this.id = id;
        this.mnfName = mnfName;
        this.modelName = modelName;
        this.steeringWheel = steeringWheel;
        this.engine = engine;
        this.gears = gears;
    }

    public List<Gear> getGears() {
        return gears;
    }

    public void setGears(List<Gear> gears) {
        this.gears = gears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMnfName() {
        return mnfName;
    }

    public void setMnfName(String mnfName) {
        this.mnfName = mnfName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public SteeringWheel getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(SteeringWheel steeringWheel) {
        this.steeringWheel = steeringWheel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


}
