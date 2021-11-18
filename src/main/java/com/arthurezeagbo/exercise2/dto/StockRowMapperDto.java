package com.arthurezeagbo.exercise2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRowMapperDto {

    private Number id;
    private String Name;
    private BigDecimal currentPrice;
    private Timestamp createDate;
    private Timestamp lastUpdate;
}
