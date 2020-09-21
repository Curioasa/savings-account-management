package com.ing.technicaltest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ing.technicaltest.domain.SavingsAccount;
import com.ing.technicaltest.domain.User;
import com.ing.technicaltest.dto.SavingsAccountDto;
import com.ing.technicaltest.enums.Currency;
import com.ing.technicaltest.exceptions.OutsideOfWorkingHoursException;
import com.ing.technicaltest.exceptions.UserAlreadyHasAccountException;
import com.ing.technicaltest.exceptions.UserNotFoundException;
import com.ing.technicaltest.services.SavingsAccountService;
import com.ing.technicaltest.services.UserService;
import com.ing.technicaltest.services.WorkingHoursService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SavingsAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WorkingHoursService workingHoursService;

    @MockBean
    private SavingsAccountService savingsAccountService;

    @MockBean
    private UserService userService;

    private ObjectWriter ow;

    @Before
    public void setUp() {
        ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    @Test
    public void addSavingsAccountToUser() throws Exception {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setCurrency(Currency.RON);
        dto.setName("My savings account");

        User user = new User();
        user.setFirstName("Cristina");
        user.setLastName("Draga");

        when(userService.retrieveUser(anyString())).thenReturn(user);

        mockMvc.perform(post("/savings-account")
                .contentType("application/json")
                .content(ow.writeValueAsBytes(dto))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("test_user", "test_pass"))
        ).andExpect(status().isOk());
    }

    @Test
    public void addSavingsAccountToUserOutsideWorkingHours() throws Exception {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setCurrency(Currency.RON);
        dto.setName("My savings account");

        doThrow(OutsideOfWorkingHoursException.class).when(workingHoursService).checkWorkingHours();

        mockMvc.perform(post("/savings-account")
                .contentType("application/json")
                .content(ow.writeValueAsBytes(dto))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("test_user", "test_pass"))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void addSavingsAccountToUserUserNotFound() throws Exception {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setCurrency(Currency.RON);
        dto.setName("My savings account");

        when(userService.retrieveUser(anyString())).thenThrow(UserNotFoundException.class);

        mockMvc.perform(post("/savings-account")
                .contentType("application/json")
                .content(ow.writeValueAsBytes(dto))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("test_user", "test_pass"))
        ).andExpect(status().isNotFound());
    }

    @Test
    public void addSavingsAccountToUserDuplicateAccount() throws Exception {
        SavingsAccountDto dto = new SavingsAccountDto();
        dto.setCurrency(Currency.RON);
        dto.setName("My savings account");

        User user = new User();
        user.setFirstName("Cristina");
        user.setLastName("Draga");
        user.setSavingsAccount(new SavingsAccount());

        when(userService.retrieveUser(anyString())).thenReturn(user);
        doThrow(UserAlreadyHasAccountException.class)
                .when(savingsAccountService)
                .addSavingsAccount(user, dto);

        mockMvc.perform(post("/savings-account")
                .contentType("application/json")
                .content(ow.writeValueAsBytes(dto))
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("test_user", "test_pass"))
        ).andExpect(status().isBadRequest());
    }
}