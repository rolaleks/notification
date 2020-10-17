package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;

    @ManyToOne
    @JoinColumn(name = "city_id")
    City city;

    @ManyToOne
    @JoinColumn(name = "district_id")
    District district;

    @ManyToOne
    @JoinColumn(name = "street_id")
    Street street;
}
