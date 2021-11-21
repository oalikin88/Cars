package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("read/{id}")
    public ResponseEntity engineReadById(String id) {
        Long idEngine;
        if(null != id) {
            try {
                idEngine = Long.parseLong(id);
                if(!engineRepository.findById(idEngine).isEmpty()) {
                    throw new IdNotFoundException();
                }
            } catch (Exception e) {
                throw new IdNotFoundException();
            }
        } else {
            throw new IdNotFoundException();
        }
        Engine engine = engineRepository.findById(idEngine).get();
        return new ResponseEntity(engine, HttpStatus.OK);
    }

    @GetMapping("read")
    public ResponseEntity engineRead() {
        List<Engine> list = new ArrayList<>();
        engineRepository.findAll().forEach(e -> list.add(e));
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
