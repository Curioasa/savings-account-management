package com.ing.technicaltest.services;

import com.ing.technicaltest.dao.SavingsAccountDao;
import com.ing.technicaltest.domain.SavingsAccount;
import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.dto.SavingsAccountDto;
import com.ing.technicaltest.enums.Currency;
import com.ing.technicaltest.exceptions.UserAlreadyHasAccountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class SavingsAccountServiceTest {
    @InjectMocks
    private SavingsAccountService victim;

    @Mock
    private SavingsAccountDao savingsAccountDao;

    @Test
    public void addSavingsAccount() {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setName("Test account");
        dto.setCurrency(Currency.RON);
        User user = new User();

        victim.addSavingsAccount(user, dto);

        verify(savingsAccountDao).save(any(SavingsAccount.class));
        verifyNoMoreInteractions(savingsAccountDao);
    }

    @Test(expected = UserAlreadyHasAccountException.class)
    public void addSavingsAccountUserAlreadyExists() {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setName("Test account");
        dto.setCurrency(Currency.RON);
        User user = new User();
        user.setSavingsAccount(new SavingsAccount());

        victim.addSavingsAccount(user, dto);
    }
}