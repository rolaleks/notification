package ru.geekbrains.entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @OneToMany(mappedBy = "street")
    @Cascade(CascadeType.ALL)
    private List<GroupAd> groupAds;

    @OneToMany(mappedBy = "street")
    @Cascade(CascadeType.ALL)
    private List<Ad> ads;
}
