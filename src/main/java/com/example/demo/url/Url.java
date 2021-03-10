package com.example.demo.url;

import com.example.demo.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class Url {

    private final RestTemplate restTemplate;

    @Autowired
    public Url(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //GET

    public List<CarDto> getCars() {

        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getCar")
                .build().encode().toUri();

        CarDto[] vehicles = restTemplate.getForObject(uri, CarDto[].class);

        return Arrays.asList(Optional.ofNullable(vehicles).orElse(new CarDto[0]));
    }

    public List<CarDto> getCarsByDate(String from, String to) {

        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getCarByDate/" + from + "/" + to)
                .build().encode().toUri();

        CarDto[] vehicles = restTemplate.getForObject(uri, CarDto[].class);

        return Arrays.asList(Optional.ofNullable(vehicles).orElse(new CarDto[0]));
    }

    public List<CarDto> getCarsByColor(String color) {

        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/getCarByColor/" + color)
                .build().encode().toUri();

        CarDto[] vehicles = restTemplate.getForObject(uri, CarDto[].class);

        return Arrays.asList(Optional.ofNullable(vehicles).orElse(new CarDto[0]));
    }
    //POST

    public void postCar(final CarDto carDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/add")
                .build().encode().toUri();
        restTemplate.postForObject(uri, carDto, CarDto.class);
    }

}
