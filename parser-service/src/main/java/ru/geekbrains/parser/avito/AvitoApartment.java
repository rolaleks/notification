package ru.geekbrains.parser.avito;

import java.math.BigDecimal;

public class AvitoApartment {

    private Integer id;

    private String url;

    private String title;

    private String description;

    private BigDecimal price;

    private String city;

    private String address;

    //Краткое описаие пример "1-к квартира, 38.8 м², 2/14 эт."
    private String infoDescription;

    //продам/аренда итд
    private String offerType;

    private Float areaKitchen;

    private Float areaLive;

    private Float area;

    private int floor;

    private int floorsCount;

    //Монолитный
    private String houseType;

    //новостройка
    private String type;

    //Студия/1/2/3/4 итд
    private String rooms;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public void setInfoDescription(String infoDescription) {
        this.infoDescription = infoDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Float getAreaKitchen() {
        return areaKitchen;
    }

    public void setAreaKitchen(Float areaKitchen) {
        this.areaKitchen = areaKitchen;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getAreaLive() {
        return areaLive;
    }

    public void setAreaLive(Float areaLive) {
        this.areaLive = areaLive;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void setFloorsCount(int floorsCount) {
        this.floorsCount = floorsCount;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public boolean isEmpty(){

        return this.id == null || this.infoDescription.isEmpty() || this.price == null;
    }
}
