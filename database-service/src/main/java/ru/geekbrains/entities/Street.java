package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "streets", schema = "geo")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    District district;

    //@OneToMany(mappedBy = "street")
    //@Cascade(CascadeType.ALL)
    //List<GroupAd> groupAds;

    //@OneToMany(mappedBy = "street")
    //@Cascade(CascadeType.ALL)
    //List<Ad> ads;
}
