package com.example.nowedemo.controller;

import com.example.nowedemo.model.Car;
import com.example.nowedemo.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/cars")
    public List<Car> listCars(){
        return carService.listAll();
    }
}
