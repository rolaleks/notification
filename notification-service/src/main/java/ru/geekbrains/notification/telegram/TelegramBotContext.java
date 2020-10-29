package ru.geekbrains.notification.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.notification.model.BotStateData;

@Data
@AllArgsConstructor
public class TelegramBotContext {
    private final TelegramBot bot; //реализация бота
    private final BotStateData user; //сущность пользователя для которого работают все стадии
    private final String input; //получаем сообщения

    public static TelegramBotContext of (TelegramBot bot, BotStateData user, String text) {
        return new TelegramBotContext(bot, user, text);
    }

}
