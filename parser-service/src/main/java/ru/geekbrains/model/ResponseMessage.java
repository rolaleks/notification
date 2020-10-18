package ru.geekbrains.model;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ResponseMessage {
    String title;
    String Details;
    String date;

    public ResponseMessage(String title, String details) {
        this.title = title;
        Details = details;
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
