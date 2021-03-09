package com.example.demo.config;

import com.example.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbConfig {

    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getjdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        String dropTable = "DROP TABLE IF EXISTS cars";
        getjdbcTemplate().update(dropTable);

        String createTable = "CREATE TABLE cars (id int PRIMARY KEY AUTO_INCREMENT, " +
                "mark varchar (100) , model varchar (100) , color varchar (50) , " +
                "production_date varchar (50))";
        getjdbcTemplate().update(createTable);

        String sql = "INSERT INTO cars (id, mark, model, color, production_date) VALUES (?, ?, ?, ?, ?)";
        addCarsToDb().forEach(car ->
                getjdbcTemplate().update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor().toString(),
                        Date.valueOf(car.getProductionDate())));

    }

    private List<Car> addCarsToDb() {
        List<Car> initDbList = new ArrayList<>();
        initDbList.add(new Car(1L, "BMW", "X5", "Black", LocalDate.of(2020, 05, 18)));
        initDbList.add(new Car(2L, "Lamborghini", "Urus", "Red", LocalDate.of(2020, 04, 13)));
        initDbList.add(new Car(3L, "Honda", "Civic", "Yellow", LocalDate.of(2011, 05, 07)));
        return initDbList;
    }
}
