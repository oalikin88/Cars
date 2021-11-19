package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Car;


public interface CarRepository extends CrudRepository<Car, Long> {
}
