package com.example.carmanagement.service;

import com.example.carmanagement.model.car.Car;
import com.example.carmanagement.model.driver.Balance;
import com.example.carmanagement.repository.CarRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    private static final Long ID = 1L;

    @Mock
    private CarRepo carRepo;

    @InjectMocks
    private CarService carService;


    @Test
    void getCarById_shouldCallRepo() {
        Car car = mock(Car.class);
        when(carRepo.findById(ID)).thenReturn(Optional.ofNullable(car));

        final Car actualCar = carService.getCarById(ID).get();

        assertNotNull(actualCar);
        assertEquals(actualCar, car);
        verify(carRepo).findById(ID);
    }

    @Test
    void getAllCars_shouldCallRepo() {
        List<Car> cars = List.of(mock(Car.class), mock(Car.class));
        when(carRepo.findAll()).thenReturn(cars);

        final List<Car> actualCars = carService.getAllCars().get();

        assertNotNull(actualCars);
        assertEquals(actualCars, cars);
        verify(carRepo).findAll();
    }

    @Test
    void saveCar_shouldCallRepo() {
        Car car = mock(Car.class);

        carService.saveCar(car);

        verify(carRepo).save(car);
    }

    @Test
    void deleteCar_shouldCallRepo() {
        Car car = mock(Car.class);

        carService.saveCar(car);
        carService.deleteCar(car);

        verify(carRepo).delete(car);
    }
}