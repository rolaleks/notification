package ru.geekbrains.entity.bot;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers", schema = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "country")
    String country;

    @Column(name = "city")
    String city;

    @Column(name = "room")
    int room;

    @Column(name = "price")
    long price;

    @Column(name = "floor")
    int floor;

    @Override
    public String toString() {
        return  "\nСтрана: " + country +
                "\nГород: " + city +
                "\nКоличество комнат: " + room +
                "\nЦена: " + price +
                "\nЭтаж: " + floor;
    }
}
