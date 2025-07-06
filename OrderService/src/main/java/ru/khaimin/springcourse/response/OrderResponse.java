package ru.khaimin.springcourse.response;

import lombok.Getter;
import lombok.Setter;

// Класс для отправки ответа

@Getter
@Setter
public class OrderResponse {
    private String dishName;
    private String dishPrice;
    private String dishQuantity;
}
