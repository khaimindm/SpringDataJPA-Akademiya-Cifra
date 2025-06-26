package ru.khaimin.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Класс контроллер для домашней страницы

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
