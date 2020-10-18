package ru.geekbrains.ui.service.model.user.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
