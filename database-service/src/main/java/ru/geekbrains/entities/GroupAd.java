package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
//@Entity
//@Table(name = "groups_ad")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class GroupAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "address_id")
    Address address;

    @Column(name = "district_id")
    District district;

    @OneToMany(mappedBy = "group_ad")
    @Cascade(CascadeType.ALL)
    List<Ad> ads;
}
