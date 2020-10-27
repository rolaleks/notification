package ru.geekbrains.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entities.BotStateData;
import ru.geekbrains.exception.CRUDException;
import ru.geekbrains.model.ResponseMessage;
import ru.geekbrains.repository.BotStateRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BotStateService {
    public final BotStateRepository repository;

    @Transactional
    public ResponseEntity<Object> save(@NotNull BotStateData botStateData) throws CRUDException {
        log.info(String.format("save bot stage with chatID = {%s}", botStateData.getChatId()));
        if(!isConstrainsChatId(botStateData.getChatId())) {
            try {
                repository.save(botStateData);
                return new ResponseEntity<>(new ResponseMessage("ok", String.format("Объект с chatID = {%s} сохранен", botStateData.getChatId())), HttpStatus.CREATED);
            } catch (Exception e) {
                throw new CRUDException(e.getMessage());
            }
        }
        return new ResponseEntity<>(new ResponseMessage("ok", String.format("Объект с таким chatID = {%s} существует", botStateData.getChatId())), HttpStatus.BAD_REQUEST);
    }

    @Transactional(readOnly = true)
    public BotStateData findByChatId(String chatId) throws CRUDException {
        log.info(String.format("find bot stage with chatID = {%s}", chatId));
        return repository.findByChatId(chatId).orElseThrow(() -> new CRUDException(String.format("Не удалось извлечь chatId = {%s}", chatId)));
    }

    private boolean isConstrainsChatId(String chatId) {
        return repository.findByChatId(chatId).isPresent();
    }
}
