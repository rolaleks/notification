package ru.geekbrains.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer whatsApp;
    String facebook;
    String telegram;
    String email;
}
