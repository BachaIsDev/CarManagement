package com.example.carmanagement.rest;

import com.example.carmanagement.model.driver.Balance;
import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.service.DriverService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PutMapping("/{id}/balance")
    @ApiOperation(value = "change balance of the driver", response = ResponseEntity.class)
    public ResponseEntity<?> updateBalanceById(@PathVariable(name = "id") Long id, @RequestBody Balance balance) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Driver updateDriver = driverService.getDriverById(id).get();
            updateDriver.setBalance(balance);
            map.put("status", 1);
            map.put("data", driverService.getDriverById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "show driver by id", response = ResponseEntity.class)
    public ResponseEntity<?> getDriverById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Optional<Driver> driver = driverService.getDriverById(id);
        if (driver.isPresent()) {
            map.put("status", 1);
            map.put("data", driver);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "show all drivers", response = ResponseEntity.class)
    public ResponseEntity<?> getAllDrivers() {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<List<Driver>> driverList = driverService.getAllDrivers();
        if (driverList.isPresent()) {
            map.put("status", 1);
            map.put("data", driverList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value = "create new driver", response = ResponseEntity.class)
    public ResponseEntity<?> createNewDriver(@RequestBody Driver driver) {
        Map<String, Object> map = new LinkedHashMap<>();
        driverService.saveDriver(driver);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "update existing driver", response = ResponseEntity.class)
    public ResponseEntity<?> updateDriver(@PathVariable(name = "id") Long id, @RequestBody Driver driver) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Driver updateDriver = driverService.getDriverById(id).get();
            updateDriver.setBalance(driver.getBalance());
            updateDriver.setBirthDate(driver.getBirthDate());
            updateDriver.setCars(driver.getCars());
            updateDriver.setDrivingExp(driver.getDrivingExp());
            updateDriver.setDrivingPermit(driver.getDrivingPermit());
            updateDriver.setName(driver.getName());
            updateDriver.setSurname(driver.getSurname());
            updateDriver.setPatronymic(driver.getPatronymic());
            updateDriver.setPassport(driver.getPassport());
            driverService.saveDriver(updateDriver);
            map.put("status", 1);
            map.put("data", driverService.getDriverById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete driver by id", response = ResponseEntity.class)
    public ResponseEntity<?> deleteDriverById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<Driver> driver = driverService.getDriverById(id);
        driverService.deleteDriver(driver.get());
        if (driver.isPresent()) {
            map.put("status", 1);
            map.put("data", driver);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    @ApiOperation(value = "show sorted cars by field and page by page", response = ResponseEntity.class)
    public ResponseEntity<?> getCarsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field){
        Map<String, Object> map = new LinkedHashMap<>();
        Page<Driver> drivers = driverService.findDriversWithPaginationAndSorting(offset, pageSize, field);
        if (!drivers.isEmpty()) {
            map.put("status", 1);
            map.put("data", drivers);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}
