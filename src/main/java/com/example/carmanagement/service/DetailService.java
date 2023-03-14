package com.example.carmanagement.service;

import com.example.carmanagement.model.car.Detail;
import com.example.carmanagement.repository.DetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailService {
    private final DetailRepo detailRepo;

    @Autowired
    public DetailService(DetailRepo detailRepo) {
        this.detailRepo = detailRepo;
    }

    public Optional<Detail> getDetailById(long id){
        return detailRepo.findById(id);
    }

    public Optional<List<Detail>> getAllDetails(){
        return Optional.of(detailRepo.findAll());
    }

    public void saveDetail(Detail detail){
        detailRepo.save(detail);
    }

    public void deleteDetailById(long id){
        detailRepo.deleteById(id);
    }
}
