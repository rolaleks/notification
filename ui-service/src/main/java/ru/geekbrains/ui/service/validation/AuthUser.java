package ru.geekbrains.ui.service.validation;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class AuthUser {

    @NotNull(message = "is required")
    @Size(min = 4, message = "is required")
    @Email
    private String login;

    @NotNull(message = "is required")
    @Size(min = 4, message = "password is too short")
    private String password;

}
