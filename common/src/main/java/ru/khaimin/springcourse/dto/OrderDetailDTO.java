package ru.khaimin.springcourse.dto;

import lombok.Getter;
import lombok.Setter;

// Класс деталей заказа DTO

@Getter
@Setter
public class OrderDetailDTO {

    private DishDTO dishDTO;
    private double price;
    private int quantity;

}
