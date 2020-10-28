package ru.geekbrains.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserDto {
    String login;
    String password;
    String name;
    String surname;
}
