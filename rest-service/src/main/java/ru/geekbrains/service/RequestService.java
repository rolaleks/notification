package ru.geekbrains.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.geekbrains.entity.bot.Answer;
import ru.geekbrains.entity.bot.BotData;
import ru.geekbrains.model.RequestParam;
import ru.geekbrains.model.ResponseMessage;
import ru.geekbrains.model.Task;
import ru.geekbrains.utils.DateUtils;

import java.util.*;

@Service
@Slf4j
public class RequestService {
    //todo аналог БД для пользовательских запросов, вынести в БД
    private final Map<String, RequestParam> userRequestList = Collections.synchronizedMap(new HashMap<String, RequestParam>());
    //храним таски
    private final Map<String, Task> taskList = Collections.synchronizedMap(new HashMap<String, Task>());

    public ResponseEntity<ResponseMessage> create(BotData botData){
        ResponseEntity<ResponseMessage> response;

        // создаем новый запрос
        RequestParam newRequest = getRequest(botData);
        //получаем ключ по параметрам запроса
        String keyRequestParam = getKeyFromUserRequest(newRequest);
        // создаем задачу для парсера по новым параметрам
        Task task = creteTask(newRequest.getAnswer());
        log.error("task: " + task.toString());

        //ищем по ключу запрос, если нашли, проверяем его актуальность
        RequestParam oldRequest = userRequestList.get(keyRequestParam);
        if(oldRequest != null){
            if(isRequestActual(oldRequest)){
                // если данные актуальны, берем данные из базы без создания таски
                //todo добавить поиск объявлений в БД и отправку пользователю
                return new ResponseEntity<>(new ResponseMessage("Создание задачи", "Парсинг по данному запросу актуален"), HttpStatus.OK);
            }
        } else {
            // создаем новый запрос в userRequestList
            userRequestList.put(keyRequestParam, newRequest);
        }

        //отправляем задачу и получаем ответ
        response = sendTask(task);

        // проверяем ответ по коду
        if(response.getStatusCode() == HttpStatus.CREATED){
            log.info("задача для парсинга успешно создана");
            taskList.put(task.getTaskId(), task); //todo когда задача выполнится, ее нужно будет отсюда удалить
        } else {
            log.error("ошибка создания задачи");
        }

        return response;
    }

    private Task creteTask(Answer answer){
        return new Task(generateTaskId(), answer.getCountry(), answer.getCity());
    }

    private ResponseEntity<ResponseMessage> sendTask(Task task) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8060").path("/task/create");
        String url = builder.build().encode().toUriString();
        log.info(String.format("url = %s", url));
        log.info(String.format("task = %s", task));
        return new RestTemplate().postForEntity(url, task, ResponseMessage.class);
    }

    private RequestParam getRequest(BotData botData){
        return new RequestParam(botData.getUser(), botData.getAnswer(), botData.getChatId(), new Date());
    }

    // если с даты создания запроса прошло меньше часа, то считаем, что объявление актуально
    private boolean isRequestActual(RequestParam requestParam){
        return DateUtils.getDurationInMinutes(requestParam.getDateCreate(), new Date()) < 60;
    }

    private String getKeyFromUserRequest(RequestParam requestParam) {
        return "" + requestParam.getAnswer().getCountry().toLowerCase() + "." +
                requestParam.getAnswer().getCity().toLowerCase() + ".";
    }

    private String generateTaskId() {
        return UUID.randomUUID().toString();
    }

}
