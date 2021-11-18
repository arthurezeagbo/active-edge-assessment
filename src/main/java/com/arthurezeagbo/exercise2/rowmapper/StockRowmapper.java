package com.arthurezeagbo.exercise2.rowmapper;

import com.arthurezeagbo.exercise2.dto.StockRowMapperDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRowmapper implements RowMapper<StockRowMapperDto> {
    @Override
    public StockRowMapperDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        StockRowMapperDto stockRowMapperDto = StockRowMapperDto.builder()
                .createDate(rs.getTimestamp("create_date"))
                .id(rs.getObject("id", Number.class))
                .currentPrice(rs.getBigDecimal("current_price"))
                .lastUpdate(rs.getTimestamp("last_update"))
                .Name(rs.getString("name"))
                .build();
        return stockRowMapperDto;
    }
}
