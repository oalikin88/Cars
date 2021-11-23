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
   public ResponseEntity carReadById(@PathVariable Long id) {
        Car car;
        if(!carRepository.findById(id).isEmpty()) {
            car = carRepository.findById(id).get();
        } else {
            throw new IdNotFoundException();
        }

        return  new ResponseEntity(car, HttpStatus.OK);
   }

    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity carEdit(@PathVariable Long id, @RequestBody Car car) {
        String mnfName = car.getMnfName();
        String model = car.getModelName();
       Car editCar;
            if(!carRepository.findById(id).isEmpty()) {
                carRepository.editCarInfoById(mnfName, model, id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Car was updated", HttpStatus.OK);
    }



    @PostMapping("delete/{id}")
    public ResponseEntity carDelete(@PathVariable Long id) {
            if(!carRepository.findById(id).isEmpty()) {
                carRepository.deleteById(id);
            } else {
                throw new IdNotFoundException();
            }
        return new ResponseEntity("Car deleted", HttpStatus.OK);
    }


}
