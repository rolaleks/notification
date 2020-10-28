package ru.geekbrains.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.geekbrains.model.ResponseMessage;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(CRUDException.class)
    public ResponseEntity<Object> notFoundExceptionHandler(CRUDException exception, WebRequest request) {
        ResponseMessage message = new ResponseMessage("Объект не был добавлен в базу", exception.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
