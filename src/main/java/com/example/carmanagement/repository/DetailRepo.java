package com.example.carmanagement.repository;

import com.example.carmanagement.model.car.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepo extends JpaRepository<Detail, Long> {
}
