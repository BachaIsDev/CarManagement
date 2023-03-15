package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Driver;
import com.example.carmanagement.repository.DriverRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverServiceTest {
    private static final Long ID = 1L;

    @Mock
    private DriverRepo driverRepo;

    @InjectMocks
    private DriverService driverService;

    @Test
    void getDriverById_shouldCallRepo() {
        Driver driver = mock(Driver.class);
        when(driverRepo.findById(ID)).thenReturn(Optional.of(driver));

        final Driver lastDriver = driverService.getDriverById(ID).get();

        assertNotNull(lastDriver);
        assertEquals(lastDriver, driver);
        verify(driverRepo).findById(ID);
    }

    @Test
    void getAllDrivers_shouldCallRepo() {
        List<Driver> drivers = List.of(mock(Driver.class), mock(Driver.class));
        when(driverRepo.findAll()).thenReturn(drivers);

        final List<Driver> actualDrivers = driverService.getAllDrivers().get();

        assertNotNull(actualDrivers);
        assertEquals(actualDrivers, drivers);
        verify(driverRepo).findAll();
    }

    @Test
    void saveDriver_shouldCallRepo() {
        Driver driver = mock(Driver.class);

        driverService.saveDriver(driver);

        verify(driverRepo).save(driver);
    }

    @Test
    void deleteDriver_shouldCallRepo() {
        Driver driver = mock(Driver.class);

        driverService.saveDriver(driver);
        driverService.deleteDriver(driver);

        verify(driverRepo).delete(driver);
    }
}