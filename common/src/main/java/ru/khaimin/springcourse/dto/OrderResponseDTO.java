package ru.khaimin.springcourse.dto;

import lombok.Getter;
import lombok.Setter;

// Класс для отправки ответа

@Getter
@Setter
public class OrderResponseDTO {

    private String orderId;
    private String dishName;
    private String dishPrice;
    private String dishQuantity;

}
