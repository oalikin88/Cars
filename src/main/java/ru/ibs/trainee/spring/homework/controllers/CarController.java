package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ibs.trainee.spring.homework.entity.Car;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;
import ru.ibs.trainee.spring.homework.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;


/*
*        Реализовал два варианта эндпоинтов update с опциональным /{id} в конце path
*  и вариант, где id обязательный в body (закомментировано внизу).
*
* */

@RestController
@RequestMapping("api/car/")
public class CarController {

    @Autowired
    CarRepository carRepository;


    @PostMapping("create")
    public ResponseEntity carAdd(@RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity("Car added", HttpStatus.OK);
    }

    @GetMapping("read")
    public List<Car> carRead() {
        List<Car> list = new ArrayList<>();
        carRepository.findAll().forEach(e -> list.add(e));
        return list;
        }
   @GetMapping("read/{id}")
   public ResponseEntity carReadById(String id) {
        Long idCar;
        try {
            idCar = Long.parseLong(id);
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        if(carRepository.findById(idCar).isEmpty()) {
            throw new IdNotFoundException();
        }
        Car car = carRepository.findById(idCar).get();
        return  new ResponseEntity(car, HttpStatus.OK);
   }


    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity carEdit(@PathVariable String id, @RequestBody Car car) {
       Long idCar;
       Car editCar;
        try {
            idCar = Long.parseLong(id);
            if(carRepository.findById(idCar).isEmpty()) {
                throw new IdNotFoundException();
            } else {
                editCar = carRepository.findById(idCar).get();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        String mnfName = car.getMnfName();
        String model = car.getModelName();
        carRepository.editCarInfoById(mnfName, model, idCar);

        return new ResponseEntity(editCar + " was updated", HttpStatus.OK);
    }

    @PostMapping("update/")
    public ResponseEntity carUpdateError() {
        return new ResponseEntity("id not found, please enter url \"car/update/{id}\" "
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @PostMapping("delete/{id}")
    public ResponseEntity carDelete(@PathVariable String id) {
        Long idCar;
        try {
            idCar = Long.parseLong(id);
            if(carRepository.findById(idCar).isEmpty()) {
                throw new IdNotFoundException();
            }
        } catch (Exception e) {
            throw new IdNotFoundException();
        }
        Car car = carRepository.findById(idCar).get();
        carRepository.delete(car);
        return new ResponseEntity("Car deleted", HttpStatus.OK);
    }

    @PostMapping("delete/")
    public ResponseEntity carDeleteError() {
        return new ResponseEntity("id not found, please enter url \"car/delete/{id}\" "
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }


/*    @PostMapping("car/update/")
    @Transactional
    public ResponseEntity carEdit(@RequestBody Car car) {
        if(null == car.getId()) {
            throw new IdNotFoundException();
        }
        Long idCar = car.getId();
        String mnfName = car.getMnfName();
        String model = car.getModelName();
        carRepository.editCarInfoById(mnfName, model, idCar);
        return new ResponseEntity("ok", HttpStatus.OK);
    }*/



 /*   @PostMapping("car/delete")
    public String carDelete(@RequestParam Long id) {
        if(null == id) {
            throw new IdNotFoundException();
        }
        Car car = carRepository.findById(id).get();
        carRepository.delete(car);
        return "ok";
    }*/


}
