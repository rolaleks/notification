package ru.geekbrains.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.BotStateData;
import ru.geekbrains.service.BotStateService;

@RestController
@RequestMapping("/state")
@AllArgsConstructor
@Slf4j
public class BotStageController {
    //в спринге 5 внедрение происходит автоматически, если существует конструктор @AllArgsConstructor
    private final BotStateService service;

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody @NonNull BotStateData botStateData) throws Exception {
        return service.save(botStateData);
    }

    @GetMapping()
    public BotStateData find(@RequestParam(name = "chatId") String chatId) throws Exception {
        return service.findByChatId(chatId);
    }
}
