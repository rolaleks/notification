package ru.geekbrains.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.geekbrains.entity.user.User;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state_data", schema = "bot")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotStateData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "chat_id", unique = true)
    long chatId;

    @Column(name = "state")
    int state;

    public BotStateData(long chatId, int state) {
        this.chatId = chatId;
        this.state = state;
    }
}
