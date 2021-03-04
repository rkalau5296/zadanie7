package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin("*")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Car> getCars (){
        return carService.getAllCars();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/car")

    public Car addCar (@RequestBody Car newCar) {
        return carService.addNewCar(newCar);
    }
}
