package ru.geekbrains.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@Table(name = "state_data", schema = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotStateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "chat_id", unique = true)
    String chatId;

    @Column(name = "state")
    int state;
}
