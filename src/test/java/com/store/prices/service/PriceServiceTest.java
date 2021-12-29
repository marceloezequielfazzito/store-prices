package com.store.prices.service;

import com.store.prices.controller.response.PriceResponse;
import com.store.prices.exception.PriceNotFoundException;
import com.store.prices.model.Price;
import com.store.prices.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PriceServiceTest {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private PriceService priceService;
    private PriceRepository priceRepository;
    private LocalDateTime date;
    private Long productId;
    private Long productId2;
    private Long brandId;
    private Price price;

    @BeforeEach
    public void before (){

        LocalDateTime start = LocalDateTime.parse("2020-06-14T16:00:00", DATE_TIME_FORMATTER);
        LocalDateTime end = LocalDateTime.parse("2020-06-14T16:00:00", DATE_TIME_FORMATTER);
        date = LocalDateTime.parse("2020-06-14T16:00:00", DATE_TIME_FORMATTER);

        price = new Price(1L, 1, productId, brandId, 1L, start, end, 40.0, "EUR");
        productId = 35455L;
        productId2 = 35456L;
        brandId = 1L;
        List<Price> prices = new ArrayList<>();
        prices.add(price);

        priceRepository = mock(PriceRepository.class);
        when(priceRepository.findByDateProductIdAndBrandId(date, productId, brandId)).thenReturn(prices);
        when(priceRepository.findByDateProductIdAndBrandId(date, productId2, brandId)).thenReturn(new ArrayList<>());

        priceService = new PriceService(priceRepository);
    }


    @Test
    public void shouldReturnPrice() throws ParseException {

        //when
        PriceResponse priceResponse = priceService.findPriceByDateProductIdAndBrandId(date, productId, brandId);

        //then
        assertEquals(price.getProductId(),priceResponse.getProductId());
        assertEquals(price.getBrandId(),priceResponse.getBrandId());
        assertEquals(price.getPriceList(),priceResponse.getPriceList());
        assertEquals(price.getPrice(),priceResponse.getPrice());
        assertEquals(price.getCurrency(),priceResponse.getCurrency());
        assertEquals(price.getStart(),priceResponse.getStart());
        assertEquals(price.getEnd(),priceResponse.getEnd());

    }

    @Test
    public void shouldReturnPriceNotFoundException() throws ParseException {
        try {
            priceService.findPriceByDateProductIdAndBrandId(date, productId2, brandId);
        }catch (PriceNotFoundException e){
            assertEquals("could not find price for date : 2020-06-14T16:00 product id : 35456 brand id :1",e.getMessage());
            return;
        }
        fail();

    }



}
