package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ibs.trainee.spring.homework.entity.Engine;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;
import ru.ibs.trainee.spring.homework.repositories.EngineRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/engine/")
public class EngineController {

    @Autowired
    EngineRepository engineRepository;


    @PostMapping("create")
    public ResponseEntity engineCreate(@RequestBody Engine engine) {
        engineRepository.save(engine);
        return new ResponseEntity("Engine was created", HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    public ResponseEntity engineReadById(@PathVariable String id) {
        Long idEngine;
        try {
            idEngine = Long.parseLong(id);
            if (engineRepository.findById(idEngine).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }

        Engine engine = engineRepository.findById(idEngine).get();
        return new ResponseEntity(engine, HttpStatus.OK);
    }

    @GetMapping("read")
    public List<Engine> engineRead() {
        List<Engine> list = new ArrayList<>();
        engineRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity engineUpdateById(@PathVariable String id, @RequestBody Engine engineUpdate) {
        Long idEngine;
        try {
            idEngine = Long.parseLong(id);
            if (engineRepository.findById(idEngine).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        String type = engineUpdate.getType();
        engineRepository.editEngineById(type, idEngine);
        return new ResponseEntity("Engine was updated", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity engineUpdateError() {
        return new ResponseEntity("id not found, please enter url \"engine/update/{id}\""
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity engineDelete(@PathVariable String id) {
        Long idEngine;
        try {
            idEngine = Long.parseLong(id);
            if (engineRepository.findById(idEngine).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        engineRepository.deleteById(idEngine);
        return new ResponseEntity("Engine was deleted", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity engineDeleteError() {
        return new ResponseEntity("id not found, please enter url \"engine/delete/{id}\""
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
