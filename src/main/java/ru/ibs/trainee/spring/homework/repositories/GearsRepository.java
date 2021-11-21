package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Gear;

public interface GearsRepository extends CrudRepository<Gear, Long> {

    @Modifying
    @Query("update Gear g set g.size = ?1 where g.id = ?2")
    int editGearById(int size, Long id);

}
