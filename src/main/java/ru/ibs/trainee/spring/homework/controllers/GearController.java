package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ibs.trainee.spring.homework.entity.Gear;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;
import ru.ibs.trainee.spring.homework.repositories.GearsRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gear")
public class GearController {

    @Autowired
    GearsRepository gearsRepository;

    @PostMapping("create")
    public ResponseEntity gearsCreate(@RequestBody Gear gear) {
        gearsRepository.save(gear);
        return new ResponseEntity("Gear was created", HttpStatus.OK);
    }

    @GetMapping("read/{id}")
    public ResponseEntity gearsReadById(@PathVariable Long id) {
        Gear gear;
            if (!gearsRepository.findById(id).isEmpty()) {
                gear = gearsRepository.findById(id).get();
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity(gear, HttpStatus.OK);
    }

    @GetMapping("read")
    public List<Gear> gearsRead() {
        List<Gear> list = new ArrayList<>();
        gearsRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity gearsUpdateById(@PathVariable Long id, @RequestBody Gear gearUpdate) {
        int size = gearUpdate.getSize();
            if (!gearsRepository.findById(id).isEmpty()) {
                gearsRepository.editGearById(size, id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Gear was updated", HttpStatus.OK);

    }


    @PostMapping("delete/{id}")
    public ResponseEntity gearDelete(@PathVariable Long id) {
            if (!gearsRepository.findById(id).isEmpty()) {
                gearsRepository.deleteById(id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Gear was deleted", HttpStatus.OK);
    }

}
