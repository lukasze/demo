package com.example.nowedemo.controller;

import com.example.nowedemo.model.Car;
import com.example.nowedemo.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(value = "/cars", consumes = "application/json")
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/cars")
    public List<Car> listCars(){
        return carService.listAll();
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.delete(id);
    }
}
