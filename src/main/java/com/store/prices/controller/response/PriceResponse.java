package com.store.prices.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.prices.model.Price;

import java.time.LocalDateTime;


public class PriceResponse {

    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("brand_id")
    private Long brandId;
    @JsonProperty("price_list")
    private Long priceList;
    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime start;
    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime end;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("currency")
    private String currency;


    public PriceResponse(Price price) {
        this.productId = price.getProductId();
        this.brandId = price.getBrandId();
        this.priceList = price.getPriceList();
        this.start = price.getStart();
        this.end = price.getEnd();
        this.price = price.getPrice();
        this.currency = price.getCurrency();
    }

    @JsonCreator
    public PriceResponse(@JsonProperty("product_id")Long productId,
                         @JsonProperty("brand_id")Long brandId,
                         @JsonProperty("price_list") Long priceList,
                         @JsonProperty("start_date") LocalDateTime start,
                         @JsonProperty("end_date") LocalDateTime end,
                         @JsonProperty("price") Double price,
                         @JsonProperty("currency")String currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.start = start;
        this.end = end;
        this.price = price;
        this.currency = currency;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "PriceResponse{" +
                "productId=" + productId +
                ", brandId=" + brandId +
                ", priceList=" + priceList +
                ", start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
