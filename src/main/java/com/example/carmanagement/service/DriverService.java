package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.repository.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DriverService {
    private final DriverRepo driverRepo;

    @Autowired
    public DriverService(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    public Page<Driver> findDriversWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Driver> drivers = driverRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return drivers;
    }

    public Optional<Driver> getDriverById(long id){
        return driverRepo.findById(id);
    }

    public Optional<List<Driver>> getAllDrivers(){
        return Optional.of(driverRepo.findAll());
    }

    public List<Driver> findDriverByBirthDate(int day, int month){
        return driverRepo.findDriverByBirthDate(day, month);
    }

    public void saveDriver(Driver driver){
        driverRepo.save(driver);
    }

    public void deleteDriver(Driver driver){
        driverRepo.delete(driver);
    }
}
