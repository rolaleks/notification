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
import ru.geekbrains.notification.model.BotStateData;
import ru.geekbrains.notification.service.BotState;
import ru.geekbrains.notification.service.Notification;
import ru.geekbrains.notification.service.BotStateService;

import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot implements Notification {

    private String username = "real_estate_notifier_bot";
    private String token = "1331299206:AAHQpxDPwtp90pkP38E211HGcpUvFL7Ncvw";
    private BotStateService botStateService;

    @Autowired
    public TelegramBot(TelegramBotsApi telegramBotsApi, BotStateService botStateService) throws TelegramApiRequestException {
        telegramBotsApi.registerBot(this);
        this.botStateService = botStateService;
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

        BotStateData user = botStateService.findUserByChatId(chatId); //если пользователь с нами общался null, если нет возвращаем

        TelegramBotContext context;
        BotState state;

        if (user == null){
            log.info("Создаем нового пользователя");
            state = BotState.getInstance();
            //создаем пользователя и задаем ему состояние
            user = new BotStateData(chatId, state.ordinal());
            botStateService.save(user);

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
        botStateService.save(user);
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
