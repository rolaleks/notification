package ru.geekbrains.parser.cian.temp;

public class Address {
    private Long id;
    private String country;
    private String region;
    private String city;
    private String district;
    private String street;

    public Address() {
    }

    public Address(Long id, String country, String region, String city, String district, String street) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

