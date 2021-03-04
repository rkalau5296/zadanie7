package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    String brand;
    @Column
    String model;
    @Column
    String color;
    @Column
    String production_date;
}
