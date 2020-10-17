package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ads")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;

    @Column(name = "qua_rooms")
    Byte quantity;

    @Column(name = "quadrature")
    Short quadrature;

    @Column(name = "period")
    String period;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "details_price")
    String detailsPrice;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "link")
    String link;
}
