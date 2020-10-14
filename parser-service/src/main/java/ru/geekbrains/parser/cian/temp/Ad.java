package ru.geekbrains.parser.cian.temp;


import java.math.BigDecimal;

public class Ad {
    private Long id;
    private Address address;
    private Integer roomsAmount;
    private Float Quadrature;
    private Integer leasePeriod;
    private BigDecimal price;
    private BigDecimal detailsPrice;
    private String title;
    private String description;
    private String url;

    public Ad() {
    }

    public Ad(Long id, Address address, Integer roomsAmount, Float quadrature, Integer leasePeriod, BigDecimal price, BigDecimal detailsPrice, String title, String description, String url) {
        this.id = id;
        this.address = address;
        this.roomsAmount = roomsAmount;
        Quadrature = quadrature;
        this.leasePeriod = leasePeriod;
        this.price = price;
        this.detailsPrice = detailsPrice;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(Integer roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public Float getQuadrature() {
        return Quadrature;
    }

    public void setQuadrature(Float quadrature) {
        Quadrature = quadrature;
    }

    public Integer getLeasePeriod() {
        return leasePeriod;
    }

    public void setLeasePeriod(Integer leasePeriod) {
        this.leasePeriod = leasePeriod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDetailsPrice() {
        return detailsPrice;
    }

    public void setDetailsPrice(BigDecimal detailsPrice) {
        this.detailsPrice = detailsPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
