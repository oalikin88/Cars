package ru.ibs.trainee.spring.homework.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.ibs.trainee.spring.homework.entity.Manual;

public interface ManualRepository extends CrudRepository<Manual, Long> {

    @Modifying
    @Query("update Manual m set m.type = ?1 where m.id = ?2")
    int editManualById(String type, Long id);

}
