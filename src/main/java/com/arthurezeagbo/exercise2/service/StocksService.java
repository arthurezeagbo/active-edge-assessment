package com.arthurezeagbo.exercise2.service;

import com.arthurezeagbo.exercise2.dto.CreateStockRequestDto;
import com.arthurezeagbo.exercise2.dto.EditStockRequestDto;
import org.springframework.data.domain.Pageable;

public interface StocksService {

   Object createStock(CreateStockRequestDto request);
   Object getStockById(Long id);
   Object getAllStock(Pageable pageable);
   Object updateStockById(Long id, EditStockRequestDto request);
   Object deleteStockById(Long id);

}
