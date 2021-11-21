package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Engine;
import java.util.List;

public interface EngineRepository extends CrudRepository<Engine, Long> {

    List<Engine> findEngineById(Long id);

    @Modifying
    @Query("update Engine e set e.type = ?1 where e.id = ?2")
    int editEngineById(String type, Long id);
}

