package com.example.carmanagement.repository;

import com.example.carmanagement.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {

}
