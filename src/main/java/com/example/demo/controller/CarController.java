package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin("*")
public class CarController {

    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCar")
    public List<Car> getCars (){
        return carRepository.getAllCars();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")

    public void addCar (@RequestBody Car newCar) {
         carRepository.save(newCar);
    }
}
