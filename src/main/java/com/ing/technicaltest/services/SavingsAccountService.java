package com.ing.technicaltest.services;

import com.ing.technicaltest.dao.SavingsAccountDao;
import com.ing.technicaltest.domain.SavingsAccount;
import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.dto.SavingsAccountDto;
import com.ing.technicaltest.exceptions.UserAlreadyHasAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsAccountService {
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    public SavingsAccountService(SavingsAccountDao savingsAccountDao) {
        this.savingsAccountDao = savingsAccountDao;
    }

    public void addSavingsAccount(User user, SavingsAccountDto savingsAccountDTO) {
        if (user.getSavingsAccount() != null) {
            throw new UserAlreadyHasAccountException("The user with the specified username already has an account.");
        }
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setName(savingsAccountDTO.getName());
        savingsAccount.setCurrency(savingsAccountDTO.getCurrency());
        savingsAccount.setUser(user);

        savingsAccountDao.save(savingsAccount);
    }
}
