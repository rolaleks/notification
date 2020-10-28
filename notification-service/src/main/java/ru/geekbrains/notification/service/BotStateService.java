package ru.geekbrains.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
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
                .path("/state")
                .queryParam("chatId", String.valueOf(chatId));

        String url = builder.build().encode().toUriString();
        log.info(url);
//        RestTemplate restTemplate = new RestTemplate();

        try {
            return new RestTemplate().exchange(url, HttpMethod.GET, null, BotStateData.class).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public void save(BotStateData botStateData){
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8079/")
                .path("/state");

        String url = builder.build().encode().toUriString();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> httpEntity = new HttpEntity<Object>(botStateData, requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.POST, httpEntity, BotStateData.class);
//        checkingHttpStatus(responseEntity.getStatusCode());
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
