package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Car;

import java.util.List;


public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findCarById(Long id);

    @Modifying
    @Query("update Car c set c.mnfName = ?1, c.modelName = ?2 where c.id = ?3")
    int editCarInfoById(String mnfName, String modelName, Long id);

}
