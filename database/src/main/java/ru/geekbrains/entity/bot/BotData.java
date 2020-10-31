package ru.geekbrains.entity.bot;

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
public class BotData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    Answer answer;

    @Column(name = "chat_id", unique = true)
    long chatId;

    @Column(name = "state")
    int state;

    public BotData(Answer answer, long chatId, int state) {
        this.answer = answer;
        this.chatId = chatId;
        this.state = state;
    }
}
