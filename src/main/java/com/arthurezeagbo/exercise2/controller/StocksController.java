package com.arthurezeagbo.exercise2.controller;

import com.arthurezeagbo.exercise2.dto.CreateStockRequestDto;
import com.arthurezeagbo.exercise2.dto.EditStockRequestDto;
import com.arthurezeagbo.exercise2.service.StocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/stocks")
public class StocksController {

    private StocksService stocksService;

    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Validated @RequestBody CreateStockRequestDto request){
        Object response = stocksService.createStock(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable Long id){
        Object response = stocksService.getStockById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit){
        Pageable pageable = PageRequest.of(offset, limit);
        Object response = stocksService.getAllStock(pageable);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateById(@PathVariable Long id,@Validated @RequestBody EditStockRequestDto request){
        Object response = stocksService.updateStockById(id, request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteById(@PathVariable Long id){
        Object response = stocksService.deleteStockById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
