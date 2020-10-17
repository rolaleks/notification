package ru.geekbrains.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "whats_app")
    private Integer whatsApp;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "email")
    private String email;
}
