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
    public ResponseEntity manualReadById(@PathVariable String id) {
        Long idManual;
        try {
            idManual = Long.parseLong(id);
            if (manualRepository.findById(idManual).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        Manual manual = manualRepository.findById(idManual).get();
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
    public ResponseEntity manualUpdateById(@PathVariable String id, @RequestBody Manual manualUpdate) {
        Long idManual;
        try {
            idManual = Long.parseLong(id);
            if (manualRepository.findById(idManual).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        String type = manualUpdate.getType();
        manualRepository.editManualById(type, idManual);
        return new ResponseEntity("Manual was updated", HttpStatus.OK);

    }

    @PostMapping("update")
    public ResponseEntity manualUpdateError() {
        return new ResponseEntity("id not found, please enter url \"manual/update/{id}\""
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity manualDelete(@PathVariable String id) {
        Long idManual;
        try {
            idManual = Long.parseLong(id);
            if (manualRepository.findById(idManual).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        manualRepository.deleteById(idManual);
        return new ResponseEntity("Manual was deleted", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity manualDeleteError() {
        return new ResponseEntity("id not found, please enter url \"manual/delete/{id}\"",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
