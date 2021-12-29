package com.store.prices.service;

import com.store.prices.controller.response.PriceResponse;
import com.store.prices.exception.PriceNotFoundException;
import com.store.prices.model.Price;
import com.store.prices.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceResponse findPriceByDateProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId){
        List<Price> prices = priceRepository.findByDateProductIdAndBrandId(date, productId, brandId);
        Price price = prices.stream()
                .findFirst()
                .orElseThrow(() -> new PriceNotFoundException("could not find price for date : " + date + " product id : " + productId + " brand id :"+ brandId));
        return new PriceResponse(price);
    }



}
