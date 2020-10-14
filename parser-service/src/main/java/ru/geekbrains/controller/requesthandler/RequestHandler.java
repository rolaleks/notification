package ru.geekbrains.controller.requesthandler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.exception.DuplicateTaskException;
import ru.geekbrains.exception.VariableBlankException;
import ru.geekbrains.model.ResponseMessage;
import ru.geekbrains.model.Task;
import ru.geekbrains.service.requesthandler.RequestHandlerService;
import ru.geekbrains.service.requesthandler.TaskService;

@RestController("/")
@RequiredArgsConstructor
@Slf4j
public class RequestHandler {

    private final RequestHandlerService requestHandlerService;

    @PostMapping()
    public ResponseEntity<Object> addTask(@RequestBody @NonNull Task task) throws Exception {
        return requestHandlerService.addTask(task);

    }
}
