package ru.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "groups_ad")
public class GroupAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address_id")
    private Address address;

    @Column(name = "district_id")
    private District district;

    @Column(name = "ads_id")
    private List<Ad> ads;
}
