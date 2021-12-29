package com.store.prices.repository;

import com.store.prices.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends CrudRepository<Price,Long> {

    @Query("Select p from Price p where p.start <= :date and p.end >= :date and p.productId = :productId and p.brandId = :brandId order by p.priority desc ")
    List<Price> findByDateProductIdAndBrandId(@Param("date") LocalDateTime date, @Param("productId") Long productId, @Param("brandId") Long brandId);
}
