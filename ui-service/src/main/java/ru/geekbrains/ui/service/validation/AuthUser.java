package ru.geekbrains.ui.service.validation;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AuthUser {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String login;

    @NotNull(message = "is required")
    @Size(min = 1, message = "password is too short")
    private String password;

}
