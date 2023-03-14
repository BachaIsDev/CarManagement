package com.example.carmanagement.repository;

import com.example.carmanagement.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
}
