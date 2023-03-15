package com.example.carmanagement.repository;

import com.example.carmanagement.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT * FROM drivers " +
            "WHERE birth_date IS NOT NULL " +
            "AND extract(MONTH FROM birth_date) = :m " +
            "AND extract(DAY FROM birth_date) = :d",
            nativeQuery = true)
    List<Driver> findByMatchMonthAndMatchDay(@Param("d") int day, @Param("m") int month);
}
