package com.example.carmanagement.rest;

import com.example.carmanagement.model.car.Car;
import com.example.carmanagement.model.car.Detail;
import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.service.CarService;
import com.example.carmanagement.service.DriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(value = "car resources")
@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;
    private final DriverService driverService;

    @Autowired
    public CarController(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "show car by id", response = ResponseEntity.class)
    public ResponseEntity<?> getCarById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<Car> car = carService.getCarById(id);
        if (car.isPresent()) {
            map.put("status", 1);
            map.put("data", car);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "show all cars", response = ResponseEntity.class)
    public ResponseEntity<?> getAllCars() {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<List<Car>> carList = carService.getAllCars();
        if (carList.isPresent()) {
            map.put("status", 1);
            map.put("data", carList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value = "create new car", response = ResponseEntity.class)
    public ResponseEntity<?> createNewCar(@RequestBody Car car) {
        Map<String, Object> map = new LinkedHashMap<>();
        carService.saveCar(car);
        map.put("status", 1);
        map.put("message", "Record is Saved Successfully!");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @PutMapping("/{car_id}/driver/{driver_id}")
    @ApiOperation(value = "change owner of the car", response = ResponseEntity.class)
    public ResponseEntity<?> changeCarOwner(@PathVariable(name = "car_id") Long carId,
                                            @PathVariable(name = "driver_id") Long driverId){
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Car updateCar = carService.getCarById(carId).get();
            Driver newDriver = driverService.getDriverById(driverId).get();
            updateCar.setDriver(newDriver);
            carService.saveCar(updateCar);
            map.put("status", 1);
            map.put("data", carService.getCarById(carId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "update existing car", response = ResponseEntity.class)
    public ResponseEntity<?> updateCar(@PathVariable(name = "id") Long id, @RequestBody Car car) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Car updateCar = carService.getCarById(id).get();
            updateCar.setDriver(car.getDriver());
            updateCar.setCarModel(car.getCarModel());
            updateCar.setManufacturer(car.getManufacturer());
            updateCar.setDetails(car.getDetails());
            updateCar.setVin(car.getVin());
            updateCar.setRegNumber(car.getRegNumber());
            updateCar.setYearOfIssue(car.getYearOfIssue());
            carService.saveCar(updateCar);
            map.put("status", 1);
            map.put("data", carService.getCarById(id));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete car by id", response = ResponseEntity.class)
    public ResponseEntity<?> deleteCarById(@PathVariable(name = "id") Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Optional<Car> car = carService.getCarById(id);
        carService.deleteCar(car.get());
        if (car.isPresent()) {
            map.put("status", 1);
            map.put("data", car);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{car_id}/detail")
    @ApiOperation(value = "change detail in the car", response = ResponseEntity.class)
    public ResponseEntity<?> changeDetail(@PathVariable(name = "car_id") Long carId,
                                            @RequestBody Set<Detail> details){
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Car updateCar = carService.getCarById(carId).get();
            updateCar.setDetails(details);
            carService.saveCar(updateCar);
            map.put("status", 1);
            map.put("data", carService.getCarById(carId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{car_id}/detail")
    @ApiOperation(value = "add new detail to the car", response = ResponseEntity.class)
    public ResponseEntity<?> addNewDetail(@PathVariable(name = "car_id") Long carId,
                                          @RequestBody Detail detail){
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            Car updateCar = carService.getCarById(carId).get();
            Set<Detail> detailSet = updateCar.getDetails();
            detailSet.add(detail);
            updateCar.setDetails(detailSet);
            carService.saveCar(updateCar);
            map.put("status", 1);
            map.put("data", carService.getCarById(carId));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    @ApiOperation(value = "show sorted cars and page by page", response = ResponseEntity.class)
    public ResponseEntity<?> getCarsWithPaginationAndSorting(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field){
        Map<String, Object> map = new LinkedHashMap<>();
        Page<Car> carList = carService.findCarsWithPaginationAndSorting(offset, pageSize, field);
        if (!carList.isEmpty()) {
            map.put("status", 1);
            map.put("data", carList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", 0);
            map.put("message", "Data is not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }
}