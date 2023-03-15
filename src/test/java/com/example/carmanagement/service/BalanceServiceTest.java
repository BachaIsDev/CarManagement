package com.example.carmanagement.service;

import com.example.carmanagement.model.driver.Balance;
import com.example.carmanagement.repository.BalanceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    private static final Long ID = 1L;

    @Mock
    private BalanceRepo balanceRepo;

    @InjectMocks
    private BalanceService balanceService;

    @Test
    void getBalanceById_shouldReturnBalance_whenExist() {
        Balance balance = mock(Balance.class);
        when(balanceRepo.findById(ID)).thenReturn(Optional.ofNullable(balance));

        final Balance actualBalance = balanceService.getBalanceById(ID).get();

        assertNotNull(actualBalance);
        assertEquals(actualBalance, balance);
        verify(balanceRepo).findById(ID);
    }

    @Test
    void saveBalance_shouldCallRepo() {
        Balance balance = mock(Balance.class);

        balanceService.saveBalance(balance);

        verify(balanceService).saveBalance(balance);
    }

}