package com.arthurezeagbo.exercise2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class EditStockRequestDto {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal currentPrice;
}
