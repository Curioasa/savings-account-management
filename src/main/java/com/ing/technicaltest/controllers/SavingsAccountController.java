package com.ing.technicaltest.controllers;

import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.dto.SavingsAccountDto;
import com.ing.technicaltest.services.SavingsAccountService;
import com.ing.technicaltest.services.UserService;
import com.ing.technicaltest.services.WorkingHoursService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController()
@Log4j2
public class SavingsAccountController {
    private UserService userService;

    private SavingsAccountService savingsAccountService;

    private WorkingHoursService workingHoursService;

    @Autowired
    public SavingsAccountController(SavingsAccountService savingsAccountService, WorkingHoursService workingHoursService, UserService userService) {
        this.workingHoursService = workingHoursService;
        this.userService = userService;
        this.savingsAccountService = savingsAccountService;
    }

    @PostMapping("/savings-account")
    ResponseEntity<String> addSavingsAccountToUser(Principal principal, @Valid @RequestBody SavingsAccountDto savingsAccount) {
        workingHoursService.checkWorkingHours();

        User user = userService.retrieveUser(principal.getName());
        savingsAccountService.addSavingsAccount(user, savingsAccount);

        return ResponseEntity.ok("Account has been added successfully");
    }

}