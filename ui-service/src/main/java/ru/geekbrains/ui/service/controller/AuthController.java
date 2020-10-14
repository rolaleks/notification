package ru.geekbrains.ui.service.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.ui.service.validation.AuthUser;
import ru.geekbrains.ui.service.validation.RegistrationUser;


@Controller
@RequestMapping("auth")
@Slf4j
public class AuthController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinderAuth ) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinderAuth.registerCustomEditor(String.class, stringTrimmerEditor); // set message error
    }

    @GetMapping
    public String authPage(Model model) {
        model.addAttribute("authUser", new AuthUser());
        return "auth/auth-form";
    }

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

    @PostMapping("/process")
    public String processAuthForm(
            @ModelAttribute("authUser") @Validated AuthUser authUser,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "auth/auth-form";
        }

        //запрос в дб

        log.info("login user " + authUser.getLogin());
        model.addAttribute("userEmail", authUser.getLogin());
        return "auth/auth-form";
    }
}
