package ru.geekbrains.notification.service;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.BotStateData;
import ru.geekbrains.repository.BotStateRepository;
import ru.geekbrains.repository.UserRepository;

@Service
@Slf4j
public class BotStateService {

    private final BotStateRepository botStateRepository;
    private final UserRepository userRepository;

    @Autowired
    public BotStateService(BotStateRepository botStateRepository, UserRepository userRepository) {
        this.botStateRepository = botStateRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveState(@NotNull BotStateData botStateData) {
        if(botStateData.getUser() == null){
            botStateData.setUser(userRepository.findByLogin("test").get());
        }
        log.info(String.format("save bot state with chatID = {%s}", botStateData.getChatId()));
//        if(!isConstrainsChatId(botStateData.getChatId())) {
            botStateRepository.save(botStateData);
//        }
    }

    @Transactional(readOnly = true)
    public BotStateData findStateByChatId(long chatId) {
        log.info(String.format("find bot state with chatID = {%s}", chatId));
        return botStateRepository.findByChatId(chatId).orElse(null);
    }

    private boolean isConstrainsChatId(long chatId) {
        return botStateRepository.findByChatId(chatId).isPresent();
    }
}
