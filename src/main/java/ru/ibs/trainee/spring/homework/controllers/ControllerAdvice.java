package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ibs.trainee.spring.homework.entity.Car;
import ru.ibs.trainee.spring.homework.exceptions.IdNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ResponseEntity handle(IdNotFoundException ex) {

        return new ResponseEntity("id not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
