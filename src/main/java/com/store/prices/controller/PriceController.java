package com.store.prices.controller;

import com.store.prices.controller.response.BadRequestResponse;
import com.store.prices.controller.response.PriceResponse;
import com.store.prices.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class PriceController {

    private PriceService priceService;
    public static final String PATTERN = "dd-MM-yyyy'T'HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "/price/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPriceByDateProductIdAndBrandId(@PathVariable(name = "productId") Long productId,
                                                               @RequestParam(name = "brandId") Long brandId,
                                                               @RequestParam(name = "date") String date){
        LOGGER.info(" getting price for productId {} brandId {} date {} " , productId , brandId , date);
        LocalDateTime localDateTime;
        try{
           localDateTime = LocalDateTime.parse(date, DATE_TIME_FORMATTER);
        }catch (Exception exception){
            return new ResponseEntity<>(new BadRequestResponse("could not parse date " + date + " invalid format try using: " + PATTERN), HttpStatus.BAD_REQUEST);
        }
        PriceResponse priceResponse = priceService.findPriceByDateProductIdAndBrandId(localDateTime, productId, brandId);
        LOGGER.info(" price found {} " , priceResponse);
        return new ResponseEntity<>(priceResponse, HttpStatus.OK);
    }
}
