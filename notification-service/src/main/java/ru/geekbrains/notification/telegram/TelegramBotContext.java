package ru.geekbrains.notification.telegram;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.notification.service.TaskService;

@Data
@AllArgsConstructor
public class TelegramBotContext {
    private final TelegramBot bot; //реализация бота
    private final BotData user; //сущность пользователя для которого работают все стадии
    private final String input; //получаем сообщения
    private final TaskService taskService; // зупускаем задачу

    public static TelegramBotContext of (TelegramBot bot, BotData user, String text, TaskService taskService) {
        return new TelegramBotContext(bot, user, text, taskService);
    }

    public void sendAnswer(){
        taskService.send(user);
    }

}
