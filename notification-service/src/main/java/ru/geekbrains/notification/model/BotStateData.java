package ru.geekbrains.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BotStateData {
    long chatId;
    int state;
}
