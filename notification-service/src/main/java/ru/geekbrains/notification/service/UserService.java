package ru.geekbrains.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.notification.model.BotData;

@Service
@Slf4j
public class UserService {

    public BotData findUserByChatId(long chatId){
        //заглушка
        return new BotData(chatId, 1);
    }

    public void save(BotData user){
        //сохраняем в базу
    }
}
