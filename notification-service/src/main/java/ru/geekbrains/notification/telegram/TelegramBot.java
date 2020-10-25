package ru.geekbrains.notification.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import ru.geekbrains.notification.model.User;
import ru.geekbrains.notification.model.BotData;
import ru.geekbrains.notification.service.BotState;
import ru.geekbrains.notification.service.Notification;
import ru.geekbrains.notification.service.UserService;

import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot implements Notification {

    private String username = "";
    private String token = "";
    private UserService userService;

    @Autowired
    public TelegramBot(TelegramBotsApi telegramBotsApi, UserService userService) throws TelegramApiRequestException {
        telegramBotsApi.registerBot(this);
        this.userService = userService;
    }


    @Override
    public synchronized void sendMessage(User data, List<String> ads) {
        SendMessage message = new SendMessage()
                .setChatId(data.getTelegram())
                .setText(ads.toString());
        try {
            this.execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if(!update.hasMessage()){
            return;
        }

        final String message = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        BotData user = userService.findUserByChatId(chatId); //если пользователь с нами общался null, если нет возвращаем

        TelegramBotContext context;
        BotState state;

        if (user == null){
            log.info("Создаем нового пользователя");
            state = BotState.getInstance();
            //создаем пользователя и задаем ему состояние
            user = new BotData(chatId, state.ordinal());
            userService.save(user);

            context = TelegramBotContext.of(this, user, message);
            state.enter(context);
        } else {
            context = TelegramBotContext.of(this, user, message);
            state = BotState.byID(user.getState());
            log.info("Пользователь существует");
        }

        state.handleInput(context);

        do {
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setState(state.ordinal());
        userService.save(user);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

}
