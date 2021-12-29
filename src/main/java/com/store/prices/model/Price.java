package com.store.prices.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PRIORITY")
    private Integer priority;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "BRAND_ID")
    private Long brandId;
    @Column(name = "PRICE_LIST")
    private Long priceList;
    @Column(name = "START_DATE" , columnDefinition = "TIMESTAMP" )
    private LocalDateTime start;
    @Column(name = "END_DATE" , columnDefinition = "TIMESTAMP")
    private LocalDateTime end;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CURR")
    private String currency;

    public Price() {
    }

    public Price(Long id, Integer priority, Long productId, Long brandId, Long priceList,
                 LocalDateTime start, LocalDateTime end, Double price, String currency) {
        this.id = id;
        this.priority = priority;
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.start = start;
        this.end = end;
        this.price = price;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", priority=" + priority +
                ", productId=" + productId +
                ", brandId=" + brandId +
                ", priceList=" + priceList +
                ", start=" + start +
                ", end=" + end +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
