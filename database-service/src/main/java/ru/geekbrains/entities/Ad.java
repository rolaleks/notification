package ru.geekbrains.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "qua_rooms")
    private Byte quantity;

    @Column(name = "quadrature")
    private Short quadrature;

    @Column(name = "period")
    private String period;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "details_price")
    private String detailsPrice;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;
}
