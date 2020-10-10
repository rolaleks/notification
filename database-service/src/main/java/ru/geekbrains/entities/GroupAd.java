package ru.geekbrains.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @OneToMany(mappedBy = "group_ad")
    @Cascade(CascadeType.ALL)
    private List<Ad> ads;
}
