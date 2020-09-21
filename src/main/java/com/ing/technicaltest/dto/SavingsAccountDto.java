package com.ing.technicaltest.dto;

import com.ing.technicaltest.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor
public class SavingsAccountDto {
    @NotNull
    private String name;

    @NotNull
    private Currency currency;
}
