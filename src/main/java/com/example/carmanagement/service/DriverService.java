package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class DriverService {
    private final DriverRepo driverRepo;

    @Autowired
    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public Optional<Driver> getDriverById(long id){
        return driverRepo.findById(id);
    }

    public Optional<List<Driver>> getAllDrivers(){
        return Optional.of(driverRepo.findAll());
    }

    public void saveDriver(Driver driver){
        driverRepo.save(driver);
    }

    public void deleteDriver(Driver driver){
        driverRepo.delete(driver);
    }
}
