package ru.ibs.trainee.spring.homework.entity;


import javax.persistence.*;

@Entity
@Table(name = "Gears")
public class Gear {

    @Id
    @GeneratedValue
    private Long id;
    private int size;

    public Gear() {
    }

    public Gear(Long id, int size) {
        this.id = id;
        this.size = size;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
