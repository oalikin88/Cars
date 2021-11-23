package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ibs.trainee.spring.homework.entity.Manual;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;
import ru.ibs.trainee.spring.homework.repositories.ManualRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/manual/")
public class ManualController {

    @Autowired
    ManualRepository manualRepository;

    @PostMapping("create")
    public ResponseEntity manualCreate(@RequestBody Manual manual) {
        manualRepository.save(manual);
        return new ResponseEntity("Manual was created", HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    public ResponseEntity manualReadById(@PathVariable Long id) {
        Manual manual;
            if (!manualRepository.findById(id).isEmpty()) {
               manual = manualRepository.findById(id).get();
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity(manual, HttpStatus.OK);
    }

    @GetMapping("read")
    public List<Manual> manualRead() {
        List<Manual> list = new ArrayList<>();
        manualRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity manualUpdateById(@PathVariable Long id, @RequestBody Manual manualUpdate) {
        String type = manualUpdate.getType();
            if (!manualRepository.findById(id).isEmpty()) {
                manualRepository.editManualById(type, id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Manual was updated", HttpStatus.OK);

    }


    @PostMapping("delete/{id}")
    public ResponseEntity manualDelete(@PathVariable Long id) {
            if (!manualRepository.findById(id).isEmpty()) {
                manualRepository.deleteById(id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Manual was deleted", HttpStatus.OK);
    }


}
