package ru.geekbrains.notification.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.notification.model.User;
import ru.geekbrains.notification.service.MessageHandlerService;

import java.util.List;

@RestController("/")
@RequiredArgsConstructor
@Slf4j
public class RequestHandler {

    private final MessageHandlerService requestHandlerService;

    @PostMapping()
    public ResponseEntity<Object> sendMessage(@RequestBody @NonNull User personalData,
                                              @RequestBody @NonNull List<String> ads) throws Exception {
        return requestHandlerService.sendMessage(personalData, ads);
    }
}
