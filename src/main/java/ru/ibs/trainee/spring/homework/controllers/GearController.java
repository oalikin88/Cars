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
    public ResponseEntity gearsReadById(@PathVariable String id) {
        Long idGear;
        try {
            idGear = Long.parseLong(id);
            if (gearsRepository.findById(idGear).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        Gear gear = gearsRepository.findById(idGear).get();
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
    public ResponseEntity gearsUpdateById(@PathVariable String id, @RequestBody Gear gearUpdate) {
        Long idGear;
        try {
            idGear = Long.parseLong(id);
            if (gearsRepository.findById(idGear).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        int size = gearUpdate.getSize();
        gearsRepository.editGearById(size, idGear);
        return new ResponseEntity("Gear was updated", HttpStatus.OK);

    }

    @PostMapping("update")
    public ResponseEntity gearsUpdateError() {
        return new ResponseEntity("id not found, please enter url \"gear/update/{id}\""
        , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity gearDelete(@PathVariable String id) {
        Long idGear;
        try {
            idGear = Long.parseLong(id);
            if (gearsRepository.findById(idGear).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        gearsRepository.deleteById(idGear);
        return new ResponseEntity("Gear was deleted", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity gearsDeleteError() {
        return new ResponseEntity("id not found, please enter url \"gear/delete/{id}\"",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
