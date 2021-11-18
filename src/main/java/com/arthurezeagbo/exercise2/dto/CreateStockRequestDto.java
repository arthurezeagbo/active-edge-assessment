package com.arthurezeagbo.exercise2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class CreateStockRequestDto {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal currentPrice;
    private Timestamp createDate;
    private Timestamp lastUpdate;
}
