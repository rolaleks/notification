package ru.geekbrains.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.geekbrains.notification.model.BotStateData;

@Service
@Slf4j
public class BotStateService {

    public BotStateData findUserByChatId(long chatId){

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8079/")
                .path("/stage")
                .queryParam("chatId", String.valueOf(chatId));

        String url = builder.build().encode().toUriString();
        log.info(url);
        RestTemplate restTemplate = new RestTemplate();

        try {
            return new RestTemplate().exchange(url, HttpMethod.GET, null, BotStateData.class).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public void save(BotStateData botStateData){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8079/")
                .path("/stage")
                .queryParam("botStateData", botStateData);

        String url = builder.build().encode().toUriString();
        ResponseEntity<BotStateData> responseEntity = new RestTemplate().exchange(url, HttpMethod.POST, null, BotStateData.class);
        checkingHttpStatus(responseEntity.getStatusCode());
    }

    public void checkingHttpStatus(HttpStatus status){
        switch (status) {
            case CREATED:
                log.info("Объект записан в базу");
                break;
            case BAD_REQUEST:
                log.info("Ошибка");
                break;
        }
    }
}
