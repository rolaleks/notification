package ru.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Settings settings;
}
