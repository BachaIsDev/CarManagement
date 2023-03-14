package com.example.carmanagement.rest;

import com.example.carmanagement.model.car.Car;
import com.example.carmanagement.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            map.put("status", 1);
            map.put("data", car);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<List<Car>> carList = carService.getAllCars();
        if (carList.isPresent()) {
            map.put("status", 1);
            map.put("data", carList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewCar(@RequestBody Car car) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        carService.saveCar(car);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCar(@PathVariable(name = "id") Long id, @RequestBody Car car) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Car updateCar = carService.getCarById(id).get();
            updateCar.setDriver(car.getDriver());
            updateCar.setCarModel(car.getCarModel());
            updateCar.setManufacturer(car.getManufacturer());
            updateCar.setDetails(car.getDetails());
            updateCar.setVin(car.getVin());
            updateCar.setRegNumber(car.getRegNumber());
            updateCar.setYearOfIssue(car.getYearOfIssue());
            carService.saveCar(updateCar);
            map.put("status", 1);
            map.put("data", carService.getCarById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<Car> car = carService.getCarById(id);
        carService.deleteCar(car.get());
        if (car.isPresent()) {
            map.put("status", 1);
            map.put("data", car);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}