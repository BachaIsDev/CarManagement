package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Balance;
import com.example.carmanagement.repository.BalanceRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalanceService {
    private final BalanceRepo balanceRepo;

    public BalanceService(BalanceRepo balanceRepo) {
        this.balanceRepo = balanceRepo;
    }

    public Optional<Balance> getBalanceById(long id){
        return balanceRepo.findById(id);
    }

    public void saveBalance(Balance car){
        balanceRepo.save(car);
    }
}
