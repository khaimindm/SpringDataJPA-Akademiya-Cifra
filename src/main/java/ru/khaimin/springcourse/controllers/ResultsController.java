package ru.khaimin.springcourse.controllers;

// Класс контроллер для обработки результатов

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultsController {

    @GetMapping("/order_waiting")
    public String showOrderWaitingPage() {
        return "order_waiting";
    }

    @GetMapping("/order_error")
    public String showOrderErrorPage() {
        return "order_error";
    }
}
