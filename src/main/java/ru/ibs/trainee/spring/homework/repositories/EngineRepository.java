package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Engine;

import java.util.List;

public interface EngineRepository extends CrudRepository<Engine, Long> {

    List<Engine> findEngineById(Long id);
}
