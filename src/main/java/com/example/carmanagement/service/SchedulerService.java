package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// Каждый день в 8 часов проверяет дни рождения

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {

    private static final String CRON = "0 0 8 * * *";

    private final DriverService driverService;

    @Scheduled(cron = CRON)
    public void congratulation() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<Driver> list = driverService.findDriverByBirthDate(month, day);
        if (!list.isEmpty()) {
            list.forEach(driver -> {
                try {
                    String message = "Happy Birthday dear " + driver.getName() + "!";
                    System.out.println(message);
                } catch (Exception e) {
                    System.out.println("Can't be sent.User's id: {}, Error: {}" + driver.getId() + "\n" + e.getMessage());
                }});
            }
        }
}
