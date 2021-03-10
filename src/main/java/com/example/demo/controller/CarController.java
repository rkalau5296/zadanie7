package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping()
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
    @RequestMapping(method = RequestMethod.GET, value = "/getCarByDate/{from}/{to}")
    public List<Car> getCarsByDate (@PathVariable String from, @PathVariable String to){
        return carRepository.findCarsByDate(LocalDate.parse(from), LocalDate.parse(to));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getCarByColor/{color}")
    public List<Car> getCarsByColor (@PathVariable String color){
        return carRepository.findCarsByColor(color);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void addCar (@RequestBody Car newCar) {
         carRepository.save(newCar);
    }


}
