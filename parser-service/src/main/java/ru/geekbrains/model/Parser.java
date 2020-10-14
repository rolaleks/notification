package ru.geekbrains.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Parser {
    private String name;
    private boolean processing = false;
    public void start(String country, String city){};
    //исправить Object -> Ad
    Map<String, Object> getResult() {
        return new HashMap<>();
    };
    public boolean getProcessingStatus(){
        return processing;
    }
}