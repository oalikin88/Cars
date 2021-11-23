package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ibs.trainee.spring.homework.entity.SteeringWheel;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;
import ru.ibs.trainee.spring.homework.repositories.SteeringWheelRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stwheel/")
public class SteeringWheelController {

    @Autowired
    SteeringWheelRepository steeringWheelRepository;


    @PostMapping("create")
    public ResponseEntity stwheelAdd(@RequestBody SteeringWheel stWheel) {
        steeringWheelRepository.save(stWheel);
        return new ResponseEntity("Steering wheel was created", HttpStatus.OK);
    }

    @GetMapping("read")
    public List<SteeringWheel> stWheelRead() {
        List<SteeringWheel> list = new ArrayList<>();
        steeringWheelRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @GetMapping("read/{id}")
    public ResponseEntity stWheelReadById(@PathVariable Long id) {
        SteeringWheel steeringWheel;
            if (!steeringWheelRepository.findById(id).isEmpty()) {
                steeringWheel = steeringWheelRepository.findById(id).get();
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity(steeringWheel, HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity stWheelUpdate(@PathVariable Long id, @RequestBody SteeringWheel stWheel) {
        String type = stWheel.getType();
            if(!steeringWheelRepository.findById(id).isEmpty()){
                steeringWheelRepository.editStWheelInfoById(type, id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Steering wheel was updated", HttpStatus.OK);
    }


    @PostMapping("delete/{id}")
    public ResponseEntity stWheelDel(@PathVariable Long id) {
            if(!steeringWheelRepository.findById(id).isEmpty()){
                steeringWheelRepository.deleteById(id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Steering wheel was deleted", HttpStatus.OK);
    }

}
