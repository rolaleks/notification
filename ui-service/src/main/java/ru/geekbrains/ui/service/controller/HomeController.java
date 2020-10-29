package ru.geekbrains.ui.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.geekbrains.ui.service.bean.Token;

@Controller
@RequestMapping("home")
@Slf4j
@AllArgsConstructor
public class HomeController {

    private final Token token;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("info", "home");
        token.validateToken(token.getToken());
        model.addAttribute("token", token.getToken());
        return "home";
    }

    @GetMapping("/a")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/u")
    @ResponseBody
    public String user() {
        return "user";
    }

}
