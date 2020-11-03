package ru.geekbrains.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.entity.bot.Answer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private User user;
    private Answer answer;
}
