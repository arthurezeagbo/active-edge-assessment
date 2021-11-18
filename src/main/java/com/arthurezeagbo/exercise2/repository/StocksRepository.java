package com.arthurezeagbo.exercise2.repository;

import com.arthurezeagbo.exercise2.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {
}
