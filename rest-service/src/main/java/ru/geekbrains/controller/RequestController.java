package ru.geekbrains.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.model.ResponseMessage;
import ru.geekbrains.service.RequestService;

@RequestMapping("/task")
@RestController
@Slf4j
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseMessage> sendMessage(@RequestBody @NonNull BotData botData) {
        return requestService.create(botData);
    }
}
