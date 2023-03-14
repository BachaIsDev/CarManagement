package com.example.carmanagement.repository;

import com.example.carmanagement.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    List<Driver> findDriverByBirthDate(int day, int month);
}
