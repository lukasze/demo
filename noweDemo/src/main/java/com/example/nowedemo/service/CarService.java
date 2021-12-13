package com.example.nowedemo.service;

import com.example.nowedemo.model.Car;
import com.example.nowedemo.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> listAll(){
        return carRepository.findAll();
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }
}
