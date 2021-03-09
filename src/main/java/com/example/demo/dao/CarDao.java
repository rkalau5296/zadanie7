package com.example.demo.dao;

import com.example.demo.model.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Repository
public class CarDao implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getAllCars() {
        String sgl = "SELECT * FROM cars";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sgl);
        return dbMapper(dbOutput);
    }

    @Override
    public void save(Car car) {
        List<Car> allCars = getAllCars();
        long maxId = allCars
                .stream()
                .max(Comparator.comparing(Car::getId))
                .get()
                .getId();
        Car newCar = new Car(maxId + 1, car.getMark(), car.getModel(), car.getColor(), car.getProductionDate());
        String sql = "INSERT INTO cars VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, newCar.getId(), newCar.getMark(), newCar.getModel(),
                newCar.getColor(), newCar.getProductionDate());
    }

    @Override
    public List<Car> findCarsByDate(LocalDate fromDate, LocalDate toDate) {
        String sql = "SELECT * FROM cars \n" +
                "WHERE (production_date > ? AND production_date < ?)";
        List<Map<String, Object>> dbOutput = jdbcTemplate.queryForList(sql, fromDate.toString(), toDate.toString());
        return dbMapper(dbOutput);
    }

    private List<Car> dbMapper(List<Map<String, Object>> dbOutput) {
        List<Car> carList = new ArrayList<>();
        dbOutput.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(String.valueOf(element.get("color"))),
                LocalDate.parse(String.valueOf(element.get("production_date")))
        )));
        return carList;
    }
}
