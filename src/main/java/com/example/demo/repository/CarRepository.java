package com.example.demo.repository;


import com.example.demo.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository {

    List<Car> getAllCars();

    void save(Car newCar);
}
