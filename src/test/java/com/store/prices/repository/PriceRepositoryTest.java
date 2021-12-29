package com.store.prices.repository;

import com.store.prices.model.Price;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PriceRepositoryTest {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    @Autowired
    private PriceRepository priceRepository;


    @Test
    public void shouldReturnTwoPricesAndUseTheMaxPriorityOne() {
        //given
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T16:00:00", DATE_TIME_FORMATTER);
        Long productId = 35455L;
        Long brandId = 1L;

        //when
        List<Price> prices = priceRepository.findByDateProductIdAndBrandId(dateTime, productId, brandId);
        Price price = prices
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("test failed price not found"));

        //then
        assertEquals(2,prices.size());
        assertEquals(1,price.getPriority());
        assertEquals(productId,price.getProductId());
        assertEquals(brandId,price.getBrandId());
        assertEquals(2L,price.getPriceList());
        assertEquals(25.45,price.getPrice());
        assertEquals("EUR",price.getCurrency());
        assertTrue(price.getStart().isBefore(dateTime));
        assertTrue(price.getEnd().isAfter(dateTime));

    }

    @Test
    public void shouldReturnOnePrice() {
        //given
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T10:00:00",DATE_TIME_FORMATTER);
        Long productId = 35455L;
        Long brandId = 1L;

        //when
        List<Price> prices = priceRepository.findByDateProductIdAndBrandId(dateTime, productId, brandId);
        Price price = prices
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("test failed price not found"));

        //then
        assertEquals(1,prices.size());
        assertEquals(0,price.getPriority());
        assertEquals(productId,price.getProductId());
        assertEquals(brandId,price.getBrandId());
        assertEquals(1L,price.getPriceList());
        assertEquals(35.50,price.getPrice());
        assertEquals("EUR",price.getCurrency());
        assertTrue(price.getStart().isBefore(dateTime));
        assertTrue(price.getEnd().isAfter(dateTime));
    }

    @Test
    public void shouldReturnNoPrices() throws ParseException {
        //given
        String PATTERN = "dd-MM-yyyy'T'HH:mm:ss";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(PATTERN);
        LocalDateTime localDateTime = LocalDateTime.parse("2010-06-14T10:00:00", DATE_TIME_FORMATTER);
        Long productId = 35455L;
        Long brandId = 1L;

        //when
        List<Price> prices = priceRepository.findByDateProductIdAndBrandId(localDateTime, productId, brandId);
        Optional<Price> price = prices
                .stream()
                .findFirst();

        //then
        assertFalse(price.isPresent());
        assertTrue(prices.isEmpty());

    }





}
