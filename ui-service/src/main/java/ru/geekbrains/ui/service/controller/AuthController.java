package ru.geekbrains.ui.service.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.ui.service.bean.Token;
import ru.geekbrains.ui.service.validation.AuthUser;
import ru.geekbrains.ui.service.validation.RegistrationUser;


@Controller
@RequestMapping("auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinderAuth) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinderAuth.registerCustomEditor(String.class, stringTrimmerEditor); // set message error
    }


    private final Token token;
    private final RestTemplate restTemplate;
    private final AuthenticationManager authenticationManager;


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registrationUser", new RegistrationUser());
        return "auth/registration-form";
    }

    @PostMapping("/register/process")
    public String processRegistrationForm(
            @ModelAttribute("registrationUser") @Validated RegistrationUser registrationUser,
            BindingResult bindingResult,
            Model model) {


        if (bindingResult.hasErrors()) {
            return "auth/registration-form";
        }

        //запрос в дб
        log.info("Save user " + registrationUser.getLogin());
        model.addAttribute("userEmail", registrationUser.getLogin());
        return "redirect:auth";
    }


    @GetMapping
    public String authPage(Model model) {
        model.addAttribute("authUser", new AuthUser());
        model.addAttribute("info", "ведите логин пароль");
        return "auth/auth-form";
    }

    @PostMapping("/process")
    public String processAuthForm(
            @ModelAttribute("authUser") @Validated AuthUser authUser,
            BindingResult bindingResult,
            Model model

    ) {
        if (bindingResult.hasErrors()) {
            return "auth/auth-form";
        }

        String url = "http://localhost:8081/auht/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(authUser.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        token.setToken(null);
        return "redirect:/auth";
    }
}
