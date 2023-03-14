package com.example.carmanagement.service;

import com.example.carmanagement.model.car.Car;
import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.repository.CarRepo;
import com.example.carmanagement.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Optional<Car> getCarById(long id){
        return carRepo.findById(id);
    }

    public Optional<List<Car>> getAllCars(){
        return Optional.of(carRepo.findAll());
    }

    public void saveCar(Car car){
        carRepo.save(car);
    }

    public void deleteCar(Car car){
        carRepo.delete(car);
    }
}
