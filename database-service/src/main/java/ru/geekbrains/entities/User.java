package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Collection<Role> roles;
}
