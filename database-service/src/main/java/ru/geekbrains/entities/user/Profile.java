package ru.geekbrains.entities.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "profiles", schema = "client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    //@OneToMany(mappedBy = "profile")
    //@Cascade(CascadeType.ALL)
    //List<PersonalData> personalData;
}
