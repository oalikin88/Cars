package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.SteeringWheel;
import java.util.List;

public interface SteeringWheelRepository extends CrudRepository<SteeringWheel, Long> {

    List<SteeringWheel> findStWheelById(Long id);

    @Modifying
    @Query("update SteeringWheel s set s.type = ?1 where s.id = ?2")
    int editStWheelInfoById(String type, Long id);
}
