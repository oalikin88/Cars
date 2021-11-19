package ru.ibs.trainee.spring.homework.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class RController {

    @PostMapping("car")
    public String hello() {
        return "Hello World!";
    }
}
