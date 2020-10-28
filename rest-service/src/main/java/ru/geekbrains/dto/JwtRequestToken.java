package ru.geekbrains.dto;

import lombok.Data;

@Data
public class JwtRequestToken {
    private String login;
    private String password;
}
