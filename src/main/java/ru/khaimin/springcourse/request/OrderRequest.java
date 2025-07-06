package ru.khaimin.springcourse.request;

import lombok.Getter;
import lombok.Setter;

// Класс для отправки запроса с заказом

@Getter
@Setter
public class OrderRequest {
    private String idSelectedDishesJson;
    private String clientIdString;
    private String clientName;
    private String dishesAndQuantityJson;
}
