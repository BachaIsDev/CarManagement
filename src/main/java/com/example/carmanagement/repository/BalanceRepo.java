package com.example.carmanagement.repository;

import com.example.carmanagement.model.driver.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepo extends JpaRepository<Balance, Long> {

}
