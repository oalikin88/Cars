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
    public ResponseEntity stWheelReadById(@PathVariable String id) {
        Long idStWheel;
        try {
            idStWheel = Long.parseLong(id);
            if (steeringWheelRepository.findById(idStWheel).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }

        SteeringWheel steeringWheel = steeringWheelRepository.findById(idStWheel).get();
        return new ResponseEntity(steeringWheel, HttpStatus.OK);
    }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity stWheelUpdate(@PathVariable String id, @RequestBody SteeringWheel stWheel) {

        Long idStWheel;
        String type;
        try {
            idStWheel = Long.parseLong(id);
            if(steeringWheelRepository.findById(idStWheel).isEmpty()){
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        type = stWheel.getType();
        steeringWheelRepository.editStWheelInfoById(type, idStWheel);
        return new ResponseEntity("Steering wheel was updated", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity stWheelUpdateError() {
        return new ResponseEntity("id not found, please enter url \"stwheel/update/{id}\" "
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity stWheelDel(@PathVariable String id) {
        Long idStWheel;
        try {
            idStWheel = Long.parseLong(id);
            if(steeringWheelRepository.findById(idStWheel).isEmpty()){
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        steeringWheelRepository.deleteById(idStWheel);

        return new ResponseEntity("Steering wheel was deleted", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity stWheelDelError() {
        return new ResponseEntity("id not found, please enter url \"stwheel/delete/{id}\""
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
