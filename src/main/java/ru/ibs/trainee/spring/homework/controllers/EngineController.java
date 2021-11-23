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
    public ResponseEntity engineReadById(@PathVariable Long id) {
        Engine engine;
        if (!engineRepository.findById(id).isEmpty()) {
            engine = engineRepository.findById(id).get();
        } else {
            throw new IdNotFoundException();
        }
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
    public ResponseEntity engineUpdateById(@PathVariable Long id, @RequestBody Engine engineUpdate) {
        String type = engineUpdate.getType();
        if (!engineRepository.findById(id).isEmpty()) {
            engineRepository.editEngineById(type, id);
        } else {
            throw new IdNotFoundException();
        }
        return new ResponseEntity("Engine was updated", HttpStatus.OK);
    }


    @PostMapping("delete/{id}")
    public ResponseEntity engineDelete(@PathVariable Long id) {
        if (!engineRepository.findById(id).isEmpty()) {
            engineRepository.deleteById(id);
        } else {
            throw new IdNotFoundException();
        }
        return new ResponseEntity("Engine was deleted", HttpStatus.OK);
    }


}
