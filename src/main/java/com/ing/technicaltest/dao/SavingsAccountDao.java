package com.ing.technicaltest.dao;

import com.ing.technicaltest.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Integer> {
}
