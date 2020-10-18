package ru.geekbrains.ui.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.ui.service.validation.AuthUser;
import ru.geekbrains.ui.service.validation.RegistrationUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void authPageTest() throws Exception {
        mvc.perform(get("http://localhost:8080/auth"))
                .andExpect(status().isOk());
    }

    @Test
    void registerPage() throws Exception {
        mvc.perform(get("http://localhost:8080/auth/register"))
                .andExpect(status().isOk());
    }


    @Test
    void processRegistrationForm() throws Exception {
        RegistrationUser registrationUser = new RegistrationUser();
        registrationUser.setLogin("fdsfsdfsd");
        registrationUser.setName("dfsdfsdfsdf");
        registrationUser.setPassword("sfsffsd");
        registrationUser.setMatchingPassword("sfsffsd");
        mvc.perform(post("http://localhost:8080/auth/register/process")
                .flashAttr("registrationUser", registrationUser))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void processAuthForm() throws Exception {
        AuthUser authUser = new AuthUser();
        authUser.setLogin("1");
        authUser.setPassword("1");
        mvc.perform(post("http://localhost:8080/auth/process")
                .flashAttr("authUser", authUser))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}
