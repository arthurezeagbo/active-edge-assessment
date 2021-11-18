package com.arthurezeagbo.exercise2.service.impl;

import com.arthurezeagbo.exercise2.config.StatusCodes;
import com.arthurezeagbo.exercise2.dto.CreateStockRequestDto;
import com.arthurezeagbo.exercise2.dto.EditStockRequestDto;
import com.arthurezeagbo.exercise2.dto.StockRowMapperDto;
import com.arthurezeagbo.exercise2.model.Stocks;
import com.arthurezeagbo.exercise2.model.response.GenericResponse;
import com.arthurezeagbo.exercise2.repository.StocksRepository;
import com.arthurezeagbo.exercise2.rowmapper.StockRowmapper;
import com.arthurezeagbo.exercise2.service.StocksService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StockServiceImpl implements StocksService {

    private StocksRepository stocksRepository;
    private JdbcTemplate jdbcTemplate;
    private ModelMapper mapper;

    public StockServiceImpl(StocksRepository stocksRepository,JdbcTemplate jdbcTemplate, ModelMapper mapper) {
        this.stocksRepository = stocksRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Object createStock(CreateStockRequestDto request) {
        try{
            Stocks mapStock = mapper.map(request, Stocks.class);

            if(request.getCreateDate() == null)
                mapStock.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));

            mapStock.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            Stocks stock = stocksRepository.save(mapStock);
            String message = "Stock created successfully";
            log.info(message);
            return new GenericResponse<Object>(message,StatusCodes.SUCCESS,stock);
        }catch (Exception ex){
            return new GenericResponse<>(ex.getMessage(), StatusCodes.FAILED, null);
        }
    }

    @Override
    public Object getStockById(Long id) {
        try{
            Optional<Stocks> stock = stocksRepository.findById(id);
            if(stock.isPresent())
                return new GenericResponse<Object>(null,StatusCodes.SUCCESS,stock.get());
        }catch (Exception ex){}

        return new GenericResponse<Object>("No record found",StatusCodes.FAILED,null);

    }

    @Override
    public Object getAllStock(Pageable pageable) {

        String query = "select * from stocks limit "+pageable.getPageSize()+" offset "+pageable.getOffset();
        try{
            List<StockRowMapperDto> stockRowMapperDtoList = jdbcTemplate.query(query, new StockRowmapper());

                return new GenericResponse<Object>(null,StatusCodes.SUCCESS,stockRowMapperDtoList);
        }catch (Exception ex){}

        return new GenericResponse<Object>("No record found",StatusCodes.SUCCESS,null);

    }

    @Override
    public Object updateStockById(Long id, EditStockRequestDto request) {
        try{
            Optional<Stocks> stocks = stocksRepository.findById(id);
            if(stocks.isPresent()){
                stocks.get().setName(request.getName());
                stocks.get().setCurrentPrice(request.getCurrentPrice());
                stocks.get().setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
                Stocks stock = stocksRepository.save(stocks.get());
                String message = "Stock updated successfully";
                log.info(message);
                return new GenericResponse<Object>(message,StatusCodes.SUCCESS,stock);
            }
            return new GenericResponse<Object>("Record not found",StatusCodes.FAILED,null);
        }catch (Exception ex){
            return new GenericResponse<>(ex.getMessage(), StatusCodes.FAILED, null);
        }
    }

    @Override
    public Object deleteStockById(Long id) {
        try{
            if(stocksRepository.findById(id).isPresent()){
                stocksRepository.deleteById(id);
                String message = "Stock deleted successfully";
                log.info(message);
                return new GenericResponse<>(message,StatusCodes.SUCCESS,null);
            }
            return new GenericResponse<>("Record not found",StatusCodes.SUCCESS,null);
        }catch (Exception ex){return new GenericResponse<>(ex.getMessage(),StatusCodes.FAILED,null);}
    }
}
