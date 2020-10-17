package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cities")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    // Can be null TODO
    @ManyToOne
    @JoinColumn(name = "region_id")
    Region region;

    @OneToMany(mappedBy = "city")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    List<District> districts;
}