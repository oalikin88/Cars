package ru.ibs.trainee.spring.homework.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity (name = "Manual")
@Table(name = "manuals")
public class Manual {
    @Id
    @GeneratedValue
    private Long id;
    private String type;

    @ManyToMany(mappedBy = "manuals", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    public Manual() {
    }

    public Manual(String type) {
        this.type = type;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}
