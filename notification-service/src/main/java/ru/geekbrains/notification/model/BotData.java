package ru.geekbrains.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BotData {
    long chatId;
    int state;
}
